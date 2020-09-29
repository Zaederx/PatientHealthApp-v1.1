package app.PatientHealthApp.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import app.PatientHealthApp.domain.users.Patient;
import app.PatientHealthApp.domain.users.User;
import app.PatientHealthApp.formObjects.PatientRegForm;
import app.PatientHealthApp.repository.UserRepository;
import app.PatientHealthApp.services.UserServiceDetailsImpl;

/**
 * Used to perform extra validate check on PatientRegForm.
 * @author Zachary Ishmael
 *
 */
public class PatientRegValidator implements Validator {
	UserServiceDetailsImpl userServices;
	
	public PatientRegValidator(UserServiceDetailsImpl userServices) {
		this.userServices = userServices;
	}
	
	//TODO - support method inccorect - should be PateitnRegForm
	@Override
	public boolean supports(Class<?> clazz) {
		return PatientRegForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PatientRegForm form = (PatientRegForm)target;
		
		//performs validation checks
		userServices.validateUser(form, errors);
		
		
		//can add additional checks if needed
		
	}

}
