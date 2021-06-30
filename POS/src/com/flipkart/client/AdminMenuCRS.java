/**
 * 
 */
package com.flipkart.client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

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
import com.flipkart.exception.UserNameAlreadyInUseException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminOperation;
import com.flipkart.service.NotificationInterface;
import com.flipkart.service.NotificationOperation;

/**
 * @author JEDI-7
 *
 *         Class To Display The Admin Client Menu.
 *
 */
public class AdminMenuCRS {

	Scanner scanner = new Scanner(System.in);

	/**
	 * Method To Create Admin Menu
	 */
	public void renderMenu(String studId) {

		int choice = -1;

		while (choice != 9) {
			System.out.println("\n\n+++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("++++++++++++++++++ Admin Menu ++++++++++++++++++");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("1. Enter 1 To View Course In Catalogue.");
			System.out.println("2. Enter 2 To Add Course To Catalogue.");
			System.out.println("3. Enter 3 To Delete Course From Catalogue.");
			System.out.println("4. Enter 4 For Approving Student Registration.");
			System.out.println("5. Enter 5 To View Pending Admissions.");
			System.out.println("6. Enter 6 To Add Professor To Database.");
			System.out.println("7. Enter 7 To Assign Course To Professor.");
			System.out.println("8. Enter 8 To Generate Report Card.");
			System.out.println("9. Enter 9 To Logout From The System.");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println();
			System.out.println("Enter The User Input.");
			choice = scanner.nextInt();
			System.out.println(choice);

			switch (choice) {

			case 1:
				viewCourses();
				break;

			case 2:
				addCourseToCatalogue();
				break;

			case 3:
				deleteCourseFromCatalogue();
				break;

			case 4:
				approveStudents();
				break;

			case 5:
				viewPendingStudents();
				break;
			case 6:
				addProfessor();
				break;

			case 7:
				assignProfessor();
				break;

			case 8:
				generateReportCard();
				break;
			case 9:
				crsMainLogout();
				break;
			default: {
				System.out.println("+++++++++ Wrong Choice !!!!! +++++");
				System.out.println("+++++++++ Please Enter The Valid One ++++++++");
			}
			}
		}

	}

	AdminInterface adminOperation = AdminOperation.getInstance();

	/**
	 * Method To Display Courses In Catalog.
	 * 
	 * @return List Of Courses In Catalog.
	 */
	private List<Course> viewCourses() {
		List<Course> courseList = adminOperation.viewCourses();
		if (courseList.size() == 0) {
			System.out.println("No course in the catalogue!");
			return courseList;
		}
		System.out.println(String.format("%30s | %30s | %30s", "COURSE CODE", "COURSE NAME", "INSTRUCTOR"));
		for (Course course : courseList) {
			System.out.println(
					String.format("%30s | %30s | %30s", course.getcCode(), course.getcName(), course.getProfName()));
		}
		return courseList;
	}

	/**
	 * Method To Add Course To Catalog.
	 * 
	 * @throws Course Already In Catalog Exception.
	 */
	private void addCourseToCatalogue() {

		List<Course> courseList = viewCourses();

		System.out.println("Enter Course Code:");
		String courseCode = scanner.next();

		scanner.nextLine();
		System.out.println("Enter Course Name:");

		String courseName = scanner.nextLine();
		Course course = new Course(courseCode, courseName, null, false, 10);
		try {
			adminOperation.addCourse(course, courseList);
		} catch (CourseAlreadyInCatalogException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Method To Display Course From Catalog.
	 * 
	 * @throws Course Not In Catalog Exception.
	 * @throws Course Not Removed Exception.
	 */
	private void deleteCourseFromCatalogue() {

		List<Course> courseList = viewCourses();
		System.out.println("Enter Course Code:");
		String courseCode = scanner.next();

		try {
			adminOperation.deleteCourse(courseCode, courseList);
		} catch (CourseNotInCatalogException | CourseNotRemovedException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method To View Students Who Are Yet To Be Approved For Registration.
	 * 
	 * @return List Of Students Whose Admissions Are Pending.
	 */
	private List<Student> viewPendingStudents() {
		List<Student> pendingStudentsList = adminOperation.viewPendingStudents();
		if (pendingStudentsList.size() == 0) {
			return pendingStudentsList;
		}
		System.out.println(String.format("%20s | %20s ", "StudentId", "Name"));
		for (Student student : pendingStudentsList) {
			System.out.println(String.format("%20s | %20s ", student.getuId(), student.getuName()));
		}
		return pendingStudentsList;
	}

	/**
	 * Method To Approve Student For Registration Using Student's User Id.
	 * 
	 * @throws Student Not Found For Approval Exception.
	 */
	private void approveStudents() {
		NotificationInterface notificationInterface = NotificationOperation.getInstance(); 
		List<Student> studentList = viewPendingStudents();
		if (studentList.size() == 0) {
			return;
		}
		System.out.println("Enter Student's ID:");
		String studentUserId = scanner.next();

		try {
			adminOperation.approveStudents(studentUserId, studentList);
			
			//payment
			String refId = notificationInterface.addPayment(studentUserId, 1000, false, null);
			System.out.println(refId + "Done ");
			//Send Notification
			String message = "Fee payment pending";
			notificationInterface.sendNotification(message, studentUserId, refId);
			
		} catch (StudentNotFoundForApprovalException e) {
			System.out.println(e.getMessage());
		}
		catch (SQLException e) 
		{
            System.out.println(e.getMessage());
		}
	}

	/**
	 * Method To Add Professor To Database.
	 * 
	 * @throws Professor Addition Failed Exception.
	 * @throws User      Name Already In Use Exception.
	 */
	private void addProfessor() {

		Professor professor = new Professor();

		String userId = UUID.randomUUID().toString();
		professor.setuId(userId);

		System.out.println("Enter Professor User Name:");
		String professorName = scanner.next();
		professor.setuName(professorName);

		System.out.println("Enter Department:");
		String department = scanner.next();
		professor.setpDepartment(department);

		System.out.println("Enter Designation:");
		String designation = scanner.next();
		professor.setpDesignation(designation);

		System.out.println("Enter Password:");
		String password = scanner.next();
		professor.setuPwd(password);

		System.out.println("Enter Create Date");
		// Date createDate = java.time.Date.now();
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		professor.setuCrDate(date);

		try {
			adminOperation.addProfessor(professor);
		} catch (ProfessorAdditionFailedException | UserNameAlreadyInUseException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method To Assign Course To Professor.
	 * 
	 * @throws Course Not In Catalog Exception.
	 * @throws User   Not Found Exception.
	 */
	private void assignProfessor() {
		List<Professor> professorList = adminOperation.showProfessors();
		System.out.println(String.format("%30s | %30s | %30s ", "ProfessorId",  "Designation", "Department"));
		for (Professor professor : professorList) {
			System.out.println(String.format("%30s | %30s | %30s ", professor.getuName(),
					professor.getpDesignation(),professor.getpDepartment()));
		}

		System.out.println("\n\n");
		List<Course> courseList = adminOperation.viewCourses();
		System.out.println(String.format("%30s | %30s | %30s", "CourseCode", "CourseName", "Instructor"));
		for (Course course : courseList) {
			System.out.println((String.format("%30s | %30s | %30s", course.getcCode(), course.getcName(),course.getProfName())));
		}

		System.out.println(("Enter Course Code:"));
		String courseCode = scanner.next();

		System.out.println(("Enter Professor's User Id:"));
		String userId = scanner.next();

		try {

			adminOperation.assignProfessor(courseCode, userId);

		} catch (CourseNotInCatalogException | UserNotFoundException e) {

			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method To Generate Report Card Of The Student.
	 */
	private void generateReportCard() {

		try {
			List<RegisteredCourse> registeredCourses = new ArrayList<RegisteredCourse>();

			System.out.println("Enter StudentId");
			String studentId = scanner.next();
			registeredCourses = adminOperation.generateReportCard(studentId);

			System.out.println("Student Username: " + studentId);
			System.out.println(String.format("%30s %30s %30s", "Course Code", "Semester", "Grade"));
			for (RegisteredCourse rc : registeredCourses) {
				System.out
						.println(String.format("%30s %30s %30s", rc.getcCode(), rc.getSem(), rc.getGrade().getGrade()));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	/**
	 * Method For Returning To The Main Menu.
	 */
	private void crsMainLogout() {
		System.out.println("++++++++ Logging Out...... Returning to Main Menu ++++++\n\n\n");
	}
}
