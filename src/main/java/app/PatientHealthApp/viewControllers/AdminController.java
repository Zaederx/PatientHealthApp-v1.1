package app.PatientHealthApp.viewControllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.PatientHealthApp.addressEnums.AdminAddressBook;
import app.PatientHealthApp.domain.users.Admin;
import app.PatientHealthApp.domain.users.Doctor;
import app.PatientHealthApp.domain.users.Patient;
import app.PatientHealthApp.formObjects.AdminRegForm;
import app.PatientHealthApp.formObjects.DoctorRegForm;
import app.PatientHealthApp.formObjects.PatientRegForm;
import app.PatientHealthApp.formObjects.PatientRegLinkForm;
import app.PatientHealthApp.formObjects.UserEditForm;
import app.PatientHealthApp.services.UserServiceDetailsImpl;
import app.PatientHealthApp.services.UserType;
import app.PatientHealthApp.validators.DoctorRegFormValidator;
import app.PatientHealthApp.validators.PatientRegFormValidator;
import app.PatientHealthApp.validators.PatientRegLinkFormValidator;

/**
 * Controller for returning admin views.
 * @author Zachary Ishmael
 *
 */
@Controller
@RequestMapping("admin")
public class AdminController {

	
	@Autowired
	UserServiceDetailsImpl userServices;
	
	//Init Binders - for validation
	@InitBinder("patientRegForm")
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new PatientRegFormValidator(userServices));
	}
	@InitBinder("doctorRegForm")
	protected void initBinderDoc(WebDataBinder binder) {
		binder.addValidators(new DoctorRegFormValidator(userServices));
	}
	@InitBinder("registrationLinkForm")
	protected void initBinderLink(WebDataBinder binder) {
		binder.addValidators(new PatientRegLinkFormValidator(userServices.getUserRepo()));
	}
	@InitBinder("userForm")
	protected void initBinderUserForm() {
		
	}
	
	
	//View Controllers
	@GetMapping("/home")
	public String adminHome() {
		return AdminAddressBook.A_HOME.resource();
	}
	@GetMapping("/assistance")
	public String getAssistance() {
		return "/admin/admin-assistance";
	}
	@GetMapping("/search-users")
	public String searchUsers() {
		
		return "/admin/admin-search-users";
	}
	@GetMapping("/manage-appointments")
	public String manageAppointments() {
		return "/admin/admin-manage-appointments";
	}
	@GetMapping("/delete-users")
	public String deleteUsers() {
		return "/admin/admin-delete-users";
	}
	@GetMapping("/doctor/manage/patients")
	public String manageDoctorPatientsView() {
		return "/admin/doctor/admin-doctor-add-patients";
	}
	
	@GetMapping("/edit-users")
	public String adminEditUsers(Model model) {
		model.addAttribute("userRegForm", new UserEditForm());
		return "/admin/admin-edit-users";
	}
	
	@PostMapping("/submit/user-form")
	public String submitUserEditForm(@Valid @ModelAttribute("userForm") UserEditForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "redirect:/admin/edit-users?error=true";
		}
		
		if ( form.getType() == UserType.doctor.num) {
			
		}
		String userString = "";
		String success = userString+" edited successfully";
		return "redirect:/admin/edit-users?success=";
	}
	
	//Registration view controllers
	@GetMapping("/register-users")
	public String viewUsers(Model model) {
		model.addAttribute("patientRegForm", new PatientRegForm());
		model.addAttribute("doctorRegForm", new DoctorRegForm());
		model.addAttribute("adminRegForm", new AdminRegForm());
		model.addAttribute("registrationLinkForm", new PatientRegLinkForm());
		return "admin/admin-register-users";
	}
	@PostMapping("/register-patient")
	public String registerPatient(@Valid @ModelAttribute("patientRegForm") PatientRegForm form, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
//			model.addAttribute("patientRegForm", new PatientRegForm());// cause conflicts otherwise
			model.addAttribute("doctorRegForm", new DoctorRegForm());
			model.addAttribute("adminRegForm", new AdminRegForm());
			return "redirect:/admin/register-users?error=true";
		} else {
		Patient p = new Patient(form);
		userServices.getPatientRepo().save(p);
		
		}
		String success = "Patient added successfully.";
		return "redirect:/admin/register-users?success="+success;
	}
	@PostMapping("/register-doctor")
	public String registerDoctor(@Valid @ModelAttribute("doctorRegForm") DoctorRegForm form, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("patientRegForm", new PatientRegForm());
//			model.addAttribute("doctorRegForm", new DoctorRegForm());// cause conflicts otherwise
			model.addAttribute("adminRegForm", new AdminRegForm());
			return "redirect:/admin/register-users?error=true";
		} else {
		Doctor d = new Doctor(form);
		userServices.getDoctorRepo().save(d);
		
		}
		String success = "Doctor added successfully.";
		return "redirect:/admin/register-users?success="+success;
	}
	@PostMapping("/register-admin")
	public String registerDoctor(@Valid @ModelAttribute("adminRegForm") AdminRegForm form, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("patientRegForm", new PatientRegForm());
			model.addAttribute("doctorRegForm", new DoctorRegForm());
//			model.addAttribute("adminRegForm", new AdminRegForm()); // cause conflicts otherwise
			return "redirect:/admin/register-users?error=true";
		} else {
		Admin a = new Admin(form);
		userServices.getAdminRepo().save(a);
		
		}
		String success = "Admin added successfully.";
		return "redirect:/admin/register-users?success="+success;
	}
	@PostMapping("/register-link")
	public String sendRegistrationLink(@Valid @ModelAttribute("registrationLinkForm") PatientRegLinkForm form, BindingResult result, Model model) {
		
		//send registrationLink to patient email
		//TODO - TEMP REGISTRATION PAGE LINK
		
		//return to registration page
		String success = "Doctor added successfully.";
		return "redirect:/admin/register-users?success="+success;
	}
	
	
	
}
