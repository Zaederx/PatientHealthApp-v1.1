package app.PatientHealthApp.addressEnums;

public enum PAddressBook {
	
	P_HOME("patient/home","patient/patient-home"),
	P_PRESCRIPTIONS("patient/prescriptions","patient/patient-prescriptions"),
	P_DOCTORS("patient/doctors","patient/patient-doctors"),
	P_APPOINTMENTS("patient/appointments","patient/patient-appointments"),
	P_ASSISTANCE("/patient/assistance","/patient/patient-assistance");
	
	
	String address;
	
	String resource;
	
	PAddressBook(String address, String resource) {
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
