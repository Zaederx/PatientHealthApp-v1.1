package app.PatientHealthApp.restControllers.patient;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.PatientHealthApp.domain.objects.surgery.AppointmentRequest;
import app.PatientHealthApp.domain.users.Patient;
import app.PatientHealthApp.formObjects.AppointmentRequestForm;
import app.PatientHealthApp.jsonObject.response.Response;
import app.PatientHealthApp.jsonObject.response.surgery.AppointmentRequestResponseList;
import app.PatientHealthApp.jsonObject.response.surgery.PrescriptionResponseList;
import app.PatientHealthApp.services.UserServiceDetailsImpl;

@RestController
@RequestMapping("ajax/patient/")
public class PatientRestController {

	@Autowired
	HttpSession session;
	@Autowired
	UserServiceDetailsImpl userServices;
	
	
	
	@GetMapping("retrieve-u")
	public Object retrieveAccountUsername() {
		String username = userServices.getUsername();
		Response res = new Response();
		if (username != null) {
			res.setResponse(true);
			res.setMessage(username);
			res.setMessages(null);
		}
		
		return res;
	}

	
	
	
	
	@GetMapping("prescriptions")
	public Object getPrescriptions() {
		String username = userServices.getUsername();
		PrescriptionResponseList res = new PrescriptionResponseList();
		Patient p = null;
		if (username != null) {
			p = userServices.getPatientRepo().findByUsername(username);
		} else {
			res.setResponse(false);
			res.setMessage("Invalid user session. Please try logging in again.");
		}
		if (p != null) {
			res.setPrescriptions(p.getPrescriptions());
			res.setResponse(true);
			return res;
		}
		
		return res;
	}
	
	public Object getOrderRequests() {
		return null;
	}
	
	
//	@GetMapping("appointments")
//	public Object getAppointments() {
//		String username = getUsername();
//		Patient patient = userServices.getPatientRepo().findByUsername(username);
//		patient.get
//		AppointmentResponse res = new AppointmentResponse();
//		res.setResponse(true);
//		
//		
//		return res;
//	}
	
	
	
	@GetMapping("editAccount")
	public Object editAccount(@RequestParam(name = "username") String newUsername, @RequestParam(name = "current", defaultValue = "empty") String currentP, @RequestParam(name = "new", defaultValue = "empty") String newP, @RequestParam(name = "repeat", defaultValue = "empty") String repeatP) {
		Response res = new Response();
		String currentUsername = userServices.getUsername();
		
		boolean emptyParam = false;
		/* Check if fields*/
		if (newUsername.equals("empty")) {
			emptyParam = true;
			res.getMessages().add("Please enter a new username.");
		}
		
		if (currentP.equals("empty")) {
			emptyParam = true;
			res.getMessages().add("Please enter your current password.");
		}
		
		if (newP.equals("empty")) {
			emptyParam = true;
			res.getMessages().add("Please enter a new password.");
		}
		
		if (repeatP .equals("empty")) {
			emptyParam = true;
			res.getMessages().add("Please repeat your new password.");
		}
		
		if (emptyParam) {
			res.setResponse(false);
			return res;
		}
		
		Patient p = null;
		p = userServices.getPatientRepo().findByUsername(currentUsername);
		if (p != null) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			encoder.matches(currentP, p.getPassword());
			/*Set new field values*/
			if (newP.equals(repeatP)) {
				
				
				p.setPassword(newP);
				p.setUsername(newUsername);
				userServices.getPatientRepo().save(p);
				
				/*Manually (Re)authenticate user*/
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(newUsername, newP);
				SecurityContext context = SecurityContextHolder.getContext();
				context.setAuthentication(authToken);
				/*Return Context to HTTP session*/
				session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
				
				res.setResponse(true);
				res.setMessage(null);
				res.getMessages().add("Account Succesfully Added");
				return res;
			}
			res.setResponse(false);
			res.getMessages().add("Passwords do not match");
		}
		
		return res;
	}
	
	
	@GetMapping("appointment/requests/display")
	public Object getAppointmentRequests() {
		String username = userServices.getUsername();
		Patient patient = userServices.getPatientRepo().findByUsername(username);
		AppointmentRequestResponseList res = new AppointmentRequestResponseList();
		if (patient.getAppointmentRequests().isEmpty()) {
			res.setResponse(false);
			res.setMessage("No active requests at present.");
		} else {
			res.setRequests(patient.getAppointmentRequests());
			res.setResponse(true);
		}
		
		return res;
	}
	
	/**
	 * To cancel appointments
	 * @param appointmentId
	 * @param message
	 * @return
	 */
//	@PostMapping("appointments/cancel/{appointmentId}/{message}")
//	public Object cancelAppointments(@PathVariable String appointmentId, @PathVariable String message) {
//		
//		Response res = new Response();
//		
//		return res;
//	}

	
	@PostMapping(value = "appointment-request/submit",consumes = "application/json")
	public Object requestAppointment(@RequestBody AppointmentRequestForm response) {
		String username = userServices.getUsername();
		Patient patient = userServices.getPatientRepo().findByUsername(username);
		
		/*Save appointment requests*/
		AppointmentRequest appointmentRequest = new AppointmentRequest(response);
		appointmentRequest.setPatient(patient);
		userServices.getReqRepo().save(appointmentRequest);
		
		Response res = new Response();
		res.setResponse(true);
		res.setMessage("received");
		return res;
	}
	
	


}
