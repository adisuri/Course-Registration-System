/**
 * 
 */
package com.flipkart.exception;

/**
 * Course already in catalog exception
 * @author JEDI-7
 */
public class CourseAlreadyInCatalogException extends Exception {
	
	private String cCode;
	
	/***
	 * Constructor
	 * @param String: Course code
	 */
	public CourseAlreadyInCatalogException(String cCode) {
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
	 * @return Exception message to be thrown
	 */
	@Override
	public String getMessage() {
		return "Course with cCode: " + cCode + " already present in catalog.";
	}
}
