package app.PatientHealthApp.domain.objects.surgery;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import app.PatientHealthApp.domain.users.Doctor;
import app.PatientHealthApp.domain.users.Patient;
import app.PatientHealthApp.formObjects.AppointmentRequestForm;

/**
 * Class used to create AppointmentRequest.
 * @author Zachary Ishmael
 *
 */
@Entity(name="appointment_request")
public class AppointmentRequest {
	
	enum Session {
		morning (true),
		afternoon(false);
		
		boolean s;
		Session (boolean s) {
			this.s = s;
		}
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	/**
	 * The appointment request type.
	 */
	private int appointmentType;
	
	/* The patient who has made the request.
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	private Patient patient;

	/**
	 * The doctor the request in intended for.
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	private Doctor doctor;
	
//	/*3 best dates for an appointment
//	 */
//	@Column
//	private String date;
	
	/* To hold 3 requested sessions to correspond with the dates.
	 * Sessions can be morning or afternoon.
	 * True = morning, False = afternoon.
	 */
	@Column
	private Boolean session;
	
	/* Times the surgery is available
	 */
	@Transient
	private String[] surgeryTimes = {"9:00","9:30","10:00","10:30", "11:00","11:30", "12:00", "12:30",
			"13:00","13:30", "14:00", "14:30", "15:00","15:30", "16:00", "16:30",
			"17:00", "17:30", "18:00"};
	
	/*What help the person needs in regard to the appointment.
	 * i.e. reasons they booked the appointment.
	 */
	@Column
	private String description;
	
	/*A short summary of the description.
	 */
	@Column
	private String summary;
	
	/*
	 * Doctor / Admin could determine what is of highest priority.
	 * 1 being top priority, 3 being lowest.
	 */
	@JsonIgnore
	@Column
	private int priority;
	
	
	public AppointmentRequest(Patient patient, String description, String summary, int appointmentType, Boolean session) {
		this.patient = patient;
		this.doctor = patient.getPrimaryDoctor();//assume first is Primary Doctor - TODO could one doctor as your primary one - AHVE SPECIAL FIELD FOR IT ON PATIENT?
		this.description = description;
		this.summary = summary;
		this.appointmentType = appointmentType;
//		this.date = date;//TODO - REMOVE IF NO LONGER TO BE IMPLEMENTED
		this.session = session;
	}
	



	public AppointmentRequest(AppointmentRequestForm response) {
		this.summary = response.getSummary();
		this.description = response.getDescription();
		this.appointmentType = response.getAppointmentType();
//		this.date = response.getDate();//TODO - REMOVE IF NO LONGER TO BE IMPLEMENTED
		this.session = response.getSession();
	}


	public AppointmentRequest() {
		
	}


	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}




	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}




	/**
	 * @return the patient
	 */
	@JsonIgnore
	public Patient getPatient() {
		return patient;
	}


	/**
	 * @param patient the patient to set
	 */
	@JsonIgnore
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	

//TODO - REMOVE IF NO LONGER TO BE IMPLEMENTED
//	/**
//	 * @return the date
//	 */
//	public String getDate() {
//		return date;
//	}
//
//
//	/**
//	 * @param dates the date to set
//	 */
//	public void setDates(String date) {
//		this.date = date;
//	}

	

	/**
	 * @return the doctor
	 */
	@JsonIgnore
	public Doctor getDoctor() {
		return doctor;
	}




	/**
	 * @param doctor the doctor to set
	 */
	@JsonIgnore
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}




	/**
	 * @return the session
	 */
	public Boolean getSession() {
		return session;
	}


	/**
	 * @return the appointmentType
	 */
	public int getAppointmentType() {
		return appointmentType;
	}




	/**
	 * @param appointmentType the appointmentType to set
	 */
	public void setAppointmentType(int appointmentType) {
		this.appointmentType = appointmentType;
	}




	/**
	 * @param session the session to set
	 */
	public void setSession(Boolean session) {
		this.session = session;
	}


	/**
	 * @return the surgeryTimes
	 */
	@JsonIgnore
	public String[] getSurgeryTimes() {
		return surgeryTimes;
	}


	/**
	 * @param surgeryTimes the surgeryTimes to set
	 */
	@JsonIgnore
	public void setSurgeryTimes(String[] surgeryTimes) {
		this.surgeryTimes = surgeryTimes;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}


	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}


	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}


	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	
	
}
