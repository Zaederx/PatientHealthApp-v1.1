package app.PatientHealthApp;

import java.util.ArrayList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.PatientHealthApp.domain.objects.admin.AdminDashboard;
import app.PatientHealthApp.domain.objects.surgery.Medication;
import app.PatientHealthApp.domain.objects.surgery.Prescription;
import app.PatientHealthApp.domain.objects.surgery.SurgeryGoogleCalendarAPI;
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
		// testUsers();
	}

	
	
	@SuppressWarnings("unused")
	private void testUsers() {
		
		//Demo Administration staff
		Admin admin1 = new Admin();
		admin1.setName("A1");
		admin1.setUsername("ADMIN1");
		admin1.setPassword("password");
		admin1.setEmail("admin1@email.com");
		
//		admin.setId(5);
		repo.getAdminRepo().save(admin1);

		Admin admin2 = new Admin();
		admin2.setName("A2");
		admin2.setUsername("ADMIN2");
		admin2.setPassword("password");
		admin2.setEmail("admin2@email.com");
//		admin.setId(5);
		repo.getAdminRepo().save(admin2);

		Admin admin3 = new Admin();
		admin3.setName("A3");
		admin3.setUsername("ADMIN3");
		admin3.setPassword("password");
		admin3.setEmail("admin3@email.com");
//		admin.setId(5);
		repo.getAdminRepo().save(admin3);
		
		
		//Demo Doctors
		Doctor doctor1 = new Doctor();
		doctor1.setName("D1");
		doctor1.setPassword("password");
		doctor1.setEmail("doctor1@email.com");
		doctor1.setUsername("DOCTOR1");
		doctor1.setGMC("GMC1");
		repo.getDoctorRepo().save(doctor1);

		Doctor doctor2 = new Doctor();
		doctor2.setName("D2");
		doctor2.setPassword("password");
		doctor2.setEmail("doctor2@email.com");
		doctor2.setUsername("DOCTOR2");
		doctor2.setGMC("GMC2");
		repo.getDoctorRepo().save(doctor2);

		Doctor doctor3 = new Doctor();
		doctor3.setName("D3");
		doctor3.setPassword("password");
		doctor3.setEmail("doctor3@email.com");
		doctor3.setUsername("DOCTOR3");
		doctor3.setGMC("GMC3");
		repo.getDoctorRepo().save(doctor3);


		/**** Demo Medication and Prescriptions ****/
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

		//Prescriptions: P1
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



		//List of Prescriptions: P1
		List<Prescription> prescriptions1 = new ArrayList<Prescription>();
		prescriptions1.add(pres1);
		prescriptions1.add(pres2);
		prescriptions1.add(pres3);
		prescriptions1.add(pres4);
		prescriptions1.add(pres5);
		repo.getPresRepo().save(prescriptions1);

		//Prescriptions: P2
		Prescription pres6 = new Prescription(meds1);
		Prescription pres7 = new Prescription(meds2);
		Prescription pres8 = new Prescription(meds3);
		Prescription pres9 = new Prescription(meds4);
		Prescription pres10 = new Prescription(meds5);

		pres6.setDirections("doctor's directions6");
		pres7.setDirections("doctor's directions7");
		pres8.setDirections("doctor's directions8");
		pres9.setDirections("doctor's directions9");
		pres10.setDirections("doctor's directions10");

		List<Prescription> prescriptions2 = new ArrayList<Prescription>();
		prescriptions2.add(pres6);
		prescriptions2.add(pres7);
		prescriptions2.add(pres8);
		prescriptions2.add(pres9);
		prescriptions2.add(pres10);
		repo.getPresRepo().save(prescriptions2);

		//Prescriptions: P3
		Prescription pres11 = new Prescription(meds1);
		Prescription pres12 = new Prescription(meds2);
		Prescription pres13 = new Prescription(meds3);
		Prescription pres14 = new Prescription(meds4);
		Prescription pres15 = new Prescription(meds5);

		pres11.setDirections("doctor's directions11");
		pres12.setDirections("doctor's directions12");
		pres13.setDirections("doctor's directions13");
		pres14.setDirections("doctor's directions14");
		pres15.setDirections("doctor's directions15");

		//Demo List of prescriptions - one list per patient
		List<Prescription> prescriptions3 = new ArrayList<Prescription>();
		prescriptions3.add(pres11);
		prescriptions3.add(pres12);
		prescriptions3.add(pres13);
		prescriptions3.add(pres14);
		prescriptions3.add(pres15);
		repo.getPresRepo().save(prescriptions3);




		//Demo Patients
		Patient p1 = new Patient();
		p1.setName("P1");
		p1.setEmail("patient1@email.com");
		p1.setPassword("password");
		p1.setUsername("PATIENT1");
		p1.setPrescriptions(prescriptions1);
		repo.getPatientRepo().save(p1);

		Patient p2 = new Patient();
		p2.setName("P2");
		p2.setEmail("patient2@email.com");
		p2.setPassword("password");
		p2.setUsername("PATIENT2");
		p2.setPrescriptions(prescriptions2);
		repo.getPatientRepo().save(p2);

		Patient p3 = new Patient();
		p3.setName("P3");
		p3.setEmail("patien3t@email.com");
		p3.setPassword("password");
		p3.setUsername("PATIENT3");
		p3.setPrescriptions(prescriptions3);
		repo.getPatientRepo().save(p3);
		
	}
}
