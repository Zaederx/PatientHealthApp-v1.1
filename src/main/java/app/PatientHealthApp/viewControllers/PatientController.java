package app.PatientHealthApp.viewControllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.PatientHealthApp.addressEnums.PAddressBook;
import app.PatientHealthApp.domain.users.Patient;
import app.PatientHealthApp.formObjects.AppointmentRequestForm;
import app.PatientHealthApp.repository.PatientRepository;

@Controller
@RequestMapping("patient/")
public class PatientController {
	
	@Autowired
	PatientRepository pRepo;
	
	@GetMapping("home")
	public String patientHome(){
		return PAddressBook.P_HOME.resource();
	}
	
	@GetMapping("prescriptions")
	public String prescriptions () {
		return "patient/patient-prescriptions";
	}
	
	@GetMapping("details")
	public String viewPatientDetails() {
		return "patient/patient-details";
	}
	
	@GetMapping("appointments/manage")
	public String viewAppointments(Model model){
		model.addAttribute("response", new AppointmentRequestForm());
		return "patient/patient-request-appointments";
	}
	
	@GetMapping("medication/requests")
	public String viewMedicationRequests() {
		return "patient/patient-medication";
	}
	
	@GetMapping("messages/manage")
	public String viewMessages() {
		return "patient/patient-messages";
	}

	@GetMapping("help")
	public String viewHelpPage() {
		return "patient/patient-assistance";
	}
	
	@GetMapping("patient/doctor/details")
	public String viewDoctorDetails() {
		return "patient/patient-doctors";
	}
	
	@GetMapping("presciptions/view")
	public String viewPrescriptions() {
		return "patient/view-prescriptions";
	}
}
