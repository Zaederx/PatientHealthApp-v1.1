package app.PatientHealthApp.domain.objects.surgery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @apiNote
 * Class used to represent medical drugs.<br>
 * 
 * Contains medication name, description of what it is used to treat
 * and manufacturer's dosage instructions.<br>
 * 
 * This class also keeps a list of all other medication names that
 * have been initailised or added.
 * 
 * @author Zachary Ishmael<br>
 *
 */
@Entity(name = "Medication")
public class Medication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	private static String[] medList;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "Dosage_Instructions")
	private String dosageInstructions;//manufacturer's dosge instrcutions
	@Column
	boolean controlDrug;
	/*
	 *  An array of descriptive tags
	 *  - e.g. pain relief, anti-inflamatory, anti-histamine, etc.
	 */


	public Medication () {
		controlDrug = false;
	}
	
	public Medication (String name, String description, String dosageInstructions) {
		this.name = name;
		this.description = description;
		this.dosageInstructions = dosageInstructions;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the dosageInstructions
	 */
	public String getDosageInstructions() {
		return dosageInstructions;
	}

	/**
	 * @param dosageInstructions the dosageInstructions to set
	 */
	public void setDosageInstructions(String dosageInstructions) {
		this.dosageInstructions = dosageInstructions;
	}

	/**
	 * @return the controlDrug
	 */
	public boolean isControlDrug() {
		return controlDrug;
	}

	/**
	 * @param controlDrug the controlDrug to set
	 */
	public void setControlDrug(boolean controlDrug) {
		this.controlDrug = controlDrug;
	}


	
	
	
}
