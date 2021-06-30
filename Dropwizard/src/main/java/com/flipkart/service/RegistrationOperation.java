/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.dao.AdminDAOOperation;
import com.flipkart.dao.RegistrationDAOInterface;
import com.flipkart.dao.RegistrationDAOOperation;
import com.flipkart.dao.StudentDAOInterface;
import com.flipkart.dao.StudentDAOOperation;
import com.flipkart.exception.CourseLimitCrossed;
import com.flipkart.exception.CourseNotInCatalogException;
import com.flipkart.exception.CourseNotRemovedException;
import com.flipkart.exception.SeatNotAvailableException;

/**
 * @author JEDI-7
 *
 */
public class RegistrationOperation implements RegistrationInterface{
	
	
	Logger logger = Logger.getLogger(RegistrationOperation.class);
	private static volatile RegistrationOperation instance=null;
	RegistrationDAOInterface registrationDAOInterface = RegistrationDAOOperation.getInstance();
	
	StudentDAOInterface studentDaoInterface = StudentDAOOperation.getInstance();
	
	private RegistrationOperation()
	{
		
	}
	/**
	 * Method to make RegistrationOperation Singleton
	 * @return
	 */
	public static RegistrationOperation getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(RegistrationOperation.class){
				instance=new RegistrationOperation();
			}
		}
		return instance;
	}
	
	
	@Override
	public boolean addCourse(String cCode, String studentId, List<Course> courseList,int sem,int type)
			throws CourseNotInCatalogException, SeatNotAvailableException,CourseLimitCrossed{
		// TODO Auto-generated method stub
		boolean check=true;
		for(Course course : courseList)
		{
			if(cCode.equalsIgnoreCase(course.getcCode())) 
			{
				check=false; 
				break;
			}
		}
		if(check) {
			throw new CourseNotInCatalogException(cCode);
		}
		try {
			if(type==0) {
				if (registrationDAOInterface.numOfRegisteredCourses(studentId) >= 4)
				{	
					throw new CourseLimitCrossed(4);
				}
				else if (registrationDAOInterface.isRegistered(cCode, studentId)) 
				{
					return false;
				} 
				else if (!registrationDAOInterface.seatAvailable(cCode)) 
				{
					throw new SeatNotAvailableException(cCode);
				} 
//				else if(!StudentValidator.isValidCourseCode(courseCode, availableCourseList))
//				{
//					throw new CourseNotFoundException(courseCode);
//				}
				
				return registrationDAOInterface.addCourse(studentId, sem,cCode);
			}
			else {
				if (registrationDAOInterface.numOfRegisteredCourses(studentId) >= 6)
				{	
					throw new CourseLimitCrossed(6);
				}
				else if (registrationDAOInterface.isRegistered(cCode, studentId)) 
				{
					return false;
				} 
				else if (!registrationDAOInterface.seatAvailable(cCode)) 
				{
					throw new SeatNotAvailableException(cCode);
				} 
				return true;
			}
		}catch (SQLException e) {
			logger.info(e.getMessage());
			return false;
		}
		
	}

	@Override
	public boolean dropCourse(String cCode, String studentId, List<Course> registeredCourseList,int sem)
			throws CourseNotRemovedException {
		// TODO Auto-generated method stub
		boolean check=true;
		for(Course course : registeredCourseList)
		{
			if(cCode.equalsIgnoreCase(course.getcCode())) 
			{
				check=false; 
				break;
			}
		}
		
		if(check) {
			
			throw new CourseNotRemovedException(cCode);
		}
		else {
			try {
				return registrationDAOInterface.dropCourse(studentId, sem, cCode);
			} catch (SQLException e) {
				logger.info(e.getMessage());
				throw new CourseNotRemovedException(cCode);
			}
		}
		
//		return false;
	}

	@Override
	public List<Course> viewCourses(String studentId){
		// TODO Auto-generated method stub
		
		try {
			return registrationDAOInterface.viewCourses(studentId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Course> viewRegisteredCourses(String studentId){
			try {
				return registrationDAOInterface.viewRegisteredCourses(studentId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		
	}

	@Override
	public List<RegisteredCourse> viewReportCard(String studentId){
		try {
			return registrationDAOInterface.viewReportCard(studentId);
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return null;
	}

	@Override
	public int calculateFee(String studentId) {
		// TODO Auto-generated method stub
		try {
			return registrationDAOInterface.calculateFee(studentId);
		} catch (SQLException e) {
			logger.info(e.getMessage());
			
		}
		return -1;
	}

	@Override
	public int getRegistrationStatus(String studentId) {
		// TODO Auto-generated method stub
		try {
			return registrationDAOInterface.getRegistrationStatus(studentId);
		} catch (SQLException e) {
			logger.info(e.getMessage());
			
		}
		return -1;
	}

	@Override
	public void setRegistrationStatus(String studentId){
		// TODO Auto-generated method stub
		try {
			registrationDAOInterface.setRegistrationStatus(studentId);
		} catch (SQLException e) {
			logger.info(e.getMessage());
		
		}
		
	}

}
