package app.PatientHealthApp.jsonObject;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import app.PatientHealthApp.domain.objects.Prescription;

@JsonInclude(Include.NON_NULL)
public class PatientMedicalDataResponse extends Response {
	
	private List<Prescription> controlDrugs;
	private List<Prescription> nonControlDrugs;
	
	public PatientMedicalDataResponse() {
		super();
		controlDrugs = null;
		nonControlDrugs = null;
	}

	public PatientMedicalDataResponse(List<Prescription> prescriptions) {
		controlDrugs = new ArrayList<Prescription>();
		nonControlDrugs = new ArrayList<Prescription>();
		for (Prescription p : prescriptions) {
			if (p.getMedication().isControlDrug()) {
				controlDrugs.add(p);
			}
			else {
				nonControlDrugs.add(p);
			}
		}
	}
	/**
	 * @return the controlDrugs
	 */
	public List<Prescription> getControlDrugs() {
		return controlDrugs;
	}

	/**
	 * @param controlDrugs the controlDrugs to set
	 */
	public void setControlDrugs(List<Prescription> controlDrugs) {
		this.controlDrugs = controlDrugs;
	}

	/**
	 * @return the nonControlDrugs
	 */
	public List<Prescription> getNonControlDrugs() {
		return nonControlDrugs;
	}

	/**
	 * @param nonControlDrugs the nonControlDrugs to set
	 */
	public void setNonControlDrugs(List<Prescription> nonControlDrugs) {
		this.nonControlDrugs = nonControlDrugs;
	}
	
	
}
