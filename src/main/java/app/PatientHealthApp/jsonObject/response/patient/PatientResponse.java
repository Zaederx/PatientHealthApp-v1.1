package app.PatientHealthApp.jsonObject.response.patient;

import java.util.List;

import app.PatientHealthApp.domain.users.Patient;
import app.PatientHealthApp.jsonObject.response.doctor.DoctorResponse;
import app.PatientHealthApp.jsonObject.response.surgery.UserResponse;

public class PatientResponse extends UserResponse {

	private List<DoctorResponse> doctors;
	
	public PatientResponse(Patient p, boolean b) {
		if (b == false) {
			
		}
	}
}
