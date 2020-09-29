package app.PatientHealthApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import app.PatientHealthApp.domain.users.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer>{
	
	Admin findByUsername(String username);
	List<Admin> findByName(String name);
	List<Admin> findAll();
}
