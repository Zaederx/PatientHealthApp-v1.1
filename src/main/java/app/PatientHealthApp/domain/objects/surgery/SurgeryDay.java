package app.PatientHealthApp.domain.objects.surgery;

import java.util.HashMap;

/**
 * Represents a surgery's day.
 * 
 * @author Zachary Ishmael
 *
 */
public class SurgeryDay {

	//Array of Time Slots
	String[] times = {"9:00","9:30","10:00","10:30", "11:00","11:30", "12:00", "12:30",
							"13:00","13:30", "14:00", "14:30", "15:00","15:30", "16:00", "16:30",
							"17:00", "17:30", "18:00"};
	
	HashMap<String, AppointmentSlot> appointments = new HashMap<String, AppointmentSlot>();

	
	public SurgeryDay () {
		for (String time : times)
			appointments.put(time, new AppointmentSlot());
	}
	
	public SurgeryDay (String[] times) {
		for (String time : times)
			appointments.put(time, new AppointmentSlot());
	}
	
	/**
	 * @return the appointments
	 */
	public HashMap<String, AppointmentSlot> getAppointments() {
		return appointments;
	}

	/**
	 * @param appointments the appointments to set
	 */
	public void setAppointments(HashMap<String, AppointmentSlot> appointments) {
		this.appointments = appointments;
	}
	
	
	



}
