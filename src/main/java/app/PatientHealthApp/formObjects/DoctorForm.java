package app.PatientHealthApp.formObjects;

public class DoctorForm {
	private String name;
	
	private String username;
	
	private String password;
	
	private String specialisation;
	
	private String medicalLicenseCode;
	
	/*Which medical practice/centre they will be going to.*/
	private String assignedCentre;
	
	
	
	public DoctorForm() {
		
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the specialisation
	 */
	public String getSpecialisation() {
		return specialisation;
	}

	/**
	 * @param specialisation the specialisation to set
	 */
	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}

	/**
	 * @return the medicalLicenseCode
	 */
	public String getMedicalLicenseCode() {
		return medicalLicenseCode;
	}

	/**
	 * @param medicalLicenseCode the medicalLicenseCode to set
	 */
	public void setMedicalLicenseCode(String medicalLicenseCode) {
		this.medicalLicenseCode = medicalLicenseCode;
	}

	/**
	 * @return the assignedCentre
	 */
	public String getAssignedCentre() {
		return assignedCentre;
	}

	/**
	 * @param assignedCentre the assignedCentre to set
	 */
	public void setAssignedCentre(String assignedCentre) {
		this.assignedCentre = assignedCentre;
	}

	
	
}
