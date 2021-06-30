/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
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
			throws CourseNotInCatalogException, SeatNotAvailableException,CourseLimitCrossed,SQLException {
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
//			else if(!StudentValidator.isValidCourseCode(courseCode, availableCourseList))
//			{
//				throw new CourseNotFoundException(courseCode);
//			}
			
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
		
	}

	@Override
	public boolean dropCourse(String cCode, String studentId, List<Course> registeredCourseList,int sem)
			throws CourseNotRemovedException,SQLException {
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
			return registrationDAOInterface.dropCourse(studentId, sem, cCode);
		}
		
//		return false;
	}

	@Override
	public List<Course> viewCourses(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		
		return registrationDAOInterface.viewCourses(studentId);
	}
	
	@Override
	public List<Course> viewRegisteredCourses(String studentId) throws SQLException {
			return registrationDAOInterface.viewRegisteredCourses(studentId);
		
	}

	@Override
	public List<RegisteredCourse> viewReportCard(String studentId) throws SQLException{
		return registrationDAOInterface.viewReportCard(studentId);
	}

	@Override
	public int calculateFee(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		return registrationDAOInterface.calculateFee(studentId);
	}

	@Override
	public int getRegistrationStatus(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		return registrationDAOInterface.getRegistrationStatus(studentId);
	}

	@Override
	public void setRegistrationStatus(String studentId) throws SQLException{
		// TODO Auto-generated method stub
		registrationDAOInterface.setRegistrationStatus(studentId);
		
	}

}
