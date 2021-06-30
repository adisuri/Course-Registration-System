/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.exception.CourseLimitCrossed;
import com.flipkart.exception.CourseNotInCatalogException;
import com.flipkart.exception.CourseNotRemovedException;
import com.flipkart.exception.SeatNotAvailableException;


/**
 * @author JEDI-7
 *
 */
public interface RegistrationInterface {
	
	/**
	 * @param cCode
	 * @param studentId
	 * @param courseList
	 * @return boolean : if Course added successfully
	 * @throws CourseLimitCrossed 
	 * @throws CourseNotInCatalogException 
	 * @throws SeatNotAvailableException 
	 * @throws CourseLimitCrossed 
	 */
	public boolean addCourse(String cCode, String studentId, List<Course> courseList,int sem,int type) throws CourseNotInCatalogException,SeatNotAvailableException, CourseLimitCrossed;
	
	/**
	 * @param cCode : course code
	 * @param studentId : student id
	 * @param registeredCourseList
	 * @return boolean if Course deleted successfully
	 * @throws CourseNotRemovedException 
	 */
	public boolean dropCourse(String cCode,String studentId, List<Course> registeredCourseList,int sem) throws CourseNotRemovedException;
	
	/**
	 * @param studentId : student id
	 * @return List of courses
	 */
	public List<Course> viewCourses(String studentId);
	
	/**
	 * @param studentId
	 * @return List of courses that student has registered in
	 */	
	public List<Course> viewRegisteredCourses(String studentId);
	
	/**
	 * @param studentId : student id
	 * @return List of RegisteredCourse as it has grade and semester
	 */
	public List<RegisteredCourse> viewReportCard(String studentId);
	
	/**
	 * @param sRollNo : student id
	 * @return double calculated fee
	 */
	public int calculateFee(String studentId);
	
	/**
	 * @param studentId : student id
	 * @return boolean whether Registration was approved successfully
	 */
	public int getRegistrationStatus(String studentId);
	
	/**
	 * @param studentId : student id
	 */
	public void setRegistrationStatus(String studentId);

	
	
}