/**
 * 
 */
package com.flipkart.exception;

/**
 * Student not registered exception
 * @author JEDI-07
 */
public class StudentNotRegisteredException extends Exception{

	private String userName;
	
	/**
	 * Constructor
	 * @param String: userName
	 */
	public StudentNotRegisteredException(String userName) {
		this.userName = userName;
	}
	
	/**
	 * getter function for studentName
	 * @return String: student name
	 */
	public String getStudentName() {
		return this.userName;
	}
	
	/**
	 * Message returned when exception is thrown
	 * @return String: Error Message
	 */
	@Override
	public String getMessage() {
		return "Can not add student " + userName + " to database";
	}
	
}
