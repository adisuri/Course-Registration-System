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
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constants.SQLQueriesConstanst;
import com.flipkart.exception.CourseAlreadyInCatalogException;
import com.flipkart.exception.CourseNotInCatalogException;
import com.flipkart.exception.CourseNotRemovedException;
import com.flipkart.exception.ProfessorAdditionFailedException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.exception.UserCannotBeAddedException;
import com.flipkart.exception.UserNameAlreadyInUseException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.AdminOperation;
import com.flipkart.utils.DBUtil;

/**
 * @author JEDI-7
 *
 */
public class AdminDAOOperation implements AdminDAOInterface {

	private PreparedStatement statement = null;
	private static Logger logger = Logger.getLogger(AdminDAOOperation.class);	
	private static volatile AdminDAOOperation instance = null;
		
	
	private AdminDAOOperation()
	{
		
	}
	
	/**
	 * Method to make AdminOperation Singleton
	 * @return
	 */
	public static AdminDAOOperation getInstance(){
		
		if(instance == null){
			synchronized(AdminDAOOperation.class){
				instance= new AdminDAOOperation();
			}
		}
		return instance;
	}
	
	
	Connection connection = DBUtil.getConnection();

	/**
	 *
	 */
	public void addProfessor(Professor professor)
			throws ProfessorAdditionFailedException, UserNameAlreadyInUseException {
		try {

			this.addUser(professor);

		} catch (UserCannotBeAddedException e) {

			logger.error(e.getMessage());
			throw new ProfessorAdditionFailedException(professor.getuName());

		} catch (UserNameAlreadyInUseException e) {

			logger.error(e.getMessage());
			throw e;

		}

		statement = null;
		try {

			String sql = SQLQueriesConstanst.ADD_PROFESSOR_QUERY;
			statement = connection.prepareStatement(sql);

			statement.setString(1, professor.getpDepartment());
			statement.setString(2, professor.getpDesignation());
			statement.setString(3, professor.getuName());

			int row = statement.executeUpdate();

			logger.info(row + " Professor Added.");
			if (row == 0) {
				logger.info("Professor with ProfessorUserName: " + professor.getuName() + " not added.");
				throw new ProfessorAdditionFailedException(professor.getuName());
			}

			logger.info("Professor with ProfessorUserName: " + professor.getuName() + " added.");

		} catch (SQLException se) {

			logger.error(se.getMessage());
			throw new UserNameAlreadyInUseException(professor.getuName());

		}

	}

	/**
	 *
	 */
	public void addUser(User user) throws UserCannotBeAddedException, UserNameAlreadyInUseException {

		statement = null;
		try {

			String sql = SQLQueriesConstanst.ADD_USER_QUERY;
			statement = connection.prepareStatement(sql);

			statement.setString(1, user.getuId());
			statement.setString(2, user.getuName());
			statement.setString(3, user.getuPwd());
			statement.setObject(4, user.getuCrDate());
			statement.setObject(5, "PROFESSOR");
			int row = statement.executeUpdate();

			logger.info(row + " user added.");
			if (row == 0) {
				logger.info("User with User Name: " + user.getuId() + " not added.");
				throw new UserCannotBeAddedException(user.getuId());
			}

			logger.info("User with User Name: " + user.getuName() + " added.");

		} catch (SQLException se) {

			logger.error(se.getMessage());
			throw new UserNameAlreadyInUseException(user.getuName());

		}

	}

	/**
	 *
	 */
	public void approveStudents(String studentId) throws StudentNotFoundForApprovalException {

		statement = null;
		
		try {
			String sql = SQLQueriesConstanst.APPROVE_STUDENT_QUERY;
			statement = connection.prepareStatement(sql);

			statement.setString(1, studentId);
			int row = statement.executeUpdate();

			logger.info(row + " student approved.");
			if (row == 0) {
				logger.info("Student with student id  : " + studentId + " not found.");
				throw new StudentNotFoundForApprovalException(studentId);
			}

			logger.info("Student with studentId: " + studentId + " approved by admin.");

		} catch (SQLException se) {

			logger.error(se.getMessage());

		}

	}

