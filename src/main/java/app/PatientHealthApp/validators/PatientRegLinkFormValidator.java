package app.PatientHealthApp.validators;

import java.util.regex.Pattern;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import app.PatientHealthApp.formObjects.PatientRegLinkForm;
import app.PatientHealthApp.repository.UserRepository;

public class PatientRegLinkFormValidator implements Validator{

	//can't be autowired without the class being a bean.
	UserRepository uRepo;
	
	public PatientRegLinkFormValidator(UserRepository uRepo) {
		this.uRepo = uRepo;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return PatientRegLinkForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PatientRegLinkForm form = (PatientRegLinkForm) target;
		
		ValidationUtils.rejectIfEmpty(errors, "usertype", "", "Invalid Usertype.");
		ValidationUtils.rejectIfEmpty(errors, "gmail", "", "Email field must not be empty.");
		String emailPattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$";
		boolean matches = Pattern.matches(emailPattern, form.getGmail());
		
		if (!matches) {
			ValidationUtils.rejectIfEmpty(errors, "gmail", "", "Email is not valid. Please check that it is correcr or contact surgry for further assistance.");
		}
		
	}

}
