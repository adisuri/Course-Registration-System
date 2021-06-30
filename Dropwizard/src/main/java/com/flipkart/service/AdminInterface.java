/**
 * 
 */
package com.flipkart.service;

import java.util.List;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseAlreadyInCatalogException;
import com.flipkart.exception.CourseNotInCatalogException;
import com.flipkart.exception.CourseNotRemovedException;
import com.flipkart.exception.ProfessorAdditionFailedException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.exception.UserNameAlreadyInUseException;
import com.flipkart.exception.UserNotFoundException;

/**
 * @author JEDI-7
 *
 */
public interface AdminInterface {
	
	/**
	 * Method to Add New Professor to the Database
	 * @param professor
	 * @throws ProfessorAdditionFailedException
	 * @throws UserNameAlreadyInUseException
	 */
	public void addProfessor(Professor professor) throws ProfessorAdditionFailedException,UserNameAlreadyInUseException;	
	
	/**
	 * Method to Approve new Students
	 * @param studendId
	 * @param studentList : list of Students
	 * @throws CourseAlreadyInCatalogException
	 */
	public void approveStudents(String studendId, List<Student> studentList) throws StudentNotFoundForApprovalException;
	
	/**
	 * Method to add new Course to the Database
	 * @param course : object of Course
	 * @param courseList : list of courses
	 * @throws CourseAlreadyInCatalogException
	 */
	public void addCourse(Course course, List<Course> courseList) throws CourseAlreadyInCatalogException;
	
	/**
	 * Method to delete a Course from the Database
	 * @param courseCode
	 * @param courseList : list of courses
	 * @throws CourseNotRemovedException
	 * @throws CourseNotInCatalogException
	 */
	public void deleteCourse(String CourseCode, List<Course> courseList)throws CourseNotRemovedException,CourseNotInCatalogException;
	
	/**
	 * Method to assign a professor to a course 
	 * @param CourseCode
	 * @param professorId
	 * @throws CourseNotInCatalogException
	 * @throws UserNotFoundException
	 */
	public void assignProfessor(String CourseCode, String professorId) throws CourseNotInCatalogException,UserNotFoundException;
	
	/**
	 * Method to view all courses
	 * @return List<Course> list of courses
	 */
	public List<Course> viewCourses();
	
	/**
	 * Method to view all Professor
	 * @return List<Professor> list of all the professors
	 */
	public List<Professor> showProfessors();
	
	/**
	 * Method to view all pending Student
	 * @return List<Student> : list of students
	 */
	public List<Student> viewPendingStudents();
	
	
	/**
	 * Method to view all courses
	 * @return List<RegisteredCourse> : list of Registered Courses
	 */
	public List<RegisteredCourse> generateReportCard(String studentId);	

}
