package app.PatientHealthApp.jsonObject.response.surgery;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.api.services.calendar.model.Event;

import app.PatientHealthApp.jsonObject.response.Response;

@JsonInclude(Include.NON_NULL)
public class GoogleEventResponse extends Response {
	
	private Integer listSize;
	private List<Event> appointments;
	
	
	public GoogleEventResponse() {
		super();
	}
	
	public GoogleEventResponse(List<Event> appointments) {
		super();
		this.appointments = appointments;
		countList();
	}

	
	@JsonIgnore
	public void countList() {
		if (appointments != null) {
			listSize = appointments.size();
		}
	}

	/**
	 * @return the count
	 */
	public Integer getListSize() {
		countList();
		return listSize;
	}


	/**
	 * @return the appointments
	 */
	public List<Event> getAppointments() {
		return appointments;
	}

	/**
	 * @param appointments the appointments to set
	 */
	public void setAppointments(List<Event> appointments) {
		this.appointments = appointments;
		countList();
	}
	
	
}
