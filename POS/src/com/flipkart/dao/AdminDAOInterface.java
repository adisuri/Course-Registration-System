/**
 * 
 */
package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.CourseAlreadyInCatalogException;
import com.flipkart.exception.CourseNotInCatalogException;
import com.flipkart.exception.CourseNotRemovedException;
import com.flipkart.exception.ProfessorAdditionFailedException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.exception.UserCannotBeAddedException;
import com.flipkart.exception.UserNameAlreadyInUseException;
import com.flipkart.exception.UserNotFoundException;

/**
 * @author JEDI-7
 *
 */
public interface AdminDAOInterface {

	/**
	 * Method to Add Professor
	 * @param professor : Professor to be Added
	 * @throws ProfessorAdditionFailedException
	 * @throws UserNameAlreadyInUseException
	 */
	public void addProfessor(Professor professor)
			throws ProfessorAdditionFailedException, UserNameAlreadyInUseException;

	/**
	 * Method to Add User
	 * @param user : User to be Added
	 * @throws UserCannotBeAddedException
	 * @throws UserNameAlreadyInUseException
	 */
	public void addUser(User user) throws UserCannotBeAddedException, UserNameAlreadyInUseException;

	/** 
	 * Method to Approve Student
	 * @param studentId : Student to be added
	 * @throws StudentNotFoundForApprovalException
	 */
	public void approveStudents(String studentId) throws StudentNotFoundForApprovalException;

	/**
	 * Method to Add Course
	 * @param course : Course to be added
	 * @throws CourseAlreadyInCatalogException
	 */
	public void addCourse(Course course) throws CourseAlreadyInCatalogException;

	/**
	 * Method To Delete Course
	 * @param CourseCode : Course to be Deleted
	 * @throws CourseNotRemovedException
	 * @throws CourseNotInCatalogException
	 */
	public void deleteCourse(String CourseCode) throws CourseNotRemovedException, CourseNotInCatalogException;
	
	/**
	 * Method to Assign a Professor to a Course
	 * @param CourseCode : Course to be Assigned
	 * @param professorId : Professor to Assign the Course
	 * @throws CourseNotInCatalogException
	 * @throws UserNotFoundException
	 */
	public void assignProfessor(String CourseCode, String professorId)
			throws CourseNotInCatalogException, UserNotFoundException;

	/**
	 * Method to View Courses
	 * @return List of Courses
	 */
	public List<Course> viewCourses();

	/**
	 * Method to Show all the Professors
	 * @return List of Professor
	 */
	public List<Professor> showProfessors();

	/**
	 * Method to Show all the students pending Approval from Admin
	 * @return List of such students pending Approval from Admin
	 */
	public List<Student> viewPendingStudents();

	
	/**
	 * Method to Generate Report Card
	 * @return A list of Courses with Grades respectively
	 */
	public List<RegisteredCourse> generateReportCard(String studentId);
}
