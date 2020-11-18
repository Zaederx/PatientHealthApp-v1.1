package app.PatientHealthApp.restControllers.doctor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.PatientHealthApp.domain.users.Doctor;
import app.PatientHealthApp.domain.users.Patient;
import app.PatientHealthApp.jsonObject.response.patient.IndividualPatientResponse;
import app.PatientHealthApp.jsonObject.response.patient.PatientListResponse;
import app.PatientHealthApp.services.UserServiceDetailsImpl;

@RestController
@RequestMapping("ajax/doctor/")
public class DoctorRestController {

	@Autowired
	UserServiceDetailsImpl userServices;
	
	@GetMapping("/patientList")
	public Object getPatientList() {
		PatientListResponse res = new PatientListResponse();
		String username = userServices.getUsername();
		Doctor doctor = userServices.getDoctorRepo().findByUsername(username);
		List<Patient> patientList = null;
		patientList = doctor.getPatients();
		if (patientList != null) {
			res.setResponse(true);
			res.setPatientListResponse(patientList);
		}
		res.setResponse(false);
		res.setMessage("No patients assigned. Please speak to administration for help.");
		return res;
	}
	
	@GetMapping("patient/data/{patientId}")
	public Object getPatientData(@PathVariable int patientId) {
		IndividualPatientResponse res = null;
		Patient p = null;
		p = userServices.getPatientRepo().findById(patientId);
		if (p != null) {
			res = new IndividualPatientResponse(p);
			res.setResponse(true);
		} else {
		res = new IndividualPatientResponse();
		res.setResponse(false);
		res.setMessage("No patient found with this ID. Please speak to administration for assistance.");
		}
		return res;
	}
	
	
	
}
