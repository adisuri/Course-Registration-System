/**
 * 
 */
package com.flipkart.exception;

/**
 * User disapproved exception
 * @author JEDI-07
 */
public class UserDisaprovedException extends Exception {
	private String uName;
	
	/**
	 * Constructor
	 * @param String: username
	 */
	public UserDisaprovedException(String uName) {
		this.uName = uName;
	}
	
	/**
	 * Message returned when exception is thrown
	 * @return String: Error Message
	 */
	public String getMessage() {
		return "User with userId: " + uName + " not Aprooved.";
	}
}
