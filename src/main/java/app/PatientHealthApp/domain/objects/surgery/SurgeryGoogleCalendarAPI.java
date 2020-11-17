package app.PatientHealthApp.domain.objects.surgery;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Quickstart file provided by google calendar api instructions.
 * @see {@link https://developers.google.com/calendar/quickstart/java}
 * @author Zachary Ishmael
 * @apiNote created by following instructions from Developer Docs
 * 
 *
 */
@Component
public class SurgeryGoogleCalendarAPI {
    private static final String APPLICATION_NAME = "Patient Health App";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    
    
    
    /**
	 * @return the applicationName
	 */
	public static String getApplicationName() {
		return APPLICATION_NAME;
	}

	/**
	 * @return the jsonFactory
	 */
	public static JsonFactory getJsonFactory() {
		return JSON_FACTORY;
	}

	/**
	 * @return the tokensDirectoryPath
	 */
	public static String getTokensDirectoryPath() {
		return TOKENS_DIRECTORY_PATH;
	}

	/**
	 * @return the scopes
	 */
	public static List<String> getScopes() {
		return SCOPES;
	}

	/**
	 * @return the credentialsFilePath
	 */
	public static String getCredentialsFilePath() {
		return CREDENTIALS_FILE_PATH;
	}

	/**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws Exception 
     */
    public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws Exception {
        // Load client secrets.
        InputStream in = SurgeryGoogleCalendarAPI.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

//    public static void main(String... args) throws Exception {
//       doCalendar();
//    }
//  TODO - FIGURE OUT HOW TO ATUOMATICALLY REFRESH TOKENS   https://accounts.google.com/o/oauth2/auth?access_type=offline&client_id=855286502115-emk3m2e458bfmrtsed1o3b7qsmq3bkht.apps.googleusercontent.com&redirect_uri=http://localhost:8888/Callback&response_type=code&scope=https://www.googleapis.com/auth/calendar
    public String getUpcomingEvents() throws Exception {
    	 // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        
        // List the next 10 events from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        String upcomingEvents = "";
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                System.out.printf("%s (%s)\n", event.getSummary(), start);
                upcomingEvents += String.format("%s (%s)\n", event.getSummary(), start);
            }
        }
        return upcomingEvents;
    }
    
    public static Calendar getCalendarService() throws Exception {
    	final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    	return service;
    }
}