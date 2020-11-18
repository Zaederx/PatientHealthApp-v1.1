package app.PatientHealthApp.jsonObject.response.surgery;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * Event response object.
 * Used to return events to admin dashboard.
 * Converted to json via Spring Jackson support.
 * @author Zachary Ishmael
 *
 */
@JsonInclude(Include.NON_NULL)
public class EventResponse {

	private String appointments;
	
	
	public EventResponse(String response) {
		appointments = response;
	}


	/**
	 * @return the appointments
	 */
	public String getAppointments() {
		return appointments;
	}


	/**
	 * @param appointments the appointments to set
	 */
	public void setAppointments(String appointments) {
		this.appointments = appointments;
	}
	
	
	
}
