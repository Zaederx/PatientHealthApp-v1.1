package app.PatientHealthApp.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.PatientHealthApp.domain.objects.doctor.GMC;
import app.PatientHealthApp.jsonObject.Response;
import app.PatientHealthApp.services.UserServiceDetailsImpl;

/**
 * Mock Api for requesting doctor GMC.
 * Used to check that a doctor is a certified doctor.
 * @author Zachary Ishmael
 *
 */
@RestController
@RequestMapping("national-medical-services/gmc/api")
public class MockGMCApi {

	@Autowired
	UserServiceDetailsImpl userServices;
	
	@GetMapping({"/{gmc}", "/{gmc}/{name}"})
	public Response isValidGMC(@PathVariable String gmc, @PathVariable(required = false) String name) {
		Response res = new Response();
		GMC code = userServices.getGmcRepo().findByCode(gmc);
		
		/* Responses:
		 * True - message recieved successfully - valid GMC
		 * False - message recieved successfully  - invalid GMC
		 */
		//No code sent
		if (code == null) {
			res.setResponse(false);
			res.setMessage("Invalid GMC");
		}
		//Name only Query
		else if (name != null && name.equals("Code-Only-Query")) {
			res.setResponse(true);
		}
		//code present + name present +name matches 
		else if (name != null && name.equals(code.getName()) ) {
			res.setResponse(true);
		}
		//code present + name present + name does not match
		else if (name != null && !name.equals(code.getName()) ) {
			res.setResponse(false);
			res.setMessage("Name does not match GMC code.");
		}
		//i.e. code present + name not present
		else {
			res.setResponse(false);
			res.setMessage("Please submit a name to match gmc code.");
		}
		
		
		return res;
	}
}
