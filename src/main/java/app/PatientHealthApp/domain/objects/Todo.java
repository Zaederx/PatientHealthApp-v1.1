package app.PatientHealthApp.domain.objects;

import java.util.Date;

import com.google.api.client.util.DateTime;

public class Todo {

	private String title;
	
	private String description;
	
	private DateTime due;
	
	public Todo() {
		title = "Untitled";
		description = "";
		due = new DateTime(new Date());
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the due
	 */
	public DateTime getDue() {
		return due;
	}

	/**
	 * @param due the due to set
	 */
	public void setDue(DateTime due) {
		this.due = due;
	}
	
	
}
