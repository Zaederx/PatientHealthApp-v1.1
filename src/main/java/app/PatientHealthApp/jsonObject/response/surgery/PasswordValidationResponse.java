package app.PatientHealthApp.jsonObject.response.surgery;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import app.PatientHealthApp.jsonObject.response.Response;

@JsonInclude(Include.NON_NULL)//only non null included
public class PasswordValidationResponse extends Response{

	//Message Strings for password checks
	private String passwordLength;
	private String specialCharacters;
	private String containsNumber;
	private String containsUppercase;
	private String containsLowercase;
	private String passwordsMatch;
	
	public PasswordValidationResponse() {
		super();
		this.passwordLength = "";
		this.specialCharacters = "";
		this.containsNumber = "";
		this.containsUppercase = "";
		this.containsLowercase = "";
		this.passwordsMatch = "";
	}
	public PasswordValidationResponse(String passwordLength, String specialCharacters, String containsNumber,
		String containsUppercase, String containsLowercase, String passwordsMatch ) {
		super();
		this.passwordLength = passwordLength;
		this.specialCharacters = specialCharacters;
		this.containsNumber = containsNumber;
		this.containsUppercase = containsUppercase;
		this.containsLowercase = containsLowercase;
		this.passwordsMatch = passwordsMatch;
	}


	/**
	 * @return the passwordLength
	 */
	public String getPasswordLength() {
		return passwordLength;
	}


	/**
	 * @param passwordLength the passwordLength to set
	 */
	public void setPasswordLength(String passwordLength) {
		this.passwordLength = passwordLength;
	}


	/**
	 * @return the specialCharacters
	 */
	public String getSpecialCharacters() {
		return specialCharacters;
	}


	/**
	 * @param specialCharacters the specialCharacters to set
	 */
	public void setSpecialCharacters(String specialCharacters) {
		this.specialCharacters = specialCharacters;
	}


	/**
	 * @return the containsNumber
	 */
	public String getContainsNumber() {
		return containsNumber;
	}


	/**
	 * @param containsNumber the containsNumber to set
	 */
	public void setContainsNumber(String containsNumber) {
		this.containsNumber = containsNumber;
	}


	/**
	 * @return the contiainsUppercase
	 */
	public String getContainsUppercase() {
		return containsUppercase;
	}


	/**
	 * @param contiainsUppercase the contiainsUppercase to set
	 */
	public void setContainsUppercase(String contiainsUppercase) {
		this.containsUppercase = contiainsUppercase;
	}


	/**
	 * @return the containsLowercase
	 */
	public String getContainsLowercase() {
		return containsLowercase;
	}


	/**
	 * @param containsLowercase the containsLowercase to set
	 */
	public void setContainsLowercase(String containsLowercase) {
		this.containsLowercase = containsLowercase;
	}


	/**
	 * @return the passwordsMatch
	 */
	public String getPasswordsMatch() {
		return passwordsMatch;
	}


	/**
	 * @param passwordsMatch the passwordsMatch to set
	 */
	public void setPasswordsMatch(String passwordsMatch) {
		this.passwordsMatch = passwordsMatch;
	}
	
	
}
