package app.PatientHealthApp.jsonObject.response.surgery;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import app.PatientHealthApp.domain.users.User;
import app.PatientHealthApp.jsonObject.response.Response;

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
