package app.PatientHealthApp.formObjects;

public class DoctorRegForm implements UserRegFormInterface {
	private String name;
	
	private String username;
	
	private String email;
	
	private String password;
	
	private String password2;
	
	private String specialisation;
	
	/*General Medical Council Number
	 * i.e. license number*/
	private String gmcNum;
	
//	/*Which medical practice/centre they will be going to.*/
//	private String assignedCentre;//TODO - Might be a good idea??
	
	
	
	public DoctorRegForm() {
		
	}

	/**
	 * @return the username
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the email
	 */
	@Override
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the password2
	 */
	@Override
	public String getPassword2() {
		return password2;
	}

	/**
	 * @param password2 the password2 to set
	 */
	@Override
	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	@Override
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
	public String getGmcNum() {
		return gmcNum;
	}

	/**
	 * @param gmcNum the medicalLicenseCode to set
	 */
	public void setGmcNum(String gmcNum) {
		this.gmcNum = gmcNum;
	}




	//TODO - Assiged Center for doctors - Might be a good idea???
//	/**
//	 * @return the assignedCentre
//	 */
//	public String getAssignedCentre() {
//		return assignedCentre;
//	}
//
//	/**
//	 * @param assignedCentre the assignedCentre to set
//	 */
//	public void setAssignedCentre(String assignedCentre) {
//		this.assignedCentre = assignedCentre;
//	}

	
	
}
