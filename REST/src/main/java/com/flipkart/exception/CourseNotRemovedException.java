/**
 * 
 */
package com.flipkart.exception;

/**
 * Course not removed exception
 * @author JEDI-07
 */
public class CourseNotRemovedException extends Exception{
	private String cCode;
	
	/**
	 * Constructor
	 * @param String: course code
	 * */
	public CourseNotRemovedException(String cCode)
	{	
		this.cCode = cCode;
	}
	
	/**
	 * Message thrown by exception
	 * @return String: Error Message
	 */
	@Override
	public String getMessage() 
	{
		return "Course with courseCode: " + cCode + " can not be removed.";
	}
}
