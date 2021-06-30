/**
 * 
 */
package com.flipkart.exception;

/**
 * Course not in catalog exception
 * @author JEDI-07
 */
public class CourseNotInCatalogException extends Exception{
		private String cCode;
	
		/**
		 * Constructor
		 * @param String: course code
		 */
		public CourseNotInCatalogException(String cCode)
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
			return "Course with courseCode: " + cCode + " can not be offered to student.";
		}
}
