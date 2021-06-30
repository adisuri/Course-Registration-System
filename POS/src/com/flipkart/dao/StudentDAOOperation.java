/**
 * 
 */
package com.flipkart.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.flipkart.bean.Student;
//import com.flipkart.client.CRSApplication;
import com.flipkart.constants.SQLQueriesConstanst;
import com.flipkart.exception.StudentNotRegisteredException;
import com.flipkart.exception.UserNameAlreadyInUseException;
//import com.flipkart.service.StudentOperation;
import com.flipkart.utils.DBUtil;


/**
 * @author JEDI-7
 * Class to implement Student DAO Operations
 *
 */
public class StudentDAOOperation implements StudentDAOInterface{

	private static volatile StudentDAOOperation instance=null;
	private static Logger logger = Logger.getLogger(StudentDAOOperation.class);
	/**
	 * Default Constructor
	 */
	private StudentDAOOperation()
	{
		
	}
	
	public static StudentDAOOperation getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(StudentDAOOperation.class){
				instance=new StudentDAOOperation();
			}
		}
		return instance;
	}
	
	/**
	 * Method to add student to database
	 * @param student: student object containing all the student fields
	 * @return boolean true if student is added, else false
	 * @throws StudentNotRegisteredException
	 */
	@Override
	public boolean addStudent(Student student) throws StudentNotRegisteredException,UserNameAlreadyInUseException {
		// TODO Auto-generated method stub
		Connection connection=DBUtil.getConnection();
		boolean added = false;
		try
		{
			//open DataBase Connection
			PreparedStatement preparedStatement=connection.prepareStatement(SQLQueriesConstanst.ADD_USER_QUERY);
			preparedStatement.setString(1, student.getuId());
			preparedStatement.setString(2, student.getuName());
			preparedStatement.setString(3, student.getuPwd());
			preparedStatement.setDate(4,new Date(student.getuCrDate().getTime()));
			preparedStatement.setString(5,"STUDENT");

			int rowsAffected=preparedStatement.executeUpdate();
			if(rowsAffected==1)
			{
				//add the student record
				//"insert into student (userId,branchName,batch,isApproved) values (?,?,?,?)";
				PreparedStatement preparedStatementStudent;
				preparedStatementStudent=connection.prepareStatement(SQLQueriesConstanst.ADD_STUDENT,Statement.RETURN_GENERATED_KEYS);
				preparedStatementStudent.setString(1,student.getuName());
				preparedStatementStudent.setString(2, student.getsBranch());
				preparedStatementStudent.setBoolean(3, false);
				preparedStatementStudent.executeUpdate();
				ResultSet results=preparedStatementStudent.getGeneratedKeys();
				if(results.next()) {
//					studentId=results.getInt(1);
					added = true;
				}		
					
			}
			
			
			
		}
		catch(SQLException se) {
			logger.error(se.getMessage());
			throw new UserNameAlreadyInUseException(student.getuName());
		}
		catch(Exception ex)
		{
			throw new StudentNotRegisteredException(student.getuName());
		}
		return added;
	}

	/**
	 * Method to retrieve Student Id from User Id
	 * @param userId
	 * @return Student Id
	 */
	@Override
	public String getStudentId(String userId) {
		Connection connection=DBUtil.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstanst.GET_STUDENT_ID);
			statement.setString(1, userId);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next())
			{
				return rs.getString("userId");
			}
				
		}
		catch(SQLException e)
		{
				logger.error(e.getMessage());
		}
		
		return null;
	}

	/**
	 * Method to check if Student is approved
	 * @param studentId
	 * @return boolean indicating if student is approved
	 */
	@Override
	public boolean isApproved(String studentId) {
		// TODO Auto-generated method stub
		Connection connection=DBUtil.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstanst.IS_APPROVED);
			statement.setString(1, studentId);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next())
			{
				return rs.getBoolean("approved");
			}
				
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}
		
		return false;
	}
	
	
}
