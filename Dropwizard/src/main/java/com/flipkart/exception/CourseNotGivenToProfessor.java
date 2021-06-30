/**
 * 
 */
package com.flipkart.exception;

/**
 * Course not given to professor exception
 * @author JEDI-07
 */
public class CourseNotGivenToProfessor extends Exception {
	private String cCode;
	private String profuName;
	
	/**
	 * Constructor
	 * @param String: course code
	 * @param String: professor name
	 */
	public CourseNotGivenToProfessor(String cCode, String profuName) {
		this.cCode = cCode;
		this.profuName = profuName;
	}
	
	/**
	 * Message returned when exception is thrown
	 * @return String: Error Message
	 */
	public String getMessage() {
		return "courseCode: " + cCode + " and professorId: " + profuName + " mismatch!";
	}
	

}
