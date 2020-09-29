package app.PatientHealthApp.viewControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import app.PatientHealthApp.formObjects.PatientRegForm;

@Controller
@RequestMapping("register/")
public class RegistrationController {

	@GetMapping("patient")
	public String registerPatient(@ModelAttribute("patientRegForm") PatientRegForm form) {
		
		return null;
	}
}
