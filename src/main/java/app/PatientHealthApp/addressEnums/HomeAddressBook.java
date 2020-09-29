package app.PatientHealthApp.addressEnums;
/**
 * Used to keep track of urls.
 * Used as an authority on RequestMappings for
 * particular resources available under the 
 * "HOME" context.
 * @author Zachary Ishmael.
 *
 */
public enum HomeAddressBook {
	HOME("/home","/home"),
	LOGIN ("home/login","/login"),
	P_REGISTRATION("home/registration","/registration");
	
	String address;
	
	String resource;
	HomeAddressBook(String address, String resource) {
		this.address = address;
		this.resource = resource;
	}
	
	/**
	 * @return the address
	 */
	public String getAddress() {
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
