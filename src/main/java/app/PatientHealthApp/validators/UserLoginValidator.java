package app.PatientHealthApp.validators;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import app.PatientHealthApp.domain.users.User;
import app.PatientHealthApp.formObjects.UserLoginForm;
import app.PatientHealthApp.repository.UserRepository;

/**
 * Validator used to validate user Login.
 * @author Zachary Ishmael
 *
 */
public class UserLoginValidator implements Validator{

	UserRepository uRepo;
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public UserLoginValidator(UserRepository uRepo) {
		this.uRepo = uRepo;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return UserLoginForm.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("Performing User Login Form Validations");
		UserLoginForm form = (UserLoginForm) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "", "Username must not be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password must not be empty.");
		
		boolean nullUser = false;//whether user is return
		User user = null;
		try {
			user = uRepo.findByUsername(form.getUsername());
		} catch (NullPointerException e) {
			errors.rejectValue("password", "", "Invalid username or password.");
			nullUser = true;
		} catch (Exception e) {
			errors.rejectValue("password", "", "Invalid username or password.");
			nullUser = true;
		}
		if (user == null) {
			errors.rejectValue("password", "", "Invalid username or password.");
			nullUser = true;
		}
		if (!nullUser) {
			boolean nullStr = false;// whether string is returned
			String uPassword = "";
			try {
			uPassword= user.getPassword();
			} catch (NullPointerException e) {
				errors.rejectValue("password", "", "Invalid username or password.");
				nullStr = true;
				System.out.println("***Null String = true****");/*if no string returned 
				notify so no password check is performed*/
			}
	
			/*Only if it returns a password string , 
			 * then check if password matches db password*/
			if (!nullStr && !encoder.matches(form.getPassword(), uPassword)) {
				errors.rejectValue("password", "", "Invalid username or password.");
			}
		}
	}
}
