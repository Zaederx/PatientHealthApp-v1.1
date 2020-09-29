package app.PatientHealthApp.repository;



import java.util.List;

import org.springframework.data.repository.CrudRepository;

import app.PatientHealthApp.domain.users.Patient;

/**
 * A Repository used to interface with User data in the database.
 * 
 * By default certain methods are provided by CrudRepository.
 * 
 * @author Zachary Ishmael
 *
 */


public interface PatientRepository extends CrudRepository<Patient,Integer> {

	
	Patient findByUsername(String username);
	List<Patient> findByName(String name);
	Patient findByEmail(String email);
	
	Patient findById(int id);
	List<Patient> findAll();
	
}
