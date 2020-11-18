package app.PatientHealthApp.restControllers.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.PatientHealthApp.domain.users.Doctor;
import app.PatientHealthApp.domain.users.Patient;
import app.PatientHealthApp.domain.users.User;
import app.PatientHealthApp.jsonObject.response.Response;
import app.PatientHealthApp.jsonObject.response.doctor.DoctorListResponse;
import app.PatientHealthApp.jsonObject.response.patient.PatientListResponse;
import app.PatientHealthApp.jsonObject.response.surgery.UList;
import app.PatientHealthApp.services.UserServiceDetailsImpl;

@RestController
@RequestMapping("ajax/admin")
public class AdminRestController {
	
	@Autowired
	UserServiceDetailsImpl userServices;

	/**
	 * Returns a list of all patients.
	 * This list can be filtered on the client side.
	 * @return
	 */
	@GetMapping("/doctor-features/search-patients")
	public Object doctorPatientSearch() {
		List<Patient> patients = null;
		patients = userServices.getPatientRepo().findAll();
		
		PatientListResponse res = new PatientListResponse();
		if (patients != null && !patients.isEmpty()) {
			res.setResponse(true);
			res.setPatientListResponse(patients);
		}
		else {
		res.setResponse(false);
		res.setMessage("No patients found. Please speak to technical support for assistance. Number: 1233434534545, Email: tech_sup@tech_sup.com");
		}
		return res;
	}
	
	/**
	 * Returns a list of all doctors.
	 * This list can be filtered on the client side.
	 * @return
	 */
	@GetMapping("/doctor-features/search-doctors")
	public Object getDoctors() {
		DoctorListResponse res = new DoctorListResponse();
		List<Doctor> doctors = null;
		doctors = userServices.getDoctorRepo().findAll();
		if (doctors != null && !doctors.isEmpty()) {
			res.setDoctors(doctors);
			res.setResponse(true);
		}
		else {
		res.setResponse(false);
		res.setMessage("No doctors found. Please speak to technical support for assitance. Number: 1233434534545, Email: tech_sup@tech_sup.com");
		}
		return res;
	}
	
	/**
	 * Returns a list of the selected Doctor's patients.
	 * 
	 * @param doctorId - the id of the selected doctor
	 * @return Object - PatientListResponse - list of patients
	 */
	@GetMapping("/doctor-features/doctor/current-patients/{doctorId}")
	public Object getDoctorPatients(@PathVariable Integer doctorId) {
		PatientListResponse res = new PatientListResponse();
		Doctor doctor = userServices.getDoctorRepo().findById(doctorId.intValue());
		List<Patient> patients = null;
		patients = doctor.getPatients();
		if (patients != null && !patients.isEmpty()) {
			res.setPatientListResponse(patients);
			res.setResponse(true);
		}
		else {
			res.setResponse(false);
			res.setMessage("No patients found.");
		}
		
		return res;
	}
	
	/**
	 * Controller method to add Patients to Doctors (mutual link).
	 * @param request - request body of the http request
	 * @return response - true is successful, false if not.
	 */
	@PostMapping(value="/doctor-features/add-selected-patient", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object addPatientToDoctor(@RequestBody Map<String,Integer> request) {
		Response res = new Response();
		Patient patient = null;
		Doctor doctor = null;
		
		patient = userServices.getPatientRepo().findById(request.get("pid").intValue());
		doctor = userServices.getDoctorRepo().findById(request.get("did").intValue());
		
		if (patient != null && doctor != null) {
			doctor.getPatients().add(patient);
			
			userServices.getDoctorRepo().save(doctor);
			res.setResponse(true);
		}
		else {
			res.setResponse(false);
			res.setMessage("Error. Patient not added. Please speak to technical support for assitance. Number: 1233434534545, Email: tech_sup@tech_sup.com");
		}
		
		return res;
	}
	
	/**
	 * Controller method to add Patients to Doctors (mutual link).
	 * @param request - request body of the http request
	 * @return response - true is successful, false if not.
	 */
	@PostMapping(value="/doctor-features/remove-selected-patient", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object removePatientFromDoctor(@RequestBody Map<String,Integer> request) {
		Response res = new Response();
		Patient patient = null;
		Doctor doctor = null;
		
		patient = userServices.getPatientRepo().findById(request.get("pid").intValue());
		doctor = userServices.getDoctorRepo().findById(request.get("did").intValue());
		boolean isThere = false;
		if (patient != null && doctor != null) {
			
			for (Patient p : doctor.getPatients()) {
				if (p.getId() == request.get("pid").intValue()) {
					isThere = true;
				}
			}
			
			if (isThere) {
				doctor.getPatients().remove(patient);
				userServices.getDoctorRepo().save(doctor);
				res.setResponse(true);
			}
			else {
			res.setResponse(false);
			res.setMessage("Error: Patient was not found in doctor's list to begin with. Please speak to technical support for assitance. Number: 1233434534545, Email: tech_sup@tech_sup.com");
			}
		}
		else {
			res.setResponse(false);
			res.setMessage("Error. Patient not removed. Please speak to technical support for assitance. Number: 1233434534545, Email: tech_sup@tech_sup.com");
		}
		
		return res;
	}
	
	
	
	@GetMapping("/search/users/name/{name}")
	public Object searchPatientName(@PathVariable(required = false) String name ) {
		List<User> users = new ArrayList<User>();
		users =userServices.getUserRepo().findAll();
		List<User> uList = users.stream().filter(user -> user.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
//		List<User> users = userServices.getUserRepo().findByName(name);
		if (uList.isEmpty()) {
			Response res = new Response();
			res.setMessage("No users found.");
			res.setResponse(false);
			return res;
		}
		List<User> users10 = new ArrayList<User>();
		int count = 0;
		for (User u : uList) {
			if (count == 10) {
				break;
			}
			users10.add(u);
			count++;
		}
		UList res = new UList();
		res.setUsers(users10);
		res.setResponse(true);
		return res;
	}
	
	@GetMapping("/search/users/username/{username}")
	public Object searchPatientUsername(@PathVariable(required = false) String username ) {
		

		List<User> users = userServices.getUserRepo().findAllByUsername(username);
		if (users.isEmpty()) {
			Response res = new Response();
			res.setMessage("No users found.");
			res.setResponse(false);
			return res;
		}
		List<User> users10 = new ArrayList<User>();
		int count = 0;
		for (User u : users) {
			if (count == 10) {
				break;
			}
			users10.add(u);
			count++;
		}
		UList res = new UList();
		res.setUsers(users10);
		res.setResponse(true);
		return res;
	}
}
