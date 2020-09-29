package app.PatientHealthApp.jsonObject;

import java.util.List;

import app.PatientHealthApp.domain.objects.Appointment;

public class AppointmentListResponse extends Response{

	private List<Appointment> appointments;
	
	public AppointmentListResponse() {
		super();
	}

	/**
	 * @return the appointments
	 */
	public List<Appointment> getAppointments() {
		return appointments;
	}

	/**
	 * @param appointments the appointments to set
	 */
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	
	
}
