package app.PatientHealthApp.jsonObject;

import java.util.List;

import app.PatientHealthApp.domain.users.Patient;

public class PatientResponse extends UserResponse {

	private List<DoctorResponse> doctors;
	
	public PatientResponse(Patient p, boolean b) {
		if (b == false) {
			
		}
	}
}
