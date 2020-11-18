package app.PatientHealthApp.jsonObject.response.surgery;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

import app.PatientHealthApp.domain.users.User;
import app.PatientHealthApp.jsonObject.response.Response;


/**
 * An Object to represent a List of Users
 * @author Zachary Ishmael
 *
 */
@JsonInclude(Include.NON_NULL)
public class UList extends Response{
	
	private List<?> users;
	private boolean response;
	private int count;
	
	public UList() {
		super();
		response = false;
		users = new ArrayList();
		count = 0;
	}

	/**
	 * @return the users
	 */
	public List<?> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<?> users) {
		this.users = users;
	}

	/**
	 * @return the response
	 */
	@JsonProperty("response")
	public boolean isResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	@JsonProperty("response")
	public void setResponse(boolean response) {
		this.response = response;
	}

	/**
	 * @return the count
	 */
	@JsonProperty("count")
	public Integer getCount() {
		if (users != null) {
		count = users.size();
		}
		return count;
	}

	/**
	 * @param count the count to set
	 */
	@JsonProperty("count")
	public void setCount(int count) {
		this.count = count;
	}
	
}
