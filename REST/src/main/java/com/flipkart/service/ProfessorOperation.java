/**
 * 
 */
package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.dao.ProfessorDAOInterface;
import com.flipkart.dao.ProfessorDAOOperation;
import com.flipkart.dao.UserDAOOperation;
import com.flipkart.exception.GradeAddFailedException;
import com.flipkart.exception.UserNotFoundException;

/**
 * @author JEDI-7
 *
 */
public class ProfessorOperation implements ProfessorInterface{
	
	private static volatile ProfessorOperation instance = null;
	ProfessorDAOInterface proffDaoOperation= ProfessorDAOOperation.getInstance();
		
	
	private ProfessorOperation()
	{
		
	}
	
	/**
	 * Method to make UserDAOImpl Singleton
	 * @return
	 */
	public static ProfessorOperation getInstance(){
		
		if(instance == null){
			synchronized(ProfessorOperation.class){
				instance= new ProfessorOperation();
			}
		}
		return instance;
	}

	
	
	
	@Override
	public boolean addGrade(String studentRollNo, String courseCode, Grade grade) throws GradeAddFailedException {
		return proffDaoOperation.addGrade(studentRollNo,courseCode,grade);
	}

	@Override
	public List<RegisteredCourse> viewRegisteredStudents(String proffId) {
		return proffDaoOperation.viewRegisteredStudents(proffId);	
	}

	@Override
	public List<Course> viewProfessorCourses(String proffId) {
		return proffDaoOperation.viewProfessorCourses(proffId);	
	}

	@Override
	public Professor getProffProfleById(String profId) throws UserNotFoundException {
		return proffDaoOperation.getProffProfleById(profId);	
	}

}
