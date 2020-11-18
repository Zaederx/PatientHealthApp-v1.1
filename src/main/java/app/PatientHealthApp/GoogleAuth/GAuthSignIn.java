package app.PatientHealthApp.GoogleAuth;


import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import app.PatientHealthApp.domain.users.OAuthUser;
import app.PatientHealthApp.domain.users.User;
import app.PatientHealthApp.jsonObject.response.Response;
import app.PatientHealthApp.repository.OAuthUserRepository;
import app.PatientHealthApp.services.UserServiceDetailsImpl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;

/**
 * Class used for Google Authentication.
 * Based on Google developer documentation examples.
 * See: https://developers.google.com/identity/sign-in/web/backend-auth
 * @author Zachary Ishmael
 *
 */
@Controller
@RequestMapping("oauthentication/")
public class GAuthSignIn {
	
	@Autowired
	UserServiceDetailsImpl userServices;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	OAuthUserRepository gAuthIdRepo;
	
    private static final String CLIENT_ID = "855286502115-emk3m2e458bfmrtsed1o3b7qsmq3bkht.apps.googleusercontent.com";

    @GetMapping("test")
    public @ResponseBody Object test () {
    	Path file = Paths.get("src/main/resources/credentials.json");
    	boolean exists = Files.isReadable(file);
    	
    	if (!exists) {
    		Response res = new Response();
    		res.setResponse(false);
    		res.setMessage("File not readable");
    		return res;
    	}
    	System.out.println("Test success");
    	Response res = new Response();
    	res.setResponse(true);
    	res.setMessage("Test complete.");
    	return res;
    }
    @PostMapping(value = "store-code", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody Object storeAuthCode( HttpServletRequest request) throws IOException {
    	
    	if (request.getHeader("X-Requested-With") == null) {
    		  // Without the `X-Requested-With` header, this request could be forged. Aborts.
    		}

    	
    	String authCode = request.getReader().readLine();
    	// (Receive authCode via HTTPS POST)

    	// Set path to the Web application client_secret_*.json file you downloaded from the
    	// Google API Console: https://console.developers.google.com/apis/credentials
    	// You can also find your Web application client ID and client secret from the
    	// console and specify them directly when you create the GoogleAuthorizationCodeTokenRequest
    	// object.
    	Path file = Paths.get("src/main/resources/credentials.json");
    	boolean exists = Files.isReadable(file);
    	
    	if (!exists) {
    		Response res = new Response();
    		res.setResponse(false);
    		res.setMessage("File not readable");
    		return res;
    	}
    	String CLIENT_SECRET_FILE = file.toString();
    	String REDIRECT_URI = "https://localhost:8090";

    	// Exchange auth code for access token
    	GoogleClientSecrets clientSecrets =
    	    GoogleClientSecrets.load(
    	        JacksonFactory.getDefaultInstance(), new FileReader(CLIENT_SECRET_FILE));
    	GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
    	              new NetHttpTransport(),
    	              JacksonFactory.getDefaultInstance(),
    	              "https://oauth2.googleapis.com/token",
    	              clientSecrets.getDetails().getClientId(),
    	              clientSecrets.getDetails().getClientSecret(),
    	              authCode,
    	              REDIRECT_URI)
    	              .execute();

    	String accessToken = tokenResponse.getAccessToken();

    	// Use access token to call API
    	GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
    	
    	
    	 Calendar service = new Calendar.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
                 .setApplicationName("Patient Health App")
                 .build();

    	 
    	// Get profile info from ID token
    	 
    	GoogleIdToken verifiedIdToken = tokenResponse.parseIdToken();
    	
    	Response res = signIn(verifiedIdToken);

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
        
    	
    	return res;
    }
    
    
    @GetMapping("Callback")
    public String callback() {
    	return "redirect:/";//TODO - FIX CALLBACK HANDLER
    }
    @PostMapping(value = "token-signin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody Object gAuthentication(@ModelAttribute("idToken") String idToken) throws GeneralSecurityException, IOException {
    	System.out.println("GOT HERE");
    	final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
		final NetHttpTransport TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(TRANSPORT, JSON_FACTORY)
			    // Specify the CLIENT_ID of the app that accesses the backend:
			    .setAudience(Collections.singletonList(CLIENT_ID))
			    // Or, if multiple clients access the backend:
			    //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
			    .build();

			// (Receive idTokenString by HTTPS POST)
			System.out.println("token="+idToken);
			//VerifY that client id matches registered Google client ID
			GoogleIdToken verifiedIdToken = verifier.verify(idToken);
			
		Object response =	signIn(verifiedIdToken);
			  
		return response;	  
			  
	}
    
    private Response signIn(GoogleIdToken verifiedIdToken) {
    	Response res = new Response();
    	if (verifiedIdToken != null) {
			  Payload payload = verifiedIdToken.getPayload();

			  // Print user identifier
			  String userId = payload.getSubject();
			  System.out.println("User ID: " + userId);

			  // Get profile information from payload
			  String email = payload.getEmail();
			  boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			  String name = (String) payload.get("name");
			  String pictureUrl = (String) payload.get("picture");
			  String locale = (String) payload.get("locale");
			  String familyName = (String) payload.get("family_name");
			  String givenName = (String) payload.get("given_name");
    	//Check if user exists with this email
		  User user = null;
		  user = userServices.getUserRepo().findByEmail(email);
		  boolean enabled = false;
		  if (user != null) {
			  enabled = user.isOAuth2Enabled();
		  }
		  else {
			  //if no current user exists
			  res.setResponse(false);
			  res.setMessage("Sign in failed. Please speak to administration to set up your account.");
			  return res;
		  }

		  
		  //Attempt to retrieve OAuth setup
		  OAuthUser oauthUser = userServices.getOauthRepo().findOAuthUserByEmail(email);
		  if (!enabled) {
			  res.setResponse(false);
			  res.setMessage("Please enable Google Sign-In in your account settings to log in with your google account.");
			  return res;
		  }
		  
		  /*if OAuth2 is enabled but not previously setup
		   * 
		   */
		  else if (enabled == true && oauthUser == null) {
			  
			  //Add google info to account
			  user.setPictureURL(pictureUrl);
			  
			  
			  //Create ouathUser login for existing user...
			  OAuthUser newOAuthUser = new OAuthUser(email,userServices.encode("oauth"),user);
			  userServices.getOauthRepo().save(newOAuthUser);
			  
			  //Start new user Session
			  UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, "oauth", AuthorityUtils.createAuthorityList("ROLE_"+newOAuthUser.getUser().getRole()));
			  Authentication auth = authenticationManager.authenticate(authToken);
			  SecurityContextHolder.getContext().setAuthentication(auth);
			  
			  res.setResponse(true);
			  res.setMessage("Sign in as: "+name);
			  return res;
		  } 
		  
		  else if (enabled) {
			//If oauth2 Account already exists - start user session
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, "oauth", AuthorityUtils.createAuthorityList("ROLE_"+oauthUser.getUser().getRole()));
			Authentication auth = authenticationManager.authenticate(authToken);
			System.out.println("*****Authorities to String******: "+auth.getAuthorities().toString());
			SecurityContextHolder.getContext().setAuthentication(auth);
			res.setResponse(true);
			res.setMessage("Sign in as: "+name);
		  }
		  
		}
		//If vierifedIdToken is null
		else {
		  System.out.println("Invalid ID token.");
		  res.setResponse(true);
		  res.setMessage("Login unsucessful.");
		}
		
	
	return res;
    }
    
    
}
