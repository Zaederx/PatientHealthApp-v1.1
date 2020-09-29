package app.PatientHealthApp.addressEnums;

public enum AdminAddressBook {

	A_HOME("/home","/admin-home"),
	A_ADD_DOCTOR("/addDoctor","/admin-add-doctor"),
	A_REMOVE_DOCTOR("/removeDoctor","/admin-remove-doctor"),
	A_MODIFY_DOCTOR("/modifyDoctor","/admin-modify-doctor"),
	A_ADD_PATIENT("/addPatient","/admin-add-patient"),
	A_REMOVE_PATIENT("/removePatient", "/admin-remove-patient"),
	A_MODIFY_PATIENT("/modifyPatient", "/admin-modify-patient"),
	A_ADD_ADMIN("/addAdmin","/admin-add-admin"),
	A_REMOVE_ADMIN("/removeAdmin","/admin-remove-admin"),
	A_ADD_USER("/addUser","/admin-add-user");

	String address;
	String resource;
	String domain = "admin";

	AdminAddressBook (String address, String resource) {
		this.address = address;
		this.resource = resource;
	}
	
	/**
	 * @return the address
	 */
	public String controller() {
		return domain+address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the resource
	 */
	public String resource() {
		return domain+resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}
}
