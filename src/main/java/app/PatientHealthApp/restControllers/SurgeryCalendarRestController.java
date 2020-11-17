package app.PatientHealthApp.restControllers;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.api.services.calendar.model.Events;

import app.PatientHealthApp.domain.objects.admin.AdminDashboard;
import app.PatientHealthApp.domain.objects.surgery.SurgeryGoogleCalendarAPI;
import app.PatientHealthApp.domain.objects.surgery.Appointment;
import app.PatientHealthApp.domain.users.Patient;
import app.PatientHealthApp.domain.users.User;
import app.PatientHealthApp.jsonObject.AppointmentListResponse;
import app.PatientHealthApp.jsonObject.EventResponse;
import app.PatientHealthApp.jsonObject.GoogleEventResponse;
import app.PatientHealthApp.jsonObject.Response;
import app.PatientHealthApp.services.UserServiceDetailsImpl;


/**
 * RestController to allow admin to have dashboard functionality.
 * Google calendar related code is based on Google Calendar API Guide demonstration 
 * {@linkplain https://developers.google.com/calendar/create-events?hl=en_US#add_an_event }
 * @author Zachary Ishmael
 * 
 */
@RestController
@RequestMapping("calendar/")
public class SurgeryCalendarRestController {
	
//	@Autowired
//	SurgeryGoogleClaendarAPI cal;
	@Autowired
	AdminDashboard dashboard;
	@Autowired
	UserServiceDetailsImpl userServices;
	
//	@GetMapping("events")
//	public Object getUpcomingEvents() {
//		dashboard.getUpcomingAppointments();
//		return new EventResponse(dashboard.getUpcomingAppointments());
//	}
	
	@GetMapping("test")
	public Object test() {
		Response res = new Response();
		res.setResponse(true);
		res.setMessage("Works");
		return res;
	}
	
	
	@GetMapping("patient/appointments")
	public Object getPatientAppointments() {
		String username = userServices.getUsername();
		Patient patient = userServices.getPatientRepo().findByUsername(username);
		
		List<Appointment> appointments =   patient.getAppointments();
		AppointmentListResponse res = new AppointmentListResponse();
		if (appointments != null && !appointments.isEmpty()) {
			res.setAppointments(appointments);
			res.setResponse(true);
		}
		else {
			res.setMessage("No appointments.");
		}
		
		return res;
	}
	/**
	 * Returns a list of the patients appointments
	 * @return
	 * @throws IOException
	 */
	@GetMapping(path = {"patient/events/google","patient/events/google/{patientId}"})
	public Object getPatientAppointmentsGoogle(@PathVariable(required = false) Integer patientId) throws IOException {
		String username =  null;
		Patient patient = null;
		GoogleEventResponse res = new GoogleEventResponse();
		
		/*If patientId present - use it
		 * else use userServices*/
		if (patientId == null) {
			username = userServices.getUsername();
			patient = userServices.getPatientRepo().findByUsername(username);
		} 
		else {
			patient = userServices.getPatientRepo().findById(patientId.intValue());
		}
		
		
		Calendar service = null;
		try {
			service = SurgeryGoogleCalendarAPI.getCalendarService();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Events events = service.events().list("primary").execute();
		List<Event> appointments = events.getItems();
		List<Event> patientAppointments = new ArrayList<Event>();
		if (appointments.size() == 0) {
			res.setMessage("No Appointments");
			return res;
		}
		boolean isEmpty = true;
		for (Event e : appointments) {
			if (e.getAttendees() != null) {
				for(EventAttendee attendee :  e.getAttendees()) {
					if (attendee.getEmail().equals(patient.getEmail())) {
						patientAppointments.add(e);
						isEmpty = false;
					}
				}
			}
			System.out.println(e.getSummary());
		}
		if (isEmpty) {
			res.setResponse(false);
			res.setMessage("No appointments.");
			return res;
		}
		res.setAppointments(patientAppointments);
		res.setResponse(true);
		return res;
	}
	
	/**
	 * 
	 * @param summary
	 * @param description
	 * @param startDate
	 * @param startTime
	 * @param endDate
	 * @param endTime
	 * @return
	 * @throws GeneralSecurityException
	 * @throws IOException
	 */
	@PutMapping("createEvents/{patientId}/{doctorId}/{summary}/{description}/{startDate}/{startTime}/{endDate}/{endTime}")
	public Object createEvent(@PathVariable Integer patientId, @PathVariable Integer doctorId, @PathVariable String summary,
			@PathVariable String description, @PathVariable String startDate, @PathVariable String startTime,
			@PathVariable String endDate, @PathVariable String endTime) throws GeneralSecurityException, IOException {
		Response res = new Response();
		User patient = userServices.getUserRepo().findUserById(patientId);
		User doctor = userServices.getUserRepo().findUserById(doctorId);
		// Refer to the Java quickstart on how to setup the environment:
		// https://developers.google.com/calendar/quickstart/java
		// Change the scope to CalendarScopes.CALENDAR and delete any stored
		// credentials.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		
		Credential credential = null;
		try {
			credential = SurgeryGoogleCalendarAPI.getCredentials(HTTP_TRANSPORT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, SurgeryGoogleCalendarAPI.getJsonFactory(), credential)
                .setApplicationName(SurgeryGoogleCalendarAPI.getApplicationName())
                .build();
		Event event = new Event()
		    .setSummary(summary)
		    .setLocation(dashboard.getSurgeryAddress())
		    .setDescription(description);

		DateTime startDateTime = new DateTime(startDate+startTime);
		EventDateTime start = new EventDateTime()
		    .setDateTime(startDateTime)
		    .setTimeZone("GMT");
		event.setStart(start);

		DateTime endDateTime = new DateTime(endDate+endTime);//"2015-05-28T17:00:00-07:00"
		EventDateTime end = new EventDateTime()
		    .setDateTime(endDateTime)
		    .setTimeZone("GMT");
		event.setEnd(end);

//		String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
//		event.setRecurrence(Arrays.asList(recurrence));
		
		

		EventAttendee[] attendees = new EventAttendee[] {
		    new EventAttendee().setEmail(patient.getEmail()),
		    new EventAttendee().setEmail(doctor.getEmail()),
		};
		event.setAttendees(Arrays.asList(attendees));

		EventReminder[] reminderOverrides = new EventReminder[] {
		    new EventReminder().setMethod("email").setMinutes(24 * 60),//remind them one day before
		    new EventReminder().setMethod("popup").setMinutes(60),//remind them 1hr before
		};
		Event.Reminders reminders = new Event.Reminders()
		    .setUseDefault(false)
		    .setOverrides(Arrays.asList(reminderOverrides));
		event.setReminders(reminders);

		String calendarId = "primary";
		event = service.events().insert(calendarId, event).execute();
		System.out.printf("Event created: %s\n", event.getHtmlLink());

		res.setMessage("Event added successfully.");
		res.setResponse(true);
		res.setLink(event.getHtmlLink());
		
		return res;
	}
	
	
}
