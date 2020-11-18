package app.PatientHealthApp.jsonObject.response.patient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import app.PatientHealthApp.domain.users.Patient;
import app.PatientHealthApp.jsonObject.response.Response;

@JsonInclude(Include.NON_NULL)
public class IndividualPatientResponse extends Response{

	private String name;
	private String email;
	private String phoneNumber;
	private String mainCondition;
	
	
	public IndividualPatientResponse() {
		name = null;
		email = null;
		phoneNumber = null;
		mainCondition = null;
	}

	public IndividualPatientResponse(Patient patient) {
		name = patient.getName();
		email = patient.getEmail();
		phoneNumber = patient.getPhoneNumber();
		mainCondition = patient.getMainCondition();
	}
	
	public IndividualPatientResponse(String name, String email, String phoneNumber, String mainCondition) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.mainCondition = mainCondition;
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
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}


	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
