package app.PatientHealthApp.domain.objects.doctor;

import java.util.HashMap;

/**
 * Class to represent days of the month.<br>
 * Each day has a set amount of time slots.
 * These time slots can be available or unavailable.
 * @author Zachary Ishmael
 *
 */
public class DoctorDay {

	//Array of Time Slots
	String[] times = {"9:00","9:30","10:00","10:30", "11:00","11:30", "12:00", "12:30",
						"13:00","13:30", "14:00", "14:30", "15:00","15:30", "16:00", "16:30",
						"17:00", "17:30", "18:00"};
	
	HashMap<String,Boolean> timeSlots = new HashMap<String,Boolean>();
	
	/**
	 * Default Constructor.<br>
	 * Fills Day timeSlots with available 30 min time slot from 9am to 6pm.
	 */
	public DoctorDay () {
		for (String time : times)
			timeSlots.put(time, true);
	}
	
	/**
	 * Custom TimeSlot constructor.
	 * Allows one to define different time slots that the default.
	 * @param times - String[] of times i.e. {"12:00","12;30"....}
	 */
	public DoctorDay(String[] times) {
		for (String time : times)
			timeSlots.put(time, true);
	}
	
	
}
