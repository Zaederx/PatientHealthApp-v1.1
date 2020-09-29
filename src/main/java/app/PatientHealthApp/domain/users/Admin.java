package app.PatientHealthApp.domain.users;

import javax.persistence.Entity;
import javax.validation.Valid;

import app.PatientHealthApp.formObjects.AdminRegForm;

/**
 * Admin {@link Entity} class that defines admin table.
 * @author Zachary Ishmael
 *
 */
@Entity(name="admin")
public class Admin extends User {

	/*Default constructor - required by jpa/hibernate*/
	public Admin() {
		this.role = "ADMIN";
	}
	
	public Admin(String name, String username, String email, String password) {
		super(name, username, email, password, "ADMIN");
	}

	public Admin(@Valid AdminRegForm form) {
		super(form.getName(), form.getUsername(), "zaki1.admn.web.app.uol@gmail.com", form.getPassword(),"ADMIN");
	}
	

}
