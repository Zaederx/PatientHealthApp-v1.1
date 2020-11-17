package app.PatientHealthApp.domain.objects.surgery;

import java.beans.JavaBean;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SurgeryCalendarContainer {

	List<SurgeryCalendar> calendars = new ArrayList<SurgeryCalendar>();
	
	public SurgeryCalendarContainer () {
		
	}

	/**
	 * @return the cal
	 */
	public List<SurgeryCalendar> getCalendars() {
		return calendars;
	}

	/**
	 * @param calendars the cal to set
	 */
	public void setCalendars(List<SurgeryCalendar> calendars) {
		this.calendars = calendars;
	}
	
	/**
	 * Adds calendar to SurgeryClaendarContainer list.
	 * @param calendar to be added
	 */
	public void addCalendar(SurgeryCalendar calendar) {
		this.calendars.add(calendar);
	}
	
	/**
	 * Removes calendar from SurgeryClaendarContainer list.
	 * @param index of calendar to be removed.
	 */
	public void removeCalendar (int index) {
		this.calendars.remove(index);
	}
}
