package app.PatientHealthApp.repository;

import org.springframework.data.repository.CrudRepository;

import app.PatientHealthApp.domain.objects.GMC;

public interface GMCRepository extends CrudRepository<GMC, Integer>{

	GMC findByCode(String code);
}
