/**
 * 
 */
package com.flipkart.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.exception.GradeAddFailedException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorOperation;
import com.flipkart.service.UserInterface;
import com.flipkart.service.UserOperation;

/**
 * @author JEDI-7
 * 
 * Class To Display Professor Client Menu.
 *
 */
public class ProfessorMenuCRS {
	
	/**
	 * @param proffId
	 */
	
	ProfessorInterface proffInterface = ProfessorOperation.getInstance();
	
	
	public void renderMenu(String proffId) {
		
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		
		while(choice != 5) {
			
			System.out.println("\n\n++++++++++++++++++++++++++++++++++++++++");
			System.out.println("++++++++++++ Professor Menu +++++++++++++");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++");
			System.out.println("1. Enter 1 To Give Grade To Student.");
			System.out.println("2. Enter 2 To View Registered Students.");
			System.out.println("3. Enter 3 To View Professor Courses.");
			System.out.println("4. Enter 4 To View Professor Details.");
			System.out.println("5. Enter 5 To Logout From The System.");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++");
			System.out.println("\nEnter The User Input");
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					addGrade(proffId);
					break;
				case 2:
					viewRegisteredStudents(proffId);
					break;
				case 3:
					getMyCourses(proffId);
					break;
				case 4:
					viewMyDetails(proffId);
					break;
				case 5:
					crsMainLogout();
					break;
				default: {
					System.out.println("++++++++++++++ Wrong Choice !!! ++++++++++++");
					System.out.println("+++++++++ Please Enter The Valid One ++++++++");
				}
					
					
			}
			
		 }
		 // sc.close();
	}
	
	/**
	 * Method To View Professor Details.
	 * @param proffId
	 */
	private void viewMyDetails(String proffId) {
		try {
			
			Professor proff = proffInterface.getProffProfleById(proffId);
			
			System.out.println(String.format("%30s %30s", "Designation", "Department"));
			System.out.println(String.format("%30s %30s", proff.getpDesignation(), proff.getpDepartment()));
		} catch (Exception ex) {
			System.out.println("++++ Some Error Occurred Returning To Menu +++++");
		}
		
	}

	/**
	 * Method To View List Of Courses Professor Has To Teach.
	 * @param proffId
	 */
	public void getMyCourses(String proffId) {
		try {
			List<Course> myCourses = new ArrayList<Course>();
			
			myCourses = proffInterface.viewProfessorCourses(proffId);
			
			System.out.println(String.format("%30s %30s %30s %30s", "Course Code", "Course Name", "Professor Name", "Is Offered"));
			for(Course cr: myCourses){
				System.out.println(String.format("%30s %30s %30s %30s", cr.getcCode(), cr.getcName(), cr.getProfName(), cr.isOffered()));
			}
		} catch (Exception ex) {
			System.out.println("++++++ Some Error Occurred Returning To Menu ++++++");
		}
	}
	
	/**
	 * Method In Which Professor Grade A Student.
	 * @param proffId
	 * @throws Grade Add Failed Exception.
	 */
	public void addGrade(String proffId) {
		
		Scanner sc = new Scanner(System.in);
		
		String grade,courseCode,studentId;
		
		
		
		try{
			
			System.out.println("++++++++++++++++++++++++++++++++");
			viewRegisteredStudents(proffId);
			System.out.println("++++++++ Add Grade Menu ++++++++");
			System.out.println("Enter Student Id : ");
			studentId = sc.next();
			System.out.println("Enter Course Code : ");
			courseCode = sc.next();
			System.out.println("Enter Grade : ");
			grade = sc.next();
			
			boolean isUpdated = proffInterface.addGrade(studentId, courseCode, new Grade(grade));
			if( isUpdated == true) {
				return;
			}else {
				System.out.println("\n+++++++ Grade Updation Failed ++++++");
			}
		
		} catch (GradeAddFailedException ex) {
			System.out.println("\n+++++++ User Not Found +++++++");
		}
		
	}
	
	/**
	 * Method To View Enrolled Students In Course.
	 * @param proffId
	 */
	public void viewRegisteredStudents(String proffId) {
		try {
			List<RegisteredCourse> registeredCourses = new ArrayList<RegisteredCourse>();
			
			registeredCourses = proffInterface.viewRegisteredStudents(proffId);
			
			System.out.println(String.format("%30s %30s %30s", "Course Code", "Student Id", "Semester"));
			for(RegisteredCourse rc: registeredCourses){
				System.out.println(String.format("%30s %30s %30s", rc.getcCode(), rc.getsstudentId(), rc.getSem()));
			}
		} catch (Exception ex) {
			System.out.println("\n++++++ Some Error Occurred Returning To Menu ++++++");
		}
	}
	
	/**
	 * Method For Returning To The Main Menu.
	 */
	public void crsMainLogout() {
		System.out.println("\n++++++ Logging Out... Returning to Main Menu ++++++\n\n");
	}
}
