package app.PatientHealthApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import app.PatientHealthApp.domain.objects.Prescription;

public interface PrescriptionRepository extends CrudRepository<Prescription, Integer>{
	Prescription findById(int id);
	
	default void save(List<Prescription> prescriptions) {
		for (Prescription p: prescriptions) {
			save(p);
		}
	}
}
