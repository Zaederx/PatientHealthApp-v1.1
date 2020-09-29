package app.PatientHealthApp.repository;

import org.springframework.data.repository.CrudRepository;

import app.PatientHealthApp.domain.users.OAuthUser;

/**
 * Repository interface used to retrieve GAuthId objects.
 * @author Zachary Ishmael
 *
 */
public interface OAuthUserRepository extends CrudRepository<OAuthUser, String> {
	
	OAuthUser findOAuthUserByEmail(String email);
}
