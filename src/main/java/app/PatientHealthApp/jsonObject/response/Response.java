package app.PatientHealthApp.jsonObject.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import app.PatientHealthApp.domain.users.User;

/**
 * Class used to transport ajax responses.
 * 
 * @author Zachary Ishmael
 *
 */
@JsonInclude(Include.NON_NULL)
public class Response {
	/*If Form has Errors 
	 * - response set to true*/
	private boolean response;
	private String message;

	private List<String> messages;
	/*Count of messages - needed for javascript message processing*/
	private Integer count;
//	private User user;
	private String link;
	
	public Response () {
		response = false;
		message = "";
		messages = null;
		count = null;
		link = null;
	}
	
	/**
	 * @return the response
	 */
	public boolean getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(boolean response) {
		this.response = response;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the errorMessage to set
	 */
	public void setMessage(String errorMessage) {
		this.message = errorMessage;
	}

	/**
	 * @return the messages
	 */
	public List<String> getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		count();
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	@JsonIgnore
	public void count() {
		if (messages != null) {
			count = messages.size();
		}
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
	
	
}
