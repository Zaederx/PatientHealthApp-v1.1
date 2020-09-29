package app.PatientHealthApp.domain.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Class used to map user information to credentials.
 * Used for Google Oauth2 login.
 * @author Zachary Ishmael
 *
 */

@Entity
public class OAuthUser {

	@Id
	private String email;
	@Column
	private String credential;
	
	@OneToOne
	private User user;
	
	public OAuthUser () {
		
	}
	public OAuthUser(String email, String credential, User user) {
		this.email = email;
		this.user = user;
	}

	/**
	 * @return the id
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the id to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
}
