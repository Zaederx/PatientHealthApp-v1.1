package app.PatientHealthApp.jsonObject;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import app.PatientHealthApp.domain.objects.AppointmentRequest;

/**
 * A class used to to return AppointmentResponses to user.
 * Spring's Jackson support used to convert to json objects.
 * @author 	Zachary Ishmael
 *
 */
@JsonInclude(Include.NON_NULL)
public class AppointmentRequestResponseList extends Response{

	/**
	 * The size of the request list.
	 * necessary variable for front end JSON processing
	 */
	private Integer listSize;
	private List<AppointmentRequestResponse> requests;
	
	
	public AppointmentRequestResponseList() {
		super();
		listSize = null;
		requests = null;
	}
	
	public AppointmentRequestResponseList(List<AppointmentRequest> requests) {
		super();
		setRequests(requests);
		countList();
	}

	/**
	 * sets listSize variable to requests.size().
	 * Necessary to allow JSON processing in front end.
	 */
	@JsonIgnore
	public void countList() {
		if (requests != null) {
			listSize = requests.size();
		}
	}

	/**
	 * @return the count
	 */
	public Integer getListSize() {
		countList();
		return listSize;
	}

	/**
	 * @return the requests
	 */
	public List<AppointmentRequestResponse> getRequests() {
		return requests;
	}

	/**
	 * @param requests the requests to set
	 */
	public void setRequests(List<AppointmentRequest> requests) {
		
		List<AppointmentRequestResponse> requestResponses = new ArrayList<AppointmentRequestResponse>();
		for (AppointmentRequest r : requests) {
			requestResponses.add(new AppointmentRequestResponse(r));
		}
		this.requests = requestResponses;
	}
	
	class AppointmentRequestResponse {
		private int id;
		
		private int appointmentType;
		
		private boolean session;
		
		private String description;
		
		private String summary;
		
		
		public AppointmentRequestResponse() {
			
		}

		public AppointmentRequestResponse(AppointmentRequest r) {
			this.id = r.getId();
			this.appointmentType = r.getAppointmentType();
			this.session = r.getSession();
			this.description = r.getDescription();
			this.summary = r.getSummary();
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
		 * @return the appointmentType
		 */
		public int getAppointmentType() {
			return appointmentType;
		}

		/**
		 * @param appointmentType the appointmentType to set
		 */
		public void setAppointmentType(int appointmentType) {
			this.appointmentType = appointmentType;
		}

		/**
		 * @return the session
		 */
		public boolean isSession() {
			return session;
		}

		/**
		 * @param session the session to set
		 */
		public void setSession(boolean session) {
			this.session = session;
		}

		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}

		/**
		 * @return the summary
		 */
		public String getSummary() {
			return summary;
		}

		/**
		 * @param summary the summary to set
		 */
		public void setSummary(String summary) {
			this.summary = summary;
		}

		
		
	}
	
}
