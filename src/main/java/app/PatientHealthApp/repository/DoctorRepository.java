package app.PatientHealthApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import app.PatientHealthApp.domain.users.Doctor;

/**
 * A Repository used to interface with User data in the database.
 * 
 * By default certain methods are provided by CrudRepository.
 * 
 * @author Zachary Ishmael
 *
 */

public interface DoctorRepository extends CrudRepository<Doctor, Integer>{
	
	Doctor findByUsername(String username);
	List<Doctor> findByName(String name);
	Doctor findById(int id);
	List<Doctor> findAll();
}
