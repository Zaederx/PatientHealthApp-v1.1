package app.PatientHealthApp.jsonObject.response.doctor;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import app.PatientHealthApp.domain.users.Doctor;
import app.PatientHealthApp.domain.users.Patient;
import app.PatientHealthApp.jsonObject.response.patient.PatientResponse;
import app.PatientHealthApp.jsonObject.response.surgery.UserResponse;

/**
 * A class to help respresent Doctors in json.
 * Necessary converting doctor objects directly to json
 * causes an unterminating string.
 * @author Zachary Ishmael
 *
 */
@JsonInclude(Include.NON_NULL)
public class DoctorResponse extends UserResponse {
	
	private List<PatientResponse> patients;

	public DoctorResponse(Doctor doctor, boolean b) {
		if (b == false) {
			patients = null;
		}
	}
	/**
	 * @return the patients
	 */
	public List<PatientResponse> getPatients() {
		return patients;
	}

	/**
	 * @param patients the patients to set
	 */
	public void setPatients(List<PatientResponse> patients) {
		this.patients = patients;
	}
	
	public void setPatientsFromDoctor(Doctor doctor) {
		
		for (Patient p : doctor.getPatients()) {
			
		}
		this.patients = patients;
	}
	
	
	
	
}
