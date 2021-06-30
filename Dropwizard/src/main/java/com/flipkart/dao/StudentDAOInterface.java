/**
 * 
 */
package com.flipkart.dao;

import com.flipkart.bean.Student;
import com.flipkart.exception.StudentNotRegisteredException;
import com.flipkart.exception.UserNameAlreadyInUseException;

/**
 * @author JEDI-7
 * Interface for Student Operations
 */
public interface StudentDAOInterface {
	
	/**
	 * Method To Add Student To Database
	 * @param student: student object containing all the student fields
	 * @throws StudentNotRegisteredException
	 * @throws UserNameAlreadyInUseException
	 * @return boolean true if student is added, else false
	 */
	public boolean addStudent(Student student) throws StudentNotRegisteredException,UserNameAlreadyInUseException;
	
	/**
	 * Method To Retrieve Student Id From User Id
	 * @param userId
	 * @return Student Id
	 */
	public String getStudentId(String userId);
	
	/**
	 * Method To Check If Student Is Approved
	 * @param studentId
	 * @return boolean Indicating If Student Is Approved
	 */
	public boolean isApproved(String studentId);
}
