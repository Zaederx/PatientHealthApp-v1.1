package app.PatientHealthApp.restControllers.surgery;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import app.PatientHealthApp.domain.users.OAuthUser;
import app.PatientHealthApp.domain.users.Patient;
import app.PatientHealthApp.jsonObject.response.Response;
import app.PatientHealthApp.services.UserServiceDetailsImpl;

@RestController
@RequestMapping("ajax/temp/signup/")
public class SignUpRestController {
	@Autowired
	UserServiceDetailsImpl userServices;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
//	@Autowired //TODO
//	OAuth2LoginAuthenticationProvider provider;

	
    private static final String CLIENT_ID = "855286502115-emk3m2e458bfmrtsed1o3b7qsmq3bkht.apps.googleusercontent.com";

    @GetMapping("test")
    public Object test () {
    	System.out.println("Test success");
    	Response res = new Response();
    	res.setResponse(true);
    	res.setMessage("Test complete.");
    	return res;
    }
    
    @PostMapping(value = "token-signup", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody Object gSignUp(@ModelAttribute("idToken") String idtoken) throws GeneralSecurityException, IOException {
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
		System.out.println("token="+idtoken);
		//VerifY that client id matches registered Google client ID
		GoogleIdToken idToken = verifier.verify(idtoken);
		
		
		/* Extract Payload Information*/
		if (idToken != null) {
		  Payload payload = idToken.getPayload();

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

		  OAuthUser oauthUser = null;
		  oauthUser = userServices.getOauthRepo().findOAuthUserByEmail(userId);
		  
		  if (oauthUser != null) {
			  Response res = new Response();
			  res.setResponse(false);
			  res.setMessage("Sign in failed. Please speak to administration to set up your account.");
			  return res;
		  } else {
			  Patient p = new Patient();
			  p.setName(name);
			  p.setEmail(email);
			  p.setUsername(initials(givenName,familyName)+p.getId());
			  userServices.getPatientRepo().save(p);
			  
			  OAuthUser user = new OAuthUser(userId,"something" ,p);
			  userServices.getOauthRepo().save(user);
		  }
		  
		  
		  
		  UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(oauthUser.getEmail(), "oauth", AuthorityUtils.createAuthorityList("ROLE_"+oauthUser.getUser().getRole()));
		  Authentication auth = authenticationManager.authenticate(authToken);
		  SecurityContextHolder.getContext().setAuthentication(auth);
		  

		} else {
		  System.out.println("Invalid ID token.");
		  Response res = new Response();
		  res.setResponse(true);
		  res.setMessage("Login sucessful.");
		}
		Response res = new Response();
		res.setResponse(true);
		res.setMessage("Login sucessful.");
		return res;
	}
    
    private String initials(String fname, String lname) {
    	String name = fname.subSequence(0, 1).toString()+lname.subSequence(0, 1).toString();
    	return name;
    }
	
}
