/**
 * 
 */
package com.flipkart.exception;

/**
 * User not found exception
 * @author JEDI-07
 */
public class UserNotFoundException extends Exception {
	
	private String uName;

	/**
	 * Constructor
	 * @param uName
	 */
	public UserNotFoundException(String uName) {
		this.uName = uName;
	}

	/**
	 * Message thrown by exception
	 * @return String: Error Message
	 */
	public String getMessage() {
		return "User with userId: " + uName + " not found.";
	}
}
