/**
 * 
 */
package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.exception.GradeAddFailedException;
import com.flipkart.exception.UserNotFoundException;

/**
 * @author JEDI-7
 *
 */
public interface ProfessorInterface {
	
	
	/**
	 * @param studentRollNo
	 * @param courseCode
	 * @param grade
	 * @throws GradeAddFailedException
	 * @return boolean : status of add grade
	 */
	public boolean addGrade(String studentRollNo,String courseCode,Grade grade) throws GradeAddFailedException;
	
	/**
	 * @param proffId
	 * @return List<RegisteredCourse> : list of all Registered Students
	 */
	public List<RegisteredCourse> viewRegisteredStudents(String proffId);
	
	/**
	 * @param proffId
	 * @return List<Course> : list of all courses for professor
	 */
	public List<Course> viewProfessorCourses(String proffId);
	
	/**
	 * @param profId
	 * @throws UserNotFoundException
	 * @return Professor object
	 */
	public Professor getProffProfleById(String profId) throws UserNotFoundException;
	
	
}
