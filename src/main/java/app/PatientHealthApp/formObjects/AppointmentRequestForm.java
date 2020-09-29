package app.PatientHealthApp.formObjects;



import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Form object for receiving Appointment Request information.
 * @author Zachary Ishmael
 *
 */
public class AppointmentRequestForm {
	
	private int appointmentType;
	
	private String summary;
	
	private String description;
	
	private Boolean session;
	
	public enum AppointmentType {
		Check_Up(1),
		Medical_Question(2),
		Medical_Concern(3),
		Urgent(4),
		Travel_Related(5),
		Prescription_Review(6),
		Mental_Health(7),
		Other(8);
		
		private int value;
		
		AppointmentType(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
		
	}
	
	

	public AppointmentRequestForm() {
		
	}

	/**
	 * @return the appointmentType
	 */
	public int getAppointmentType() {
		return appointmentType;
	}



	/**
	 * @param appointmentType the appointmentType to set
	 */
	public void setAppointmentType(int appointmentType) {
		this.appointmentType = appointmentType;
	}



	/**
	 * @return the session
	 */
	public Boolean getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */@JsonProperty("session")
	public void setSession(String session) {
		 if (session.equals("true")) {
			 this.session = true;
		 } else {
			 this.session = false;
		 }
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
}