	/**
	 *
	 */
	public void addCourse(Course course) throws CourseAlreadyInCatalogException {

		statement = null;
		try {

			String sql = SQLQueriesConstanst.ADD_COURSE_QUERY;
			statement = connection.prepareStatement(sql);

			statement.setString(1, course.getcCode());
			statement.setString(2, course.getcName());
			statement.setInt(3, 0);
			statement.setInt(4, 0);

			int row = statement.executeUpdate();

			logger.info(row + " course added");
			if (row == 0) {
				logger.info("Course with courseCode: " + course.getcCode() + "not added to catalog.");
				throw new CourseAlreadyInCatalogException(course.getcCode());
			}

			logger.info("Course with courseCode: " + course.getcCode() + " is added to catalog.");

		} catch (SQLException se) {

			logger.error(se.getMessage());
			throw new CourseAlreadyInCatalogException(course.getcCode());
		}
	}

	/**
	 *
	 */
	public void deleteCourse(String CourseCode) throws CourseNotRemovedException, CourseNotInCatalogException {

		statement = null;
		try {
			String sql = SQLQueriesConstanst.DELETE_COURSE_QUERY;
			statement = connection.prepareStatement(sql);

			statement.setString(1, CourseCode);
			int row = statement.executeUpdate();

			logger.info(row + " entries deleted.");
			if (row == 0) {
				logger.info(CourseCode + " not in catalog!");
				throw new CourseNotInCatalogException(CourseCode);
			}

			logger.info("Course with courseCode: " + CourseCode + " deleted.");

		} catch (SQLException se) {

			logger.error(se.getMessage());
			throw new CourseNotRemovedException(CourseCode);
		}
	}

	/**
	 *
	 */
	public void assignProfessor(String CourseCode, String professorId)
			throws CourseNotInCatalogException, UserNotFoundException {

		statement = null;
		try {
			String sql = SQLQueriesConstanst.ASSIGN_COURSE_QUERY;
			statement = connection.prepareStatement(sql);

			statement.setString(1, professorId);
			statement.setString(3, CourseCode);
			statement.setInt(2, 1);
			int row = statement.executeUpdate();

			logger.info(row + " course assigned.");
			if (row == 0) {
				logger.info(CourseCode + " not found");
				throw new CourseNotInCatalogException(CourseCode);
			}

			logger.info("Course with courseCode: " + CourseCode + " is assigned to professor with professorId: "
					+ professorId + ".");

		} catch (SQLException se) {

			logger.error(se.getMessage());
			throw new UserNotFoundException(professorId);

		}
	}

	/**
	 *
	 */
	public List<Course> viewCourses() {

		statement = null;
		List<Course> courseList = new ArrayList<>();
		try {

			String sql = SQLQueriesConstanst.VIEW_COURSE_QUERY;
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Course course = new Course(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
				courseList.add(course);

			}

			logger.info(courseList.size() + "Courses Present In Catalog");

		} catch (SQLException se) {

			logger.error(se.getMessage());

		}

		return courseList;
	}

	/**
	 *
	 */
	public List<Professor> showProfessors() {

		statement = null;
		List<Professor> professorList = new ArrayList<>();
		try {

			String sql = SQLQueriesConstanst.VIEW_PROFESSOR_QUERY;
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Professor professor = new Professor();
				professor.setpDepartment(resultSet.getString(1));
				professor.setpDesignation(resultSet.getString(2));
				professor.setuName(resultSet.getString(3));
				professorList.add(professor);

			}

			logger.info(professorList.size() + " professors in the institute.");

		} catch (SQLException se) {

			logger.error(se.getMessage());

		}
		return professorList;
	}

	/**
	 *
	 */
	public List<Student> viewPendingStudents() {

		statement = null;
		List<Student> userList = new ArrayList<Student>();
		try {

			String sql = SQLQueriesConstanst.VIEW_PENDING_ADMISSION_QUERY;
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Student user = new Student();
				user.setuId(resultSet.getString(1));
				user.setuName(resultSet.getString(2));
				user.setsBranch(resultSet.getString(3));
				userList.add(user);

			}

			logger.info(userList.size() + " students have pending-approval.");

		} catch (SQLException se) {

			logger.error(se.getMessage());

		}

		return userList;
	}

	/**
	 *
	 */
	public List<RegisteredCourse> generateReportCard(String studentId) {

		List<RegisteredCourse> registeredStudentsUnderProff = new ArrayList<RegisteredCourse>();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(SQLQueriesConstanst.VIEW_REPORT_CARD);
			
			
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
}