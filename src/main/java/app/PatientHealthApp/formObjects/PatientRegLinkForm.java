package app.PatientHealthApp.formObjects;
/**
 * Class Form Object Admin's patient self registration.
 * 
 * @author Zachary Ishmael
 *
 */
public class PatientRegLinkForm {
	private String usertype;
	
	private String gmail;
	
	/**
	 * @return the usertype
	 */
	public String getUsertype() {
		return usertype;
	}

	/**
	 * @param usertype the usertype to set
	 */
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	/**
	 * @return the gmail
	 */
	public String getGmail() {
		return gmail;
	}

	/**
	 * @param gmail the gmail to set
	 */
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	
	
}
