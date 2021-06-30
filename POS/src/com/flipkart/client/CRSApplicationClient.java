/**
 * 
 */
package com.flipkart.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.flipkart.bean.Student;
import com.flipkart.constants.RolesConstant;
import com.flipkart.exception.StudentNotRegisteredException;
import com.flipkart.exception.UserNameAlreadyInUseException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentOperation;
import com.flipkart.service.UserInterface;
import com.flipkart.service.UserOperation;

/**
 * @author JEDI-7
 * This Class Is The Main Entry Point Of The Application.
 */
public class CRSApplicationClient {
	
	static boolean loggedIn = false;
	UserInterface userInterface = UserOperation.getInstance();

	/**
	 * @param args
	 * @throws User Not Found Exception .
	 */
	public static void main(String[] args) throws UserNotFoundException {
		
		
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		CRSApplicationClient app = new CRSApplicationClient();
		
		int userInput=0;
		
		while(userInput!=4) {
			createMainMenu();
			userInput = sc.nextInt();
			switch(userInput)
			{	
				case 1:
					app.loginUser();
					break;
				case 2:
					app.registerStudent();
					break;	
				case 3:
					app.updatePassword();
					break;
				case 4:
					System.out.println("\n+++++++ Exiting Application..... +++++++");
					break;
				default:
					System.out.println("+++++++ Please Enter The Valid Input +++++++");
			}
		}
		sc.close();

	}
	
	/**
	 * Method to display Main Menu
	 */
	public static void createMainMenu() {
		System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("++++++++++++ Welcome To  Course Management System  +++++++++++++++");
		System.out.println("Menu For Course Management System:");
		System.out.println("1. Enter 1 To Login.");
		System.out.println("2. Enter 2 For Student Registration.");
		System.out.println("3. Enter 3 To Update Your Password.");
		System.out.println("4. Enter 4 To Exit From The Menu.");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		System.out.println("Enter user input : ");
	}
	
	/**
	 * Method To Login User To The System
	 * @throws User Not Found Exception. 
	 */
	public void loginUser() {
		
		String userName, password, role = null;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter User Id : ");
		userName = sc.next();
		System.out.println("Enter Password : ");
		password = sc.next();
		
		try {
			
			loggedIn = userInterface.verifyCredentials(userName, password);
			
			System.out.println("Login Status -> " +  loggedIn);
			
			role = userInterface.getUserRole(userName);
			
			if(loggedIn == false || role == null) {
				System.out.println("++++++ Login Failed Returning To Main Menu +++++");
				return;
			}
			
			
			switch(RolesConstant.stringToName(role))
			{	
				case STUDENT:
					System.out.println("\nStudent Logged In!");
					StudentMenuCRS studentMenu=new StudentMenuCRS();
					studentMenu.renderMenu(userName);
					break;
				case PROFESSOR:
					System.out.println("\nProfessor Logged In!");
					ProfessorMenuCRS professorMenu = new ProfessorMenuCRS();
					professorMenu.renderMenu(userName);
					break;	
				case ADMIN:
					System.out.println("\nAdmin Logged In!");
					AdminMenuCRS adminMenu=new AdminMenuCRS();
					adminMenu.renderMenu(userName);
					break;
				default:
					System.out.println("\nInvalid Input Returning to Main Menu");
			}
		
		} catch (UserNotFoundException ex) {
			System.out.println("\nLogin Failed Returning to Main Menu");
		}
			
	}
	
	/**
	 * Method In Which Students Are Registering Themselves.
	 * @throws ParseException 
	 */
	public void registerStudent(){
		Scanner scanner = new Scanner(System.in);

		Student student = new Student();
		
		String uuid = UUID.randomUUID().toString();
		student.setuId(uuid);
		
		System.out.println("Enter Student User Name:");
		String studentName = scanner.next();
		student.setuName(studentName);

		System.out.println("Enter Student Branch:");
		String branch = scanner.next();
		student.setsBranch(branch);
		
		System.out.println("Enter Student Roll Number:");
		int rollno = scanner.nextInt();
		student.setsRollNo(rollno);
		
		student.setApproved(false);
		
		System.out.println("Enter Student Password:");
		String pwd = scanner.next();
		student.setuPwd(pwd);
		
		try {
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(new Date());
			Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
			student.setuCrDate(date1);
		}catch(ParseException e){
			System.out.println(e.getMessage());
		}
		
		
		StudentInterface studentOperation = StudentOperation.getInstance();
		
		int flag=0;
		
		try {
			flag = studentOperation.addStudent(student);
		} catch (StudentNotRegisteredException | UserNameAlreadyInUseException e) {
			System.out.println(e.getMessage());
		}
		if(flag==1) {
			System.out.println("Student Registered Successfully!");
		}
		else {
			System.out.println("Student Failed To Register.");
		}
	}
	
	/**
	 * Method To Update Password Of The User.
	 */
	public void updatePassword() {
		String userName, newPassword, role = null;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter User Id : ");
		userName = sc.next();
		System.out.println("Enter New Password : ");
		newPassword = sc.next();
		
		try {
			
			boolean isUpdated = userInterface.updatePassword(userName, newPassword);
			if( isUpdated == true) {
				System.out.println("\nPassword changed for User -> " + userName);
			}else {
				System.out.println("\n+++++++ Password Update Failed +++++++");
			}
		
		} catch (UserNotFoundException ex) {
			System.out.println("User Not Found.");
		}
	}

}
