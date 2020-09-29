package app.PatientHealthApp.validators;

import java.util.regex.Pattern;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import app.PatientHealthApp.domain.users.User;
import app.PatientHealthApp.formObjects.DoctorRegForm;
import app.PatientHealthApp.formObjects.UserRegFormInterface;
import app.PatientHealthApp.repository.UserRepository;
import app.PatientHealthApp.services.UserServiceDetailsImpl;

/**
 * Performs second validation check on data as precaution.
 * (Information is already validated by ajax requests
 * during user input).
 * @author zacharyishmael
 *
 */
public class DoctorRegFormValidator implements Validator{
	//Autowired doesn't work inside of non bean classes
	UserServiceDetailsImpl userServices;
	
	public DoctorRegFormValidator(UserServiceDetailsImpl userServices) {
		this.userServices = userServices;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return DoctorRegForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserRegFormInterface form = (UserRegFormInterface) target;
		
		//performs validation checks
		userServices.validateUser(form, errors);
		
		
		//can add additional checks if needed
		
	}
	
}
