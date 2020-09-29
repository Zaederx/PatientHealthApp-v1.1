package app.PatientHealthApp.viewControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.PatientHealthApp.addressEnums.DoctorAddressBook;
import app.PatientHealthApp.repository.DoctorRepository;

@Controller
@RequestMapping("doctor/")
public class DoctorController {

	DoctorRepository dRepo;
	
	@GetMapping("home")
	public String doctorHome() {
		return DoctorAddressBook.D_HOME.resource();
	}
	
	@GetMapping("view-patients")
	public String doctorViewPatientDetails() {
		return "doctor/doctor-view-patient-details";
	}
}
