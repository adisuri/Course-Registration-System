/**
 * 
 */
package com.flipkart.exception;

/**
 * Exception to check if Professor is added to database or not.
 * @author JEDI-07
 */
public class ProfessorAdditionFailedException extends Exception{
	
	private String proffuserName;

	/**
	 * Constructor
	 * @param proffId
	 */
	public ProfessorAdditionFailedException(String proffId) {
		this.proffuserName = proffId;
	}

	/**
	 * Gets the professor name
	 * @return String: the professor id
	 */
	public String getProffId() {
		return proffuserName;
	}
	
	/*
	 * Message returned when exception is thrown
	 * @return String: Error Message
	 * */
	@Override
	public String getMessage() {
		return "Can not add professor " + proffuserName + " to database";
	}

}
