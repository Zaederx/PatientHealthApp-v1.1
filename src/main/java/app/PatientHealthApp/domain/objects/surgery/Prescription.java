package app.PatientHealthApp.domain.objects.surgery;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 * 
 * Entity to represent doctor's prescription to a patient.
 * 
 * Each prescription has instructions to the patient
 * and references a medication in the Medication entity.
 * @author Zachary Ishmael
 */

@Entity(name="prescription")
public class Prescription {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="directions", nullable = false)
	private String directions;
	
	@ManyToOne
	@JoinColumn(name="medication", referencedColumnName = "id")
	//@JoinColumn - allows for naming of this column and 
	//explicit reference data to be referenece  in this column from another tables column
	private Medication medication;
	
	
	public Prescription() {
		
	}
	
	public Prescription(String directions) {
		this.directions = directions;
	}
	
	public Prescription(Medication medication) {
		this.medication = medication;
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
	 * @return the directions
	 */
	public String getDirections() {
		return directions;
	}

	/**
	 * @param directions the directions to set
	 */
	public void setDirections(String directions) {
		this.directions = directions;
	}

	/**
	 * @return the medication
	 */
	public Medication getMedication() {
		return medication;
	}

	/**
	 * @param medication the medication to set
	 */
	public void setMedication(Medication medication) {
		this.medication = medication;
	}
	
	

}
