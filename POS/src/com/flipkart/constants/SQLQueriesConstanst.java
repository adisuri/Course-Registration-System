/**
 * 
 */
package com.flipkart.constants;

/**
 * @author JEDI-7
 *
 */
public class SQLQueriesConstanst {

	
	public static final String VERIFY_CREDENTIALS = "SELECT * FROM user WHERE userName = ? AND passwordHash = ?";
	public static final String VIEW_AVAILABLE_COURSES = "select * from course where cCode not in  (select courseCode  from registered_courses where studentId = ?) and course.isOffered = ?";
	public static final String GET_USER_ROLE = "SELECT role FROM user WHERE userName = ?";
	public static final String UPDATE_USER_PASSWORD = "UPDATE user SET passwordHash = ? WHERE userName = ?";
	public static final String GET_REGISTERED_STUDENTS_FOR_PROFF = "SELECT registered_courses.studentId , registered_courses.semester, registered_courses.courseCode,registered_courses.grade FROM (course INNER JOIN registered_courses ON registered_courses.courseCode = course.cCode) WHERE course.instructor = ? ORDER BY course.cCode";
	public static final String GET_PROFF_COURSES = "SELECT * FROM course WHERE instructor = ?";
	public static final String GET_PROFF_DETAILS = "SELECT user.userId, user.userName, professor.department, professor.designation FROM (professor INNER JOIN user ON user.userName = professor.proffId ) WHERE professor.proffId = ?";
	public static final String ADD_GRADE_FOR_STUDENT = "UPDATE registered_courses SET grade = ? WHERE studentId = ? AND courseCode = ?";
	
	
	
	public static final String ADD_COURSE="insert into registered_courses (studentId,semester,courseCode) values ( ? , ? , ?)";
	public static final String DECREMENT_COURSE_SEATS="update course set courseSeats = courseSeats-1 where cCode = ? ";
	public static final String DROP_COURSE_QUERY = "delete from registered_courses where courseCode = ? AND studentId = ? AND semester = ?;";
	public static final String INCREMENT_SEAT_QUERY  = "update course set courseSeats = courseSeats + 1 where  cCode = ?;";
//	public static final String VIEW_AVAILABLE_COURSES=" select * from course where cCode not in  (select courseCode  from registered_courses where studentId = ?) and course.isOffered = ? and seats > 0";
	public static final String VIEW_GRADE = "select course.cCode,registered_courses.studentId,course.cName,registered_courses.grade from course inner join registered_courses on course.cCode = registered_courses.courseCode where registered_courses.studentId = ?;";
	
	public static final String ADD_PROFESSOR_QUERY = "INSERT INTO professor(department, designation, proffid) VALUES (?, ?, ?)";
	public static final String ADD_USER_QUERY = "INSERT INTO user(userId, userName, passwordHash, createDate ,role) values (?, ?, ?, ?, ?)";
	public static final String APPROVE_STUDENT_QUERY = "UPDATE student SET approved = 2 where userId = ?";
	public static final String ADD_COURSE_QUERY = "INSERT INTO course(cCode, cName,isOffered,courseSeats) values (?, ?,?,?)";
	public static final String DELETE_COURSE_QUERY = "DELETE FROM course WHERE cCode = ?";
	public static final String ASSIGN_COURSE_QUERY = "UPDATE course SET instructor = ?,isOffered = ?, courseSeats = 10 where cCode = ?";
	public static final String VIEW_COURSE_QUERY = "SELECT cCode, cName, instructor FROM course";
	public static final String VIEW_PROFESSOR_QUERY = "SELECT department, designation, proffId FROM professor";
	public static final String VIEW_PENDING_ADMISSION_QUERY = "SELECT student.userId, user.userName, student.branch FROM (student INNER JOIN user ON user.userName = student.userId) WHERE student.approved = 1";
	public static final String VIEW_REPORT_CARD = "SELECT * FROM registered_courses WHERE studentId = ?";
	public static final String GET_REGISTER_STATUS=" select approved from student where userId = ? ";
	
	public static final String ADD_STUDENT="insert into student (userId,branch,approved) values (?,?,?)";
	public static final String GET_STUDENT_ID="select student.userId from (user inner join student on student.userId = user.userName) where user.userId = ? ";
	public static final String IS_APPROVED="select approved from student where userId = ? ";
	public static final String NUM_OF_REGISTERED_COURSES=" select studentId from registered_courses where studentId = ? ";
	public static final String IS_REGISTERED=" select courseCode from registered_courses where courseCode=? and studentId=? ";
	public static final String GET_SEATS = "select courseSeats from course where cCode = ?";
	public static final String SET_REGISTRATION_STATUS="update student set approved = 1 where userId=?";
	public static final String VIEW_REGISTERED_COURSES="select * from course where cCode IN (select courseCode from registered_courses where studentId =?)";
//	public static final String VIEW_REGISTERED_COURSES="select cou from course where cCode IN (select courseCode from registered_courses where studentId =?)";

	
	public static final String INSERT_PAYMENT = "insert into payment(referenceId,studentId,amount,status,paymentType) values(?,?,?,?,?)";
	public static final String UPDATE_PAYMENT = "update payment set status = 1, paymentType = ? where status = 0 and studentId = ?";
	public static final String GET_REFID_PAYMENT = "select referenceId from payment where status = 0 and studentId = ?";
	public static final String INSERT_NOTIFICATION = "insert into notification(notificationId,message,studentId,referenceId) values(?,?,?,?)";
	public static final String GET_REFERENCE_ID = "select * from notification where notificationId=?";
	public static final String GET_ALL_NOTIFICATIONS = "select * from notification where studentId=?";
	
	public static final String CALCULATE_FEES  = "select sum(amount) from payment where studentId = ? and status = 0;";
	
}
