package app.PatientHealthApp.GoogleAuth;

import javax.persistence.GeneratedValue;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class GoogleCallBackRest {
	
	@GetMapping("Callback")
	public Object callback(@RequestParam String code) {
		return null;
		
	}
}
