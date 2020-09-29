package ChronicPatientHealthApp.PatientHealthApp.spockTests

import static org.junit.jupiter.api.Assertions.*
import static org.mockito.ArgumentMatchers.anyString
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view


import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.session.web.http.SessionRepositoryFilter
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import app.PatientHealthApp.ChronicPatientHealthApp
import app.PatientHealthApp.config.SecurityConfig
import spock.lang.Specification
//
//@ContextConfiguration
@SpringBootTest(classes = ChronicPatientHealthApp.class)
//@WebMvcTest(useDefaultFilters = true )
public class Tests extends Specification{

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mock;
	
	private ResultActions results;
	
//	@Autowired(required = false)
//	private UsernamePasswordAuthenticationFilter securityFilter

	def "1: patient logins" () {
		given: "Application is running"
		this.mock = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
		when: "user submits POST request with correct login information"
		results = this.mock.perform(formLogin("/authenticateUser").acceptMediaType(MediaType.APPLICATION_FORM_URLENCODED).loginProcessingUrl("/authenticateUser")
			.user("username","zim")
			.password("password","password"));
		
		then: "HTTP response should be 200"
		results.andExpect(status().isOk());
		
		and: "patient-home.html view should be presented"
		results.andExpect(view().name('patient-home'));
	}
	
}
