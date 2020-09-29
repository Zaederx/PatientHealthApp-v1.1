package app.PatientHealthApp.domain.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import app.PatientHealthApp.domain.objects.Message;

/**
 * Class to represent users.
 * All other user types extend from this class.
 * Enables jpa/hibernate to perform repository 
 * operations on interchangable user objects.
 * 
 * Important for use of Spring Security login conventions.
 * @author Zachary Ishmael
 *
 */
@Entity(name = "User")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	@Column(nullable = false)
	protected String name;
	@Column(unique = true, nullable = false)
	protected String username;
	
	@Column(unique = true, nullable = false)
	protected String email;
	
	@JsonIgnore//to ensure password isn't sent in json responses
	@Column(nullable = false)
	protected String password;
	@Column(nullable = false)
	protected String role;
	@JsonIgnore
	@OneToMany(mappedBy = "sender")
	protected List<Message> messagesSent;
	@JsonIgnore
	@ManyToMany(mappedBy = "recievers")
	protected List<Message> messagesRecieved;
	
	@Column
	boolean OAuth2Enabled;
	@Column
	String pictureURL;
	@JsonIgnore
	@Transient
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	
	public User () {
		
	}
	
	public User(String name, String username, String email, String password, String role ) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = encoder.encode(password);
		this.role = role;
		this.messagesSent = new ArrayList<Message>();
		this.OAuth2Enabled = true;//for demonstration purposes - but should in reality be set to false as a default
		this.pictureURL = "";
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
		this.password = encoder.encode(password);
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	
	/**
	 * @return the messagesSent
	 */
	public List<Message> getMessagesSent() {
		return messagesSent;
	}

	/**
	 * @param messagesSent the messagesSent to set
	 */
	public void setMessagesSent(List<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}

	/**
	 * @return the messagesRecieved
	 */
	public List<Message> getMessagesRecieved() {
		return messagesRecieved;
	}
	
	/*For Information retrieved from Google via OAuth2*/

	/**
	 * @return the oAuth2Enabled
	 */
	public boolean isOAuth2Enabled() {
		return OAuth2Enabled;
	}

	/**
	 * @param oAuth2Enabled the oAuth2Enabled to set
	 */
	public void setOAuth2Enabled(boolean oAuth2Enabled) {
		OAuth2Enabled = oAuth2Enabled;
	}

	/**
	 * @return the pictureURL
	 */
	public String getPictureURL() {
		return pictureURL;
	}

	/**
	 * @param pictureURL the pictureURL to set
	 */
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	/**
	 * @param messagesRecieved the messagesRecieved to set
	 */
	public void setMessagesRecieved(List<Message> messagesRecieved) {
		this.messagesRecieved = messagesRecieved;
	}

	@Override
	public String toString() {
		String s = "";
		s +=  "Name " +this.name + "Id: "+ this.id + " Username: " +this.username + " Role: " +this.role + "\n";
		return s;
	}
	
}
