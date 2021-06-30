/**
 * 
 */
package com.flipkart.exception;

/**
 * Course registration failed exception
 * @author JEDI-07
 */
public class CourseRegistrationFailedException extends Exception {

	private String cCode;

	/**
	 * Constructor
	 * @param String: course code
	 */
	public CourseRegistrationFailedException(String cCode) {
		this.cCode = cCode;
	}

	/**
	 * Getter method
	 * @return String: course code
	 */
	public String getCourseCode() {
		return cCode;
	}

	/**
	 * Message returned when exception is thrown
	 * @return String: Error Message
	 */
	@Override
	public String getMessage() {
		return "You have already registered for " + cCode;
	}
}
