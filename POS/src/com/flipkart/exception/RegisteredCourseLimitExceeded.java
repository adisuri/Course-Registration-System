/**
 * 
 */
package com.flipkart.exception;

/**
 * Exception to check weather the number of registered courses exceeded limit
 * @author JEDI-07
 */
public class RegisteredCourseLimitExceeded extends Exception {
	private int numCourses;

	/**
	 * Constructor
	 * @param integer: number of courses
 	 */
	public RegisteredCourseLimitExceeded(int numCourses )
	{	
		this.numCourses = numCourses;
	}

	/**
	 * Message returned when exception is thrown
	 * @return String: Error Message
	 */
	@Override
	public String getMessage() 
	{
		return "You have already registered in " + numCourses + " courses";
	}
}
