package app.PatientHealthApp.domain.objects.surgery;

import java.util.ArrayList;
import java.util.List;

import app.PatientHealthApp.domain.users.Doctor;

/**
 * Class to represent AppointmentSlots.
 * Each appointment slot in the Surgery Schedule
 * should have a list of available doctors during 
 * that specific time.
 * @author Zachary Ishmael
 *
 */
public class AppointmentSlot {
	
	private List<Doctor> availableDoctors;
	
	public AppointmentSlot () {
		setAvailableDoctors(new ArrayList<Doctor>());
	}

	public List<Doctor> getAvailableDoctors() {
		return availableDoctors;
	}

	public void setAvailableDoctors(List<Doctor> availableDoctors) {
		this.availableDoctors = availableDoctors;
	}
	
}
