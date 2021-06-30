/**
 * 
 */
package com.flipkart.exception;

/**
 * User cannot be added exception
 * @author JEDI-07
 */
public class UserCannotBeAddedException extends Exception {
	private String uName;
	
	/**
	 * Constructor
	 * @param String: username
	 * */
	public UserCannotBeAddedException(String uName) {
		this.uName = uName;
	}
	
	/**
	 * Getter function for uName
	 * @return String: username
	 */
	public String getUName() {
		return this.uName;
	}
	
	/**
	 * Message returned when exception is thrown
	 * @return String: Message
	 */
	@Override
	public String getMessage() {
		return "userName: " + uName + " is already in use and hence cannot be added";
	}
}
