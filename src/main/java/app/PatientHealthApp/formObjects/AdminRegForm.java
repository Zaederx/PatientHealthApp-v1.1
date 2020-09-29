package app.PatientHealthApp.formObjects;
/**
 * DTO for Admin registration.
 * @author Zachary Ishmael
 *
 */
public class AdminRegForm implements UserRegFormInterface {
	
	private String name;
	
	private String username;
	
	private String password;
	
	private String password2;

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
	 * @return the password2
	 */
	public String getPassword2() {
		return password2;
	}

	/**
	 * @param password2 the password2 to set
	 */
	public void setPassword2(String password2) {
		this.password2 = password2;
	}


	@Override
	public String getEmail() {
		return "dummy_email@email.com";
	}

	@Override
	public void setEmail(String email) {
	}
	
	
}
