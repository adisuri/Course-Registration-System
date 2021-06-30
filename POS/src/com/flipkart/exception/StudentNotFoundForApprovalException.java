/**
 * 
 */
package com.flipkart.exception;

/**
 * Student not found for approval exception
 * @author JEDI Group 7
 */
public class StudentNotFoundForApprovalException extends Exception{	
	private String studentID;
	
	/**
	 * Constructor
	 * @param String: student id
	 * */
	public StudentNotFoundForApprovalException(String studentID) {
		this.studentID = studentID;
	}
	
	/**
	 * Getter function for studentId
	 * @return String: student id
	 */
	public String getStudentID() {
		return this.studentID;
	}
	
	/**
	 * Message returned when exception is thrown
	 * @return String: getMessage
	 */
	
	@Override
	public String getMessage() {
		return "Student id: " + this.studentID + " not found for approval";
	}
}
