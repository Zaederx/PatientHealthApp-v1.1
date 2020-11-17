package app.PatientHealthApp.domain.objects.surgery;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ManyToMany;

import org.springframework.beans.factory.annotation.Autowired;

import app.PatientHealthApp.domain.users.Admin;
import app.PatientHealthApp.domain.users.User;
import app.PatientHealthApp.services.UserServiceDetailsImpl;
/**
 * Specific implementation of Message class.
 * Used for having request messages available for all
 * Admin staff to view.
 * @author Zachary Ishmael
 *
 */
public class AppointmentRequestMessage extends Message {

	@Autowired
	UserServiceDetailsImpl userServices;
	
	@ManyToMany
	List<Admin> recievers;
	
	public AppointmentRequestMessage(User sender, String title, String body) {
		super();
		this.sender = sender;
		this.title = title;
		this.body = body;
		List<Admin> adminUsers = new ArrayList<Admin>();
		adminUsers = userServices.getAdminRepo().findAll();
		
		this.recievers = adminUsers;
	}
}
