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
import com.flipkart.bean.RegisteredCourse;
//import com.flipkart.constant.Grade;
//import com.flipkart.constant.ModeOfPayment;
//import com.flipkart.constant.NotificationType;
import com.flipkart.constants.SQLQueriesConstanst;
import com.flipkart.service.RegistrationOperation;
import com.flipkart.utils.DBUtil;

/**
 * @author JEDI-7
 * Class to implement Registration Dao Operations
 * This class communicates with the database.
 */
public class RegistrationDAOOperation implements RegistrationDAOInterface{
	
	private static volatile RegistrationDAOOperation instance=null;
	private static Logger logger = Logger.getLogger(RegistrationDAOOperation.class);
	private PreparedStatement stmt = null;
	
	StudentDAOInterface studentDaoInterface = StudentDAOOperation.getInstance();
	
	private RegistrationDAOOperation()
	{
		
	}
	/**
	 * Method to make RegistrationDAOOperation Singleton
	 * @return
	 */
	public static RegistrationDAOOperation getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(RegistrationDAOOperation.class){
				instance=new RegistrationDAOOperation();
			}
		}
		return instance;
	}


	/**
	 * DONE
	 * Method to add course in database
	 * @param courseCode
	 * @param studentId
	 * @return boolean whether course got added successfully
	 * @throws SQLException
	 */
	@Override
	public boolean addCourse( String studentId,int semester, String courseCode) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		
		try 
		{
			stmt = conn.prepareStatement(SQLQueriesConstanst.ADD_COURSE);
			stmt.setString(1, studentId);
			stmt.setInt(2, semester);
			stmt.setString(3, courseCode);

			stmt.executeUpdate();
			
			stmt = conn.prepareStatement(SQLQueriesConstanst.DECREMENT_COURSE_SEATS);
			stmt.setString(1, courseCode);
			stmt.executeUpdate();
			return true;
		}
		catch (SQLException e) 
		{
			logger.error(e.getMessage());
		}
		finally
		{
			stmt.close();
//			conn.close();
		}
		return false;

	}

	/**
	 * 
	 * Drop Course selected by student
	 * @param courseCode
	 * @param studentId
	 * @param semester
	 * @return boolean whether course got added successfully
	 * @throws SQLException
	 */
	@Override
	public boolean dropCourse(String studentId,int semester, String courseCode) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		
		
		try
		{
			stmt = conn.prepareStatement(SQLQueriesConstanst.DROP_COURSE_QUERY);
			stmt.setString(1, courseCode);
			stmt.setString(2, studentId);
			stmt.setInt(3, semester);
			stmt.execute();
			
			stmt = conn.prepareStatement(SQLQueriesConstanst.INCREMENT_SEAT_QUERY);
			stmt.setString(1, courseCode);
			stmt.execute();
			
			stmt.close();
			
			return true;
		}
		catch(Exception e)
		{
			logger.error("Exception found" + e.getMessage());
		}
		finally
		{

			stmt.close();
//			conn.close();
		}
		
		return false;
	}

	
	@Override
	public List<Course> viewCourses(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		List<Course> availableCourseList = new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		
		try 
		{
			PreparedStatement preaparedstatement = connection.prepareStatement(SQLQueriesConstanst.VIEW_AVAILABLE_COURSES);
			preaparedstatement.setString(1, studentId);
			preaparedstatement.setBoolean(2, true);
			ResultSet rs = preaparedstatement.executeQuery();
 
			while (rs.next()) {
				availableCourseList.add(new Course(rs.getString("cCode"), rs.getString("cName"),
						rs.getString("instructor"), rs.getBoolean("isoffered"),rs.getInt("courseSeats")));
 
			}
			
 
		} 
		catch (SQLException e) 
		{
			logger.error(e.getMessage());
		} 
		catch (Exception e)
		{
			logger.error(e.getMessage());
		}
		finally
		{
//			preaparedstatement.close();
//			connection.close();
		}
		
		return availableCourseList;
	}

	
	@Override
	public List<Course> viewRegisteredCourses(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		List<Course> registeredCourseList = new ArrayList<>();
		try 
		{
			stmt = conn.prepareStatement(SQLQueriesConstanst.VIEW_REGISTERED_COURSES);
			stmt.setString(1, studentId);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				registeredCourseList.add(new Course(rs.getString("cCode"), rs.getString("cName"),
						rs.getString("instructor"), rs.getBoolean("isOffered"),rs.getInt("courseSeats")));

			}
		} 
		catch (SQLException e) 
		{
			logger.error(e.getMessage());

		} 
		finally
		{
			stmt.close();
//			conn.close();
		}
		
		return registeredCourseList;
	}

	@Override
	public List<RegisteredCourse> viewReportCard(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		List<RegisteredCourse> registeredStudentsUnderProff = new ArrayList<RegisteredCourse>();
		Connection conn = DBUtil.getConnection();

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(SQLQueriesConstanst.VIEW_REPORT_CARD);
			
			
			preparedStatement.setString(1, studentId);
			

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				registeredStudentsUnderProff.add(new RegisteredCourse(resultSet.getString("courseCode"),
						resultSet.getString("studentId"), resultSet.getInt("semester"), new Grade(resultSet.getString("grade"))));
			}

		} catch (SQLException ex) {
			logger.error("SQL Exception Thrown : " + ex.getMessage());
		}

		return registeredStudentsUnderProff;
	}

	@Override
	public int calculateFee(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		int fee = 0;
		try
		{
			stmt = conn.prepareStatement(SQLQueriesConstanst.CALCULATE_FEES);
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			fee = rs.getInt(1);
		}
		catch(SQLException e)
		{
			logger.error(e.getErrorCode());
			logger.error(e.getMessage());
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		finally
		{
			stmt.close();
//			conn.close();
		}
		
		return fee;
	}

	@Override
	public boolean seatAvailable(String courseCode) throws SQLException {
		// TODO Auto-generated method stub
		
	Connection conn = DBUtil.getConnection();
		
		try
		{
			PreparedStatement preaparedstatement = conn.prepareStatement(SQLQueriesConstanst.GET_SEATS);
			preaparedstatement.setString(1, courseCode);
			ResultSet rs = preaparedstatement.executeQuery();
			while (rs.next()) {
				return (rs.getInt("courseSeats") > 0);
			}

		}
		catch (SQLException e) {
			logger.error(e.getMessage());
		}
		finally
		{
//			stmt.close();
//			conn.close();
		}
		
		return true;
	}

	@Override
	public int numOfRegisteredCourses(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		int course_count=0;
		try
		{
			stmt = conn.prepareStatement(SQLQueriesConstanst.NUM_OF_REGISTERED_COURSES);
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				course_count++;
			}
			return course_count;
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		
		return course_count;
	}

	@Override
	public boolean isRegistered(String courseCode, String studentId) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection conn = DBUtil.getConnection();
		
		boolean check = false;
		try
		{
			PreparedStatement preaparedstatement = conn.prepareStatement(SQLQueriesConstanst.IS_REGISTERED);
			preaparedstatement.setString(1, courseCode);
			preaparedstatement.setString(2, studentId);
			ResultSet rs = preaparedstatement.executeQuery();
			while(rs.next())
			{
				check = true;
			}
			return check;
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		
		return check;
	}

	@Override
	public int getRegistrationStatus(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = DBUtil.getConnection();
		int status=0;
		try 
		{
			PreparedStatement preaparedstatement = connection.prepareStatement(SQLQueriesConstanst.GET_REGISTER_STATUS);
			preaparedstatement.setString(1, studentId);
			ResultSet rs = preaparedstatement.executeQuery();
			rs.next();
			status=rs.getInt("approved");
		} 
		catch(SQLException e){
			logger.error(e.getMessage());
		}
		
		return status;
	}


	@Override
	public void setRegistrationStatus(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = DBUtil.getConnection();
		try 
		{
			PreparedStatement preaparedstatement = connection.prepareStatement(SQLQueriesConstanst.SET_REGISTRATION_STATUS);
			preaparedstatement.setString(1, studentId);
			preaparedstatement.executeUpdate();
		}
		catch (SQLException e) 
		{
			logger.error(e.getMessage());

		} 
		finally
		{
			stmt.close();
//			conn.close();
		}
	}
	
	
	
	

	
	
}
