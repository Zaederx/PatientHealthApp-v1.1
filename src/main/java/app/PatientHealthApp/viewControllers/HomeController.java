package app.PatientHealthApp.viewControllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.View;

import app.PatientHealthApp.addressEnums.AdminAddressBook;
import app.PatientHealthApp.addressEnums.DoctorAddressBook;
import app.PatientHealthApp.addressEnums.PAddressBook;
import app.PatientHealthApp.formObjects.UserLoginForm;
import app.PatientHealthApp.repository.UserRepository;
import app.PatientHealthApp.validators.UserLoginValidator;

@Controller
public class HomeController {
	@Autowired
	UserRepository uRepo;
	@InitBinder("userLoginForm")
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new UserLoginValidator(uRepo));
	}
	
	@GetMapping("/")
	public String root() {
		System.out.println("/ root controller");
		return redirectUserHome("home/home");
	}
	
	@PostMapping("/validate") 
	public String validateUser (HttpServletRequest request , @Valid @ModelAttribute("userLoginForm") UserLoginForm form, BindingResult result, Model model) {
		System.out.println("At the validate controller");
		if (result.hasErrors()) {
			System.out.println("*********Has Errors**********");
			return "home/login";
		}
		
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		return "redirect:/authenticateUser";
	}
	@GetMapping("/home")
	public String home() {
		
		return "home/home";
	}
	
	@GetMapping("/registration")
	public String register () {
		return "home/register";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("userLoginForm", new UserLoginForm());
		return redirectUserHome("home/login");
	}

	public String redirectUserHome(String sDefault) {
		System.out.println("RedirectHome");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = auth.getAuthorities().stream().findFirst().get().getAuthority();
		System.out.println(role);
		switch (role) {
		case "ROLE_PATIENT": return "redirect:"+PAddressBook.P_HOME.controller();
		case "ROLE_DOCTOR":  return "redirect:"+DoctorAddressBook.D_HOME.controller();
		case "ROLE_ADMIN": return "redirect:"+AdminAddressBook.A_HOME.controller();
		}
		return sDefault;
	}
}
