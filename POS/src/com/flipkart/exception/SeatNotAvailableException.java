/**
 * 
 */
package com.flipkart.exception;

/**
 * Exception to check if seat is available in a course or not
 * @author JEDI-07
 */
public class SeatNotAvailableException extends Exception{
	private String cCode;

	/**
	 * Constructor
	 * @param String: course code
	 */
	public SeatNotAvailableException(String cCode) {
		this.cCode = cCode;
	}

	/**
	 * Returns the course code
	 * @return String: course code
	 */
	public String getcCode() {
		return this.cCode;
	}
	
	/**
	 * Message returned when exception is thrown
	 * @return String: Error Message
	 * */
	public String getMessage() {
		return "Seat not available in course " + this.cCode;
	}
	

}
