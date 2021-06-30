/**
 * 
 */
package com.flipkart.exception;

/**
 * Course limit crossed exception
 * @author JEDI-07
 */
public class CourseLimitCrossed extends Exception {
	private int no;

	/**
	 * Assigns number of courses offered
	 * @param no : number of courses
	 */
	public CourseLimitCrossed(int no) {
		this.no = no;
	}

	/**
	 * Message returned when exception is thrown
	 * @return Error Message
	 * */
	@Override
	public String getMessage() 
	{
		return "Course Limit Exceeded! Cannot add more courses.";
	}

}
