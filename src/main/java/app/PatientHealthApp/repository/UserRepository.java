package app.PatientHealthApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import app.PatientHealthApp.domain.users.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	User findUserById(Integer id);
	
	User findByUsername(String username);
	
	List<User> findAllByUsername(String username);
	List<User> findAll();
	User findByEmail(String email);
	
	List<User> findByRole(String role);

	List<User> findByName(String name);
	

}
