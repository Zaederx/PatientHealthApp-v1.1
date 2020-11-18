package app.PatientHealthApp.restControllers.surgery;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.PatientHealthApp.domain.users.Patient;
import app.PatientHealthApp.domain.users.User;
import app.PatientHealthApp.jsonObject.response.Response;
import app.PatientHealthApp.jsonObject.response.surgery.PasswordValidationResponse;
import app.PatientHealthApp.jsonObject.response.surgery.UList;
import app.PatientHealthApp.jsonObject.response.surgery.UserResponse;
import app.PatientHealthApp.repository.AdminRepository;
import app.PatientHealthApp.repository.DoctorRepository;
import app.PatientHealthApp.repository.PatientRepository;
import app.PatientHealthApp.repository.UserRepository;
import app.PatientHealthApp.services.UserServiceDetailsImpl;
import app.PatientHealthApp.services.UserType;

@RestController
@RequestMapping("ajax")
public class ValidationController {
	@Autowired
	UserServiceDetailsImpl userServices;
	
	
	@Autowired
	UserServiceDetailsImpl uService;
	@GetMapping("/search-user/{username}/{usertype}")
	public Object getUsername(@PathVariable String username, @PathVariable String usertype) {
		
		UserResponse res = new UserResponse();

		int type = Integer.parseInt(usertype);
		User user = null;
		if (type == UserType.doctor.num) {
			user = userServices.getDoctorRepo().findByUsername(username);
		}
		else if (type == UserType.patient.num) {
			user = userServices.getPatientRepo().findByUsername(username);
		}
		else if (type == UserType.admin.num) {
			user = userServices.getAdminRepo().findByUsername(username);
		}
		
		if (user == null) {
			res.setResponse(false);
			res.setMessage("No user found.");
			return res;
		}
		System.out.println("**********User Details*********");
		System.out.println(user.toString());
		res.setResponse(true);
		res.setUser(user);
		return res;
	}
	
	@GetMapping("/search-name/{name}/{usertype}")
	public Object getUserByName(@PathVariable String name, @PathVariable String usertype) {
		int type = Integer.parseInt(usertype);
		List<?> users = null;
		if (type == UserType.doctor.num) {
			users = userServices.getDoctorRepo().findByName(name);
		}
		else if (type == UserType.patient.num) {
			users = userServices.getPatientRepo().findByName(name);
		}
		else if (type == UserType.admin.num) {
			users = userServices.getAdminRepo().findByName(name);
		}
		

		if (users == null) {
			Response res = new Response();
			res.setResponse(false);
			res.setMessage("No users found.");
			return res;
		}
		UList list = new UList();
		list.setResponse(true);
		list.setUsers(users);
		return list;
	}
	
	/**
	 * Used to check whether a username is foudn in Database.
	 * Can be user for any user class/entity.
	 * @param username
	 * @return
	 */
	@GetMapping("/is-user/{username}")
	public Response isUsername(@PathVariable(required = false) String username) {
		Response res = new Response();
		boolean exits = true;
		
		if(username.isBlank()) {
			res.setResponse(!exits);
			return res;
			}
		System.out.println("is-user/{username} called");
		User user = userServices.getUserRepo().findByUsername(username);
		try {
		if ( user != null) {
			res.setResponse(exits);
			res.setMessage("Username already exists. Choose another username.");
			return res;
		}}
		catch (Exception e) {
			res.setResponse(exits);
			return res;
		}
		System.out.println("response = true");
		res.setResponse(!exits);
		return res;
	}
	
	/**
	 * Check whether an email is found in the Database.
	 * This method is only necessary for the patient class,
	 * as they are the only class that have emails registered.
	 * @param email
	 * @return
	 */
	@GetMapping("/is-valid/email/{email}")
	public Response isValidEmail(@PathVariable(required = false) String email) {
		Response res = new Response();
		boolean valid = false;
		
		if (email.isBlank()) {
			res.setResponse(!valid);
		}
		
		System.out.println("/is-valid/email/{email} - called");
		
		Patient p = uService.getPatientRepo().findByEmail(email);
		String emailPattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$";//TODO - is this the best regex pattern for email???
		boolean matches = Pattern.matches(emailPattern, email);
		
		if(!matches) {
			res.setResponse(!valid);
			res.setMessage("Not valid email. Please enter a valid email address "
					+ "or contact our administrative team for assistance.");
			return res;
		}
		
		try {
			if (p != null) {
				res.setResponse(!valid);
				res.setMessage("This email is already in use."
						+ " Please choose a unique email or contact our "
						+ "administrative team for assistance.");//TODO - help / support / assistance ?? which is better
				return res;
			} 
		} catch (Exception e) {
			res.setResponse(!valid);
			res.setMessage("There seems to be a problem."
					+ " Please contact our administrative team for assistance.");//TODO
			return res;
		}
		res.setResponse(valid);
		
		return res;
	}
	
	/**
	 * Method for password validation.
	 * Method checks if first password is valid &
	 * whether second password field matches first
	 * @param password
	 * @param passwordTwo
	 * @return
	 */
	@GetMapping(path = {"/is-valid/password/{password}","/is-valid/password/{password}/{passwordTwo}",
						"/matches/{passwordTwo}","/matches/{password}/{passwordTwo}"})
	public Response isValidPassword(@PathVariable(required = false, name="password") String password, @PathVariable(required = false, name = "passwordTwo") String passwordTwo) {
		PasswordValidationResponse res = new PasswordValidationResponse();
		boolean valid = false;
		
		boolean p1Null = password == null;//if password null - true
		boolean p2Null = passwordTwo == null;
		
		//if password 1 not present - return error
		if (p1Null || password.isBlank()) {
			res.setResponse(!valid);
			//TODO make error message more user friendly
			res.setMessage("First password field is empty.");
			return res;
		}
		

		//Password Regex rules
		String spec = "[^a-z0-9]";
		String upper = "[A-Z]";
		String lower = "[a-z]";
		String num = "[0-9]";
		
		//Password Error messages
		String passwordsMatch;
		String passwordLength;
		String specialCharacters;
		String containsNumber;
		String containsUppercase;
		String containsLowercase;

		Pattern special = Pattern.compile(spec, Pattern.CASE_INSENSITIVE);
		Pattern uppercase = Pattern.compile(upper);
		Pattern lowercase = Pattern.compile(lower);
		Pattern number = Pattern.compile(num);
		res.setMessage("password");
		
		if (!p2Null && !password.equals(passwordTwo)) {
			passwordsMatch = "- passwords do not match";
			res.setPasswordsMatch(passwordsMatch);
			res.setResponse(!valid);
		}
		
		if (!(password.length() >= 8)) {
			passwordLength = "- must be at least 8 cahracters long";
			res.setPasswordLength(passwordLength);
			res.setResponse(!valid);
		}
		
		if (!special.matcher(password).find()) {
			specialCharacters = "- must contain special characters";
			res.setSpecialCharacters(specialCharacters);
			res.setResponse(!valid);
		}
		
		if (!number.matcher(password).find()) {
			containsNumber = "- must contain a number";
			res.setContainsNumber(containsNumber);
			res.setResponse(!valid);
		}
		
		if (!uppercase.matcher(password).find()) {
			containsUppercase = "- must contain an uppercased (capital) letter";
			res.setContainsUppercase(containsUppercase);
			res.setResponse(!valid);
		}
		
		if (!lowercase.matcher(password).find()) {
			containsLowercase = "- must contain a lowercased (common) letter";
			res.setContainsLowercase(containsLowercase);
			res.setResponse(!valid);
		}
		
		
		return res;
	}
	
	
}
	

