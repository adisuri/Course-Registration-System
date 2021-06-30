/**
 * 
 */
package com.flipkart.service;

import java.util.UUID;

import com.flipkart.bean.Student;
import com.flipkart.exception.StudentNotRegisteredException;
import com.flipkart.exception.UserNameAlreadyInUseException;

/**
 * @author JEDI-7
 *
 */
public interface StudentInterface{
	
	
	
	
	/**
	 * @param student
	 * @return success/failure integer flag
	 * @throws StudentNotRegisteredException
	 * @throws UserNameAlreadyInUseException
	 */
	public int addStudent(Student student) throws StudentNotRegisteredException, UserNameAlreadyInUseException;
	
	/**
	 * @param uId : uuid for student
	 * @return student id string
	 */
	public String getStudentId(UUID uId);
	
    	
	/**
	 * @param sId : student id
	 * @return boolean : is student is approved or not
	 */
	public boolean isApproved(String sId);
}
