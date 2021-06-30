/**
 * 
 */
package com.flipkart.exception;

/**
 * Exceptions to check if grade has been added student's course 
 * @author JEDI-07
 */
public class GradeAddFailedException extends Exception{
	
	private String sRollNo;

	/**
	 * Constructor
	 * @param sRollNo
	 */
	public GradeAddFailedException(String sRollNo) {
		this.sRollNo = sRollNo;
	}

	/**
	 * Return the student scholar number
	 * @return String: Scholar Number
	 */
	public String getRollNo() {
		return sRollNo;
	}
	
	/**
	 * Message returned when exception is thrown
	 * @return String: Message returned when exception is thrown
	 * */
	@Override
	public String getMessage() {
		return "Can not add Grade for sRollNo: " + sRollNo;
	}
	

}
