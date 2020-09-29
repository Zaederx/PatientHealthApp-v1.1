package app.PatientHealthApp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

/**
 * Class for the dashboard for Admin Staff.
 * @author Zachary Ishmael
 *
 */
@Entity
@Component
public class AdminDashboard {
	@Id
	private int id;

	@Column
	String upcomingAppointments;
	
	@Column
	String surgeryAddress;
	
	public AdminDashboard() {
		upcomingAppointments = "";
		surgeryAddress = "University of Leicester\n" + 
							"University Road\n" + 
							"Leicester\n" + 
							"LE1 7RH\n" + 
							"United Kingdom";
	}

	/**
	 * @return the upcomingAppointments
	 */
	public String getUpcomingAppointments() {
		return upcomingAppointments;
	}

	/**
	 * @param upcomingAppointments the upcomingAppointments to set
	 */
	public void setUpcomingAppointments(String upcomingAppointments) {
		this.upcomingAppointments = upcomingAppointments;
	}

	/**
	 * @return the surgeryAddress
	 */
	public String getSurgeryAddress() {
		return surgeryAddress;
	}

	/**
	 * @param surgeryAddress the surgeryAddress to set
	 */
	public void setSurgeryAddress(String surgeryAddress) {
		this.surgeryAddress = surgeryAddress;
	}

	
}
