package app.PatientHealthApp.jsonObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import app.PatientHealthApp.domain.users.User;

/**
 * Json Response Object for returning users.
 * @author Zachary Ishmael
 *
 */
@JsonInclude(Include.NON_NULL)
public class UserResponse extends Response {
	
	private User user;
	public UserResponse() {
		super();
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
