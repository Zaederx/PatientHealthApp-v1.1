package app.PatientHealthApp.jsonObject.response.patient;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import app.PatientHealthApp.domain.users.Patient;
import app.PatientHealthApp.jsonObject.response.Response;
/**
 * Class used as a JSON {@link Response} object.
 * @author Zachary Ishmael
 *
 */
@JsonInclude(Include.NON_NULL)
public class PatientListResponse extends Response {

	private List<PatientResponse> patientList;
	
	public PatientListResponse (List<Patient> patients) {
		super();
		if (!patients.isEmpty()) {
			patientList = new ArrayList<PatientResponse>();
			for (Patient p : patients) {
				PatientResponse response = new PatientResponse(p);
				patientList.add(response);
			}
		}
	}
	
	
	
	public PatientListResponse() {
		super();
	}



	/**
	 * @return the patientList
	 */
	public List<PatientResponse> getPatientList() {
		return patientList;
	}



	/**
	 * @param patientList the patientList to set
	 */
	public void setPatientList(List<PatientResponse> patientList) {
		this.patientList = patientList;
	}


	public void setPatientListResponse(List<Patient> patients) {
		if (!patients.isEmpty()) {
			patientList = new ArrayList<PatientResponse>();
			for (Patient p : patients) {
				PatientResponse response = new PatientResponse(p);
				patientList.add(response);
			}
		}
	}
	/**
	 * 
	 * @return patientList size
	 */
	public int getListSize() {
		if (patientList == null || patientList.isEmpty() ) {
			return 0;
		}
		return patientList.size();
	}

	/**
	 * Inner class used as container for Patient information.
	 * @author Zachary Ishmael
	 *
	 */
	@JsonInclude(Include.NON_NULL)
	class PatientResponse {
		Integer id;
		String name;
		String username;
		String email;
		String dOB;//date of birth
		String mainCondition;
		
		PatientResponse() {
			id = null;
			name = null;
			username = null;
			email = null;
			dOB = null;
			mainCondition = null;
		}
		
		PatientResponse(Integer id, String name, String username, String email, String dOB, String mainCondition) {
			this.id = id;
			this.name = name;
			this.username = username;
			this.email = email;
			this.dOB = dOB;
			this.mainCondition = mainCondition;
		}
		
		PatientResponse(Patient p) {
			this.id = p.getId();
			this.name = p.getName();
			this.username = p.getUsername();
			this.email = p.getEmail();
			this.dOB = p.getdOB();
			this.mainCondition = p.getMainCondition();
		}

		
		/**
		 * @return the id
		 */
		public Integer getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(Integer id) {
			this.id = id;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the username
		 */
		public String getUsername() {
			return username;
		}

		/**
		 * @param username the username to set
		 */
		public void setUsername(String username) {
			this.username = username;
		}

		/**
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * @param email the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}

		/**
		 * @return the dOB
		 */
		public String getdOB() {
			return dOB;
		}

		/**
		 * @param dOB the dOB to set
		 */
		public void setdOB(String dOB) {
			this.dOB = dOB;
		}

		/**
		 * @return the mainCondition
		 */
		public String getMainCondition() {
			return mainCondition;
		}

		/**
		 * @param mainCondition the mainCondition to set
		 */
		public void setMainCondition(String mainCondition) {
			this.mainCondition = mainCondition;
		}
		
		
	}
}


