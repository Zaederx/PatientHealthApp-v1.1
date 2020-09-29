package app.PatientHealthApp;

import java.util.ArrayList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.PatientHealthApp.domain.objects.Medication;
import app.PatientHealthApp.domain.objects.Prescription;
import app.PatientHealthApp.domain.users.Admin;
import app.PatientHealthApp.domain.users.Doctor;
import app.PatientHealthApp.domain.users.Patient;
import app.PatientHealthApp.services.UserServiceDetailsImpl;





/**
 * 
 * Class used to run the app.
 * Implements CommandLineRunner - indicates that the class should be run when 
 * within a Spring Application.
 * @author Zachary Ishmael
 */

@SpringBootApplication
public class ChronicPatientHealthApp implements CommandLineRunner{
	@Autowired
	UserServiceDetailsImpl repo;
	@Autowired
	AdminDashboard dashboard;
	@Autowired
	SurgeryGoogleCalendarAPI cal;
	
	public static void main(String[] args) {
		SpringApplication.run(ChronicPatientHealthApp.class, args);
	}

	@Override
	@Transactional
	public void run (String ... strings) throws Exception {
//		testUsers();
	}

	
	
	@SuppressWarnings("unused")
	private void testUsers() {
		
		//Demo Admin
		Admin admin = new Admin();
		admin.setName("A");
		admin.setUsername("ADMIN1");
		admin.setPassword("password");
		admin.setEmail("admin@email.com");
		admin.setId(5);
		repo.getAdminRepo().save(admin);
		
		
		//Demo Doctor
		Doctor doctor = new Doctor();
		doctor.setName("D");
		doctor.setPassword("password");
		doctor.setEmail("doctor@email.com");
		doctor.setUsername("DOCTOR1");
		repo.getDoctorRepo().save(doctor);
		
		
		//Demo Patient & Medication
		Medication meds1 = new Medication("med1","desscription1","dosage1");
		Medication meds2 = new Medication("med2","desscription2","dosage2");
		Medication meds3 = new Medication("med3","desscription3","dosage3");
		Medication meds4 = new Medication("med4","desscription4","dosage4");
		Medication meds5 = new Medication("med5","desscription5","dosage5");
		
		repo.getMedRepo().save(meds1);
		repo.getMedRepo().save(meds2);
		repo.getMedRepo().save(meds3);
		repo.getMedRepo().save(meds4);
		repo.getMedRepo().save(meds5);
		Prescription pres1 = new Prescription(meds1);
		Prescription pres2 = new Prescription(meds2);
		Prescription pres3 = new Prescription(meds3);
		Prescription pres4 = new Prescription(meds4);
		Prescription pres5 = new Prescription(meds5);
		pres1.setDirections("doctor's directions1");
		pres2.setDirections("doctor's directions2");
		pres3.setDirections("doctor's directions3");
		pres4.setDirections("doctor's directions4");
		pres5.setDirections("doctor's directions5");
		List<Prescription> prescriptions = new ArrayList<Prescription>();
		prescriptions.add(pres1);
		prescriptions.add(pres2);
		prescriptions.add(pres3);
		prescriptions.add(pres4);
		prescriptions.add(pres5);
		repo.getPresRepo().save(prescriptions);
		
		Patient p = new Patient();
		p.setName("P");
		p.setEmail("patient@email.com");
		p.setPassword("password");
		p.setUsername("PATIENT01");
		p.setPrescriptions(prescriptions);
		repo.getPatientRepo().save(p);
		
	}
}
