/**
 * 
 */
package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDAOInterface;
import com.flipkart.dao.AdminDAOOperation;
import com.flipkart.dao.NotificationDAOInterface;
import com.flipkart.dao.NotificationDAOOperation;
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
public class AdminOperation implements AdminInterface {

	
	
	private static volatile AdminOperation instance = null;
	AdminDAOInterface adminDAOOperation = AdminDAOOperation.getInstance();
		
	
	private AdminOperation()
	{
		
	}
	
	/**
	 * Method to make AdminOperation Singleton
	 * @return
	 */
	public static AdminOperation getInstance(){
		
		if(instance == null){
			synchronized(AdminOperation.class){
				instance= new AdminOperation();
			}
		}
		return instance;
	}
	
	
	

	public void addProfessor(Professor professor)
			throws ProfessorAdditionFailedException, UserNameAlreadyInUseException {

		try {
			adminDAOOperation.addProfessor(professor);
		} catch (ProfessorAdditionFailedException | UserNameAlreadyInUseException e) {
			throw e;
		}

	}

	public void approveStudents(String studentId, List<Student> studentList) throws StudentNotFoundForApprovalException {

		try {
			adminDAOOperation.approveStudents(studentId);
		} catch (StudentNotFoundForApprovalException e) {
			throw e;
		}
	}

	public void addCourse(Course newCourse, List<Course> courseList) throws CourseAlreadyInCatalogException {

		try {
			adminDAOOperation.addCourse(newCourse);
		} catch (CourseAlreadyInCatalogException e) {
			throw e;
		}
	}

	public void deleteCourse(String dropCourseCode, List<Course> courseList)
			throws CourseNotInCatalogException, CourseNotRemovedException {

		try {
			adminDAOOperation.deleteCourse(dropCourseCode);
		} catch (CourseNotInCatalogException | CourseNotRemovedException e) {
			throw e;
		}

	}

	public void assignProfessor(String CourseCode, String professorId)
			throws CourseNotInCatalogException, UserNotFoundException {
		try {
			adminDAOOperation.assignProfessor(CourseCode, professorId);
		} catch (CourseNotInCatalogException | UserNotFoundException e) {
			throw e;
		}
	}

	public List<Course> viewCourses() {

		return adminDAOOperation.viewCourses();

	}

	public List<Professor> showProfessors() {

		return adminDAOOperation.showProfessors();

	}
	
	public List<Student> viewPendingStudents() {
		return adminDAOOperation.viewPendingStudents();
	}
	
	public List<RegisteredCourse> generateReportCard(String studentId){
		return adminDAOOperation.generateReportCard(studentId);
	}
}
