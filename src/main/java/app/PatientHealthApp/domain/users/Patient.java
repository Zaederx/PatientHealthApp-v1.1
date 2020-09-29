package app.PatientHealthApp.domain.users;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import app.PatientHealthApp.domain.objects.Appointment;
import app.PatientHealthApp.domain.objects.AppointmentRequest;
import app.PatientHealthApp.domain.objects.Prescription;
import app.PatientHealthApp.formObjects.PatientRegForm;

/**
 * An {@link Entity} class used to represent patients with chronic illness.
 * @author Zachary Ishmael<br>
 */

@Entity(name = "patient")
public class Patient extends User{
	
	@JsonIgnore//causes unterminating string otherwise
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "patients")
	private List<Doctor> doctors;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Prescription> prescriptions;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	private List<AppointmentRequest> appointmentRequests;
	
	@OneToMany
	private List<Appointment> appointments;
	
	@Column
	private String dOB;//TODO Birthday/DOB field Patient - AND FORM OBJECT UPDATE + CONSTRUCTOR
	
	@Column
	private String mainCondition;
	
	@Column
	private String phoneNumber;
	
	@OneToOne
	private Doctor primaryDoctor;
	
	//default constructor - needed by Spring
	public Patient() {
		this.role = "PATIENT";
		this.dOB  = "NOT SPECIFIED";
		this.mainCondition = "NOT SPECIFIED";
		this.phoneNumber = "NOT SPECIFIED";
	}
	
	public Patient(String name, String username, String email, String password, String role, String dOB) {
		super(name,username, email, password,"PATIENT");
		this.mainCondition = "NOT SPECIFIED";
		this.dOB = dOB;
		this.phoneNumber = "NOT SPECIFIED";
		
	}

	public Patient(PatientRegForm form) {
		super(form.getName(), form.getEmail(), form.getUsername(),form.getPassword(),"PATIENT");
		this.email = form.getEmail();
//		this.dOB = TODO - ADD bithday field in form - admin register patients
		this.mainCondition = "Not Specified";//TODO - UPDATE admin patient registry form
	}

	/**
	 * @return the doctors
	 */
	public List<Doctor> getDoctors() {
		return doctors;
	}

	/**
	 * @param doctors the doctors to set
	 */
	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
		if (doctors.size() == 1) {
			primaryDoctor = doctors.get(0);
		}
	}

	/**
	 * @return the prescriptions
	 */
	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	/**
	 * @param prescriptions the prescriptions to set
	 */
	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	/**
	 * @return the dOB
	 */
	public String getdOB() {
		return dOB;
	}

	/**
	 * @param dOB the dOB to set
	 */
	public void setdOB(String dOB) {
		this.dOB = dOB;
	}

	
	/**
	 * @return the mainCondition
	 */
	public String getMainCondition() {
		return mainCondition;
	}

	/**
	 * @param mainCondition the mainCondition to set
	 */
	public void setMainCondition(String mainCondition) {
		this.mainCondition = mainCondition;
	}
	
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the appointmentRequests
	 */
	public List<AppointmentRequest> getAppointmentRequests() {
		return appointmentRequests;
	}

	/**
	 * @param appointmentRequests the appointmentRequests to set
	 */
	public void setAppointmentRequests(List<AppointmentRequest> requests) {
		this.appointmentRequests = requests;
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

	/**
	 * @return the primaryDoctor
	 */
	public Doctor getPrimaryDoctor() {
		return primaryDoctor;
	}

	/**
	 * @param primaryDoctor the primaryDoctor to set
	 */
	public void setPrimaryDoctor(Doctor primaryDoctor) {
		this.primaryDoctor = primaryDoctor;
	}
	
	

	
	
}
