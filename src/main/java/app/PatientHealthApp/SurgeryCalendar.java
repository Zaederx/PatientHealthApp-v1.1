package app.PatientHealthApp;

import java.util.HashMap;

/**
 * Represent a surgeries schedule.
 * @author Zachary Ishmael
 *
 */
public class SurgeryCalendar {
	
	private HashMap<String, SurgeryDay[]> months = new HashMap<String, SurgeryDay[]>();
	//31 day months
	private SurgeryDay[] January = new SurgeryDay[31];
	private SurgeryDay[] March = new SurgeryDay[31];
	private SurgeryDay[] May = new SurgeryDay[31];
	private SurgeryDay[] July = new SurgeryDay[31];
	private SurgeryDay[] August = new SurgeryDay[31];
	private SurgeryDay[] October = new SurgeryDay[31];
	private SurgeryDay[] December = new SurgeryDay[31];
	
	//30 day months
	private SurgeryDay[] April = new SurgeryDay[31];
	private SurgeryDay[] June = new SurgeryDay[31];
	private SurgeryDay[] September = new SurgeryDay[31];
	private SurgeryDay[] November = new SurgeryDay[31];
	
	// February
	private SurgeryDay[] February = new SurgeryDay[28];
	
	public SurgeryCalendar (boolean isLeapYear) {
		if (isLeapYear) {
			February = new SurgeryDay[29];
		}
		months.put("January", January);
		months.put("February", February);
		months.put("March", March);
		months.put("April", April);
		months.put("May", May);
		months.put("June", June);
		months.put("July", July);
		months.put("August", August);
		months.put("September", September);
		months.put("October", October);
		months.put("November", November);
		months.put("December", December);
	}

	/**
	 * @return the months
	 */
	public HashMap<String, SurgeryDay[]> getMonths() {
		return months;
	}

	/**
	 * @param months the months to set
	 */
	public void setMonths(HashMap<String, SurgeryDay[]> months) {
		this.months = months;
	}
	
	
}
