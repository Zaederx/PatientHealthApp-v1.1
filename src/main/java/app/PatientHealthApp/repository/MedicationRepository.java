package app.PatientHealthApp.repository;



import org.springframework.data.repository.CrudRepository;

import app.PatientHealthApp.domain.objects.Medication;

/**
 * Repository interface used to retrieve Medication Objects.
 * 
 * @author Zachary Ishmael
 *
 */
public interface MedicationRepository extends CrudRepository<Medication, Integer>{

	Medication findById(int id);
	
	Medication findByName(String name);
	
}
