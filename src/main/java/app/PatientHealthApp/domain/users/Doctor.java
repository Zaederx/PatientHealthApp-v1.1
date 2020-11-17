package app.PatientHealthApp.domain.users;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;

import app.PatientHealthApp.domain.objects.surgery.Appointment;
import app.PatientHealthApp.formObjects.DoctorRegForm;
/**
 * Doctor {@link Entity} class that defines the doctor table.
 * 
 * @author Zachary Ishmael
 *
 */
@Entity(name= "doctor")
public class Doctor extends User {

	@JsonIgnore//causes unterminating string otherwise
	@Column
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "PatientsToDoctors", joinColumns=
	@JoinColumn ( name = "doctor_id"),
	inverseJoinColumns=@JoinColumn(name ="patient_id" ))
	private List<Patient> patients;
	@Column
	String specialisation;
	
	@Column(unique = true)
	String gmc;
	@OneToMany
	private List<Appointment> appointments;

	
	public Doctor() {
		this.role = "DOCTOR";
	}
	
	public Doctor (String name, String username, String email, String password, String gmc, String specialisation) {
		super(name,username, email, password,"DOCTOR");
		this.gmc = gmc;
		this.specialisation = specialisation;
	}
	
	public Doctor(DoctorRegForm form) {
		super(form.getName(),form.getUsername(), form.getEmail(), form.getPassword(),"DOCTOR");
		this.gmc = form.getGmcNum();
		this.specialisation = form.getSpecialisation();
	}
	
	public List<Patient> getPatients() {
		return patients;
	}
	
	public void setPatients(List<Patient>patients) {
		this.patients = patients;
	}

	/**
	 * @return the specialty
	 */
	public String getSpecialisation() {
		return specialisation;
	}

	/**
	 * @param specialty the specialty to set
	 */
	public void setSpecialisation(String specialty) {
		this.specialisation = specialty;
	}
	
}
