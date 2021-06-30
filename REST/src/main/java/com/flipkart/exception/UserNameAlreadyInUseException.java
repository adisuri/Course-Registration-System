/**
 * 
 */
package com.flipkart.exception;

/**
 * User already in use exception
 * @author JEDI-07
 */
public class UserNameAlreadyInUseException extends Exception{
	private String uName;
	
	/**
	 * Construtor
	 * @param String: username
	 * */
	public UserNameAlreadyInUseException(String uName) {
		this.uName = uName;
	}
	
	/**
	 * Returns the username
	 * @return username
	 * */
	public String getUName() {
		return uName;
	}
	
	/**
	 * Message returned when exception is thrown
	 * @return String: Error Message
	 * */
	@Override
	public String getMessage() {
		return "userName: " + uName + " is already in use.";
	}
}
