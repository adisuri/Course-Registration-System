/**
 * 
 */
package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.constants.SQLQueriesConstanst;
import com.flipkart.exception.GradeAddFailedException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.ProfessorOperation;
import com.flipkart.utils.DBUtil;

/**
 * @author prafu
 *
 */
public class ProfessorDAOOperation implements ProfessorDAOInterface{
	
	private static volatile ProfessorDAOOperation instance = null;
	private static Logger logger = Logger.getLogger(ProfessorDAOOperation.class);
	
	private ProfessorDAOOperation()
	{
		
	}
	
	/**
	 * Method to make UserDAOImpl Singleton
	 * @return
	 */
	public static ProfessorDAOOperation getInstance(){
		
		if(instance == null){
			synchronized(ProfessorDAOOperation.class){
				instance= new ProfessorDAOOperation();
			}
		}
		return instance;
	}
	
	

	@Override
	public boolean addGrade(String studentRollNo, String courseCode, Grade grade) throws GradeAddFailedException {
		Connection connection = DBUtil.getConnection();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstanst.ADD_GRADE_FOR_STUDENT);
			
			preparedStatement.setString(1,grade.getGrade());
			preparedStatement.setString(2,studentRollNo);
			preparedStatement.setString(3,courseCode);
			
			int rows = preparedStatement.executeUpdate();
			
			if(rows > 0) {
				logger.info("Grade Updated for Student -> " + studentRollNo);
				return true;
			}
			else{
				throw new GradeAddFailedException(studentRollNo);
			}
			
		}
		catch(SQLException ex){
			logger.error("SQL Exception Thrown : "+ ex.getMessage());
		}
		return false;
	}

	@Override
	public List<RegisteredCourse> viewRegisteredStudents(String proffId) {
		Connection connection = DBUtil.getConnection();
		List<RegisteredCourse> registeredStudentsUnderProff = new ArrayList<RegisteredCourse>();
		
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstanst.GET_REGISTERED_STUDENTS_FOR_PROFF);
			
			preparedStatement.setString(1,proffId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				registeredStudentsUnderProff.add(new RegisteredCourse(resultSet.getString("courseCode"),resultSet.getString("studentId"),resultSet.getInt("semester"),null));
			}
			
		}
		catch(SQLException ex){
			logger.error("SQL Exception Thrown : "+ ex.getMessage());
		}
		
		return registeredStudentsUnderProff;
	}
	

	@Override
	public List<Course> viewProfessorCourses(String proffId) {
		
		Connection connection = DBUtil.getConnection();
		List<Course> coursesByProff = new ArrayList<Course>();
		
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstanst.GET_PROFF_COURSES);
			
			preparedStatement.setString(1,proffId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				coursesByProff.add(new Course(resultSet.getString("cCode"),resultSet.getString("cName"),resultSet.getString("instructor"),resultSet.getBoolean("isOffered"),resultSet.getInt("courseSeats")));
			}
			
		}
		catch(SQLException ex){
			logger.error("SQL Exception Thrown : "+ ex.getMessage());
		}
		
		return coursesByProff;
	}
	
	

	@Override
	public Professor getProffProfleById(String profId) throws UserNotFoundException {
		
		Connection connection = DBUtil.getConnection();
		
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstanst.GET_PROFF_DETAILS);
			
			preparedStatement.setString(1,profId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				return new Professor(resultSet.getString("department"),resultSet.getString("designation"));
			}else {
				throw new UserNotFoundException(profId);
			}
			
		}
		catch(SQLException ex){
			logger.error("SQL Exception Thrown : "+ ex.getMessage());
		}
		
		return null;
	}

}
