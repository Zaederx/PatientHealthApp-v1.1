package app.PatientHealthApp.addressEnums;

public enum DoctorAddressBook {
	
	D_HOME("doctor/home","doctor/doctor-home"),
	D_PATIENTS("doctor/patients","doctor/doctor-patients");
	
	String address;
	String resource;

	DoctorAddressBook (String address, String resource) {
		this.address = address;
		this.resource = resource;
	}
	
	/**
	 * @return the address
	 */
	public String controller() {
		return address;
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
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}
}
