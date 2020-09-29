package app.PatientHealthApp.viewControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import app.PatientHealthApp.formObjects.PatientRegForm;



@Controller
@RequestMapping("temp/signup/")
public class SignUpViewController {

	@GetMapping("patient/{hashCode}")
	public String getPatientView(@PathVariable String hashCode, Model model) {
		model.addAttribute("patientRegForm", new PatientRegForm());
		return "/selfSignup/temp-signup-patientRegistration";
	}
}
