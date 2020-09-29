package app.PatientHealthApp.domain.objects;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import app.PatientHealthApp.domain.users.User;

/**
 * An {@link Entity} class used to store user messages.
 * 
 * @author Zachary Ishmael
 *
 */
@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	
	@ManyToOne
	protected User sender;
	
	@ManyToMany
	protected List<User> recievers;
	
	@Column
	protected String title;
	
	@Column
	protected String body;
	
	public Message() {
	}

	
	public Message(User sender, List<User> reciever) {
		this.sender = sender;
		this.recievers = reciever;
	}

	public Message(User sender, List<User> reciever, String title, String body) {
		this.sender = sender;
		this.recievers = reciever;
		this.title = title;
		this.body = body;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the sender
	 */
	public User getSender() {
		return sender;
	}


	/**
	 * @param sender the sender to set
	 */
	public void setSender(User sender) {
		this.sender = sender;
	}


	/**
	 * @return the reciever
	 */
	public List<User> getRecievers() {
		return recievers;
	}


	/**
	 * @param reciever the reciever to set
	 */
	public void setRecievers(List<User> reciever) {
		this.recievers = reciever;
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
	 * @return the body
	 */
	public String getBody() {
		return body;
	}


	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
	
	

}
