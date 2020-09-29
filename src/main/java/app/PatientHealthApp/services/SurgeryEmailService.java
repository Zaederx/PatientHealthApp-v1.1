package app.PatientHealthApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service class for sending surgery emails.
 * Contains templates for emailing patient 
 * their appointment information and for 
 * email confirmation.
 * @author Zachary Ishmael
 *
 */


@Service("surgeryEmailService")
public class SurgeryEmailService {
	@Autowired
	public JavaMailSender sender;
	
	String confirmationTemplate;
	
	String appointmentTemplate;
	public void sendMessage(String to, String from, String messageBody) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setFrom(from);
		message.setText(messageBody);
		sender.send(message);
	}
}
