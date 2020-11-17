
package app.PatientHealthApp.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationProvider;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import app.PatientHealthApp.services.UserServiceDetailsImpl;



@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	@Qualifier("gauthenticationSuccessHandler")
//	private AuthenticationSuccessHandler gOathHandler;
	@Autowired
	private UserServiceDetailsImpl uDetails;
	@Override
	protected void configure (HttpSecurity https) throws Exception {
		//for testing purposes - to be configured later
//		https.authorizeRequests().antMatchers("/").permitAll(); 
		

		https
				.requiresChannel()
				.anyRequest()
				.requiresSecure()
				
			.and()
				.authorizeRequests()
				.antMatchers("/","/home","/validate","/resources/**", "/img/**.jpg","/oauthentication/**", "/temp/signup/**","/oauth2/patient").permitAll()
				.antMatchers("/h2-console/**").permitAll()//for viewing h2 console - would remove in real app senario
				.antMatchers("/doctor/**","ajax/doctor/**","/calendar/doctor/**").hasRole("DOCTOR")
				.antMatchers("/patient/**","/ajax/patient/**","/appointment/submit","/calendar/patient/**").hasRole("PATIENT")
				.antMatchers("/admin/**","/ajax/**").hasRole("ADMIN")
				.anyRequest().authenticated() // all request should be authenticated...
				
				
				.and().formLogin()
				.passwordParameter("password")
				.usernameParameter("username")
				.loginPage("/login") // for custom page - later on
				.defaultSuccessUrl("/", true)
				.loginProcessingUrl("/authenticateUser") //for custom Processing
				.permitAll()	//...except default Spring Login page
				
//				.and().oauth2Login().loginPage("/temp/signup/patient/{hashCode}").successHandler(gOathHandler)
				
				.and().rememberMe()
				.rememberMeCookieName("PatientApp")
				.rememberMeParameter("rememberMe")
				.tokenValiditySeconds(172800)
				
				.and().logout()
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")//delete Spring default cookies
					.deleteCookies("PatientApp")
					.deleteCookies("G_AUTHUSER_H", "G_ENABLED_IDPS")//Google Auth Cookies
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/login?logout")
					.permitAll()
					
				.and().exceptionHandling().accessDeniedPage("/login-error") 
//				.and().csrf().disable()
				// same as error page so hackers can' tell whether there is a resource page at that request 
				.and().csrf().ignoringAntMatchers("/h2-console/**")
				.and().headers().frameOptions().disable()//prevents h2-console frame problems
		;
	}
	
	@Autowired
	public void configureGlobal (AuthenticationManagerBuilder auth ) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		auth.userDetailsService(uDetails).passwordEncoder(encoder);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
}
