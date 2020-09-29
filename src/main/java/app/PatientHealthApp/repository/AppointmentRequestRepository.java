package app.PatientHealthApp.repository;

import org.springframework.data.repository.CrudRepository;

import app.PatientHealthApp.domain.objects.AppointmentRequest;
import app.PatientHealthApp.domain.users.Patient;

public interface AppointmentRequestRepository extends CrudRepository<AppointmentRequest, Integer>{

	public AppointmentRequest findByPatient(Patient patient);
}
