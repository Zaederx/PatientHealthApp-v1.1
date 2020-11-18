package app.PatientHealthApp.jsonObject.response.surgery;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import app.PatientHealthApp.domain.objects.surgery.Prescription;
import app.PatientHealthApp.jsonObject.response.Response;

/**
 * A Class used to return Prescriptions to user.
 * Spring's Jackson support used to convert to json objects.
 * @author Zachary Ishmael
 *
 */
@JsonInclude(Include.NON_NULL)
public class PrescriptionResponseList extends Response {

	private Integer listSize;
	private List<PrescriptionResponse> prescriptions;

	public PrescriptionResponseList() {
		super();
		listSize = null;
		prescriptions = null;
	}

	public PrescriptionResponseList(List<Prescription> prescriptions) {
		super();
		this.prescriptions = setPrescriptions(prescriptions);
		listSize = null;
		count();
	}
	@JsonIgnore
	public void countList() {
		if (prescriptions != null) {
			listSize = prescriptions.size();
		}
	}
	/**
	 * @return the listSize
	 */
	public Integer getListSize() {
		return listSize;
	}

	/**
	 * @param listSize the listSize to set
	 */
	public void setListSize(Integer listSize) {
		this.listSize = listSize;
	}

	/**
	 * @return the prescriptions
	 */
	public List<PrescriptionResponse> getPrescriptions() {
		return prescriptions;
	}

	/**
	 * @param prescriptions the prescriptions to set
	 */
	public List<PrescriptionResponse> setPrescriptions(List<Prescription> prescriptions) {
		List<PrescriptionResponse> prescriptionResponses = new ArrayList<PrescriptionResponse>();
		for (Prescription p : prescriptions) {
			prescriptionResponses.add(new PrescriptionResponse());
		}

		countList();
		return prescriptionResponses;
	}
	
	
	class PrescriptionResponse {
		int id;
		String directions;
		String medicationName;
		
		public PrescriptionResponse() {
			int id = 0;
			String directions = "Default";
			String medicationName = "Default";
		}
		
		public PrescriptionResponse(Prescription p) {
			this.id = p.getId();
			this.directions = p.getDirections();
			this.medicationName = p.getMedication().getName();
		}

		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * @return the directions
		 */
		public String getDirections() {
			return directions;
		}

		/**
		 * @param directions the directions to set
		 */
		public void setDirections(String directions) {
			this.directions = directions;
		}

		/**
		 * @return the medicationName
		 */
		public String getMedicationName() {
			return medicationName;
		}

		/**
		 * @param medicationName the medicationName to set
		 */
		public void setMedicationName(String medicationName) {
			this.medicationName = medicationName;
		}
		
		
	}

}
