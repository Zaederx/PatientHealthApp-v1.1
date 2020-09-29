package app.PatientHealthApp.services;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import app.PatientHealthApp.domain.users.OAuthUser;
import app.PatientHealthApp.formObjects.UserRegFormInterface;
import app.PatientHealthApp.repository.AdminRepository;
import app.PatientHealthApp.repository.AppointmentRequestRepository;
import app.PatientHealthApp.repository.DoctorRepository;
import app.PatientHealthApp.repository.GMCRepository;
import app.PatientHealthApp.repository.MedicationRepository;
import app.PatientHealthApp.repository.OAuthUserRepository;
import app.PatientHealthApp.repository.PatientRepository;
import app.PatientHealthApp.repository.PrescriptionRepository;
import app.PatientHealthApp.repository.UserRepository;

/**
 * Provides access to all User Repositories.
 * Implements {@link UserDetailsService}.
 * Also overrides {@code loadByUsername(String username)} method in 
 * UserDetails service to provide custom user roles assignment.
 * @author Zachary Ishmael
 *
 */
@Service
public class UserServiceDetailsImpl implements UserDetailsService{
	
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	OAuthUserRepository oauthRepo;
	
	@Autowired
	PatientRepository patientRepo;
	
	@Autowired
	DoctorRepository doctorRepo;
	
	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	MedicationRepository medRepo;
	 
	@Autowired
	PrescriptionRepository presRepo;
	
	@Autowired
	AppointmentRequestRepository reqRepo;
	
	@Autowired
	GMCRepository gmcRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("\n***************** LoadByUsername Called**************");
		System.out.println(username);
		String password = null;
		app.PatientHealthApp.domain.users.User u =  null;
		u = userRepo.findByUsername(username);
		
		OAuthUser oUser = null;
		
		if (u == null) {
			oUser = oauthRepo.findOAuthUserByEmail(username);
		} else {
			password = u.getPassword();
		}
		
		if (oUser != null) {
			u = oUser.getUser();
			password = encode("oauth");
		}
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNotLocked  = true;
		
		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		String role = u.getRole();
		System.out.println("********ROLE******: "+ role);
		System.out.println(role+" created and granted authority.");
		
		authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
		
		System.out.println("*********Username*********: " +u.getUsername());
		if (oUser !=null) {
			
		}
		
		/*Authentication Manager will then try to make sure 
		 * that details submitted match these details that are being returned*/
		return new User(
				u.getUsername(),
				password,
				enabled, 
				accountNonExpired,
				credentialsNonExpired, 
				accountNotLocked,
				authorities
				);
	}

	
	public String encode(String raw) {
		return encoder.encode(raw);
	}
	/**
	 * @return the userRepo
	 */
	public UserRepository getUserRepo() {
		return userRepo;
	}

	/**
	 * @param userRepo the userRepo to set
	 */
	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	/**
	 * @return the oauthRepo
	 */
	public OAuthUserRepository getOauthRepo() {
		return oauthRepo;
	}

	/**
	 * @param oauthRepo the oauthRepo to set
	 */
	public void setOauthRepo(OAuthUserRepository oauthRepo) {
		this.oauthRepo = oauthRepo;
	}

	/**
	 * @return the patientRepo
	 */
	public PatientRepository getPatientRepo() {
		return patientRepo;
	}

	/**
	 * @param patientRepo the patientRepo to set
	 */
	public void setPatientRepo(PatientRepository patientRepo) {
		this.patientRepo = patientRepo;
	}

	/**
	 * @return the doctorRepo
	 */
	public DoctorRepository getDoctorRepo() {
		return doctorRepo;
	}

	/**
	 * @param doctorRepo the doctorRepo to set
	 */
	public void setDoctorRepo(DoctorRepository doctorRepo) {
		this.doctorRepo = doctorRepo;
	}

	/**
	 * @return the adminRepo
	 */
	public AdminRepository getAdminRepo() {
		return adminRepo;
	}

	/**
	 * @param adminRepo the adminRepo to set
	 */
	public void setAdminRepo(AdminRepository adminRepo) {
		this.adminRepo = adminRepo;
	}

	/**
	 * @return the medRepo
	 */
	public MedicationRepository getMedRepo() {
		return medRepo;
	}

	/**
	 * @param medRepo the medRepo to set
	 */
	public void setMedRepo(MedicationRepository medRepo) {
		this.medRepo = medRepo;
	}

	/**
	 * @return the presRepo
	 */
	public PrescriptionRepository getPresRepo() {
		return presRepo;
	}

	/**
	 * @param presRepo the presRepo to set
	 */
	public void setPresRepo(PrescriptionRepository presRepo) {
		this.presRepo = presRepo;
	}

	/**
	 * @return the reqRepo
	 */
	public AppointmentRequestRepository getReqRepo() {
		return reqRepo;
	}

	/**
	 * @param reqRepo the reqRepo to set
	 */
	public void setReqRepo(AppointmentRequestRepository reqRepo) {
		this.reqRepo = reqRepo;
	}

	/**
	 * @return the gmcRepo
	 */
	public GMCRepository getGmcRepo() {
		return gmcRepo;
	}


	/**
	 * @param gmcRepo the gmcRepo to set
	 */
	public void setGmcRepo(GMCRepository gmcRepo) {
		this.gmcRepo = gmcRepo;
	}


	/*
	 * Method to return username
	 */
	public String getUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return username;
	}

	public void validateUser(UserRegFormInterface form, Errors errors) {
		
		String username = form.getUsername();
		String email = form.getEmail();
		String password = form.getPassword();
		String password2 = form.getPassword2();
		
		/**
		 * Just to double check - but validation is already carried dynamically by ajax requests
		 */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Name must not be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors ,"username", "" ,"Username must not be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email must not be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password must not be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password2", "", "Passwords must match: Second Password Field cannot be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gmcNum", "", "GMC cannot be empty.");
		
		
		//Username
		app.PatientHealthApp.domain.users.User user = userRepo.findByUsername(username);
		if (user != null) {
			errors.rejectValue("username", "", "Username already in use.");
		}
		
		//Email
		String emailPattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$";//TODO - is this the best regex pattern for email???
		boolean emailMatches = Pattern.matches(emailPattern, email);
		if (!emailMatches) {
			errors.rejectValue("email", "", "Not valid email");
		}
		
		
		//Password
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
		
		//If password2 is null
		boolean p2Null = password2 == null;
		
		if (!p2Null && !password.equals(password2)) {
			passwordsMatch = "- passwords do not match";
			errors.rejectValue("password", "", passwordsMatch);
			
		}
		
		if (!(password.length() >= 8)) {
			passwordLength = "- must be at least 8 cahracters long";
			errors.rejectValue("password", "", passwordLength);
		}
		
		if (!special.matcher(password).find()) {
			specialCharacters = "- must contain special characters";
			errors.rejectValue("password", "", specialCharacters);
		}
		
		if (!number.matcher(password).find()) {
			containsNumber = "- must contain a number";
			errors.rejectValue("password", "", containsNumber);
		}
		
		if (!uppercase.matcher(password).find()) {
			containsUppercase = "- must contain an uppercased (capital) letter";
			errors.rejectValue("password", "", containsUppercase );
		}
		
		if (!lowercase.matcher(password).find()) {
			containsLowercase = "- must contain a lowercased (common) letter";
			errors.rejectValue("password", "", containsLowercase);
		}
	}
	
	@Bean
	public BCryptPasswordEncoder BCryptEncoder() {
		return encoder;
	}
}
