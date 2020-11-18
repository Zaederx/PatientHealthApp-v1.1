package app.PatientHealthApp.jsonObject.response.doctor;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import app.PatientHealthApp.domain.users.Doctor;
import app.PatientHealthApp.jsonObject.response.Response;

/**
 * Class used as a JSON {@link Response} object.
 * @author Zachary Ishmael
 *
 */
@JsonInclude(Include.NON_NULL)
public class DoctorListResponse extends Response{

	private List<DoctorResponse> doctorList;
	
	
	public DoctorListResponse() {
		doctorList = null;
	}
	
	public DoctorListResponse(List<Doctor> doctors) {
		if (!doctors.isEmpty()) {
			doctorList = new ArrayList<DoctorResponse>();
			for (Doctor d : doctors) {
				DoctorResponse response = new DoctorResponse(d);
				doctorList.add(response);
			}
		}
	}

	/**
	 * @return the doctorList
	 */
	public List<DoctorResponse> getDoctorList() {
		return doctorList;
	}

	/**
	 * @param doctorList the doctorList to set
	 */
	public void setDoctorList(List<DoctorResponse> doctorList) {
		this.doctorList = doctorList;
	}

	
	public void setDoctors(List<Doctor> doctors) {
		doctorList = new ArrayList<DoctorResponse>();
		for (Doctor d : doctors) {
			DoctorResponse response = new DoctorResponse(d);
			doctorList.add(response);
		}
	}

	/**
	 * 
	 * @return doctorList size
	 */
	public int getListSize() {
		if (doctorList.isEmpty()) {
			return 0;
		}
		return doctorList.size();
	}


	/**
	 * Inner Class used to complete DoctorListResponse.
	 * @author Zachary Ishmael
	 *
	 */
	@JsonInclude(Include.NON_NULL)
	class DoctorResponse {
		Integer id;
		String name;
		String username;
		String email;
		String specialty;
		
		
		DoctorResponse() {
			this.id = null;
			this.name= null;
			this.username = null;
			this.email = null;
			this.specialty = null;
		}
		
		DoctorResponse(Doctor d) {
			this.id = d.getId();
			this.name= d.getName();
			this.username = d.getUsername();
			this.email = d.getEmail();
			this.specialty = d.getSpecialisation();
		}
		
		DoctorResponse(Integer id, String name, String username, String email, String specialty) {
			this.id = id;
			this.name= name;
			this.username = username;
			this.email = email;
			this.specialty = specialty;
		}

		/**
		 * @return the id
		 */
		public Integer getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(Integer id) {
			this.id = id;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the username
		 */
		public String getUsername() {
			return username;
		}

		/**
		 * @param username the username to set
		 */
		public void setUsername(String username) {
			this.username = username;
		}

		/**
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * @param email the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}

		/**
		 * @return the specialty
		 */
		public String getSpecialty() {
			return specialty;
		}

		/**
		 * @param specialty the specialty to set
		 */
		public void setSpecialty(String specialty) {
			this.specialty = specialty;
		}
		
		
	}
}
