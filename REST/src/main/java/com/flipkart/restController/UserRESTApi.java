/**
 * 
 */
package com.flipkart.restController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Student;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentOperation;
import com.flipkart.service.UserInterface;
import com.flipkart.service.UserOperation;

/**
 * @author JEDI-7
 *
 */


@Path("/user")
public class UserRESTApi {

	StudentInterface studentInterface = StudentOperation.getInstance();
	UserInterface userInterface = UserOperation.getInstance();

	/**
	 * @param userName
	 * @param newPassword
	 * @return
	 * @throws UserNotFoundException
	 */
	@POST
	@Path("/updatePassword")
	public Response updatePassword(@NotNull @QueryParam("userName") String userName,
			@NotNull @QueryParam("newPassword") String newPassword) throws UserNotFoundException {

		if (userInterface.updatePassword(userName, newPassword)) {
			return Response.status(201).entity("Password updated successfully! ").build();
		} else {
			return Response.status(500).entity("Something went wrong, please try again!").build();
		}
	}

	/**
	 * @param userId
	 * @param password
	 * @return
	 * @throws UserNotFoundException
	 */
	@POST
	@Path("/login")
	public Response verifyCredentials(@NotNull @QueryParam("userId") String userId,
			@NotNull @QueryParam("password") String password) throws UserNotFoundException {

		try {
			boolean loggedin = userInterface.verifyCredentials(userId, password);
			if (loggedin) {
				String role = userInterface.getUserRole(userId);
				return Response.status(200).entity("Login successful").build();
			} else {
				return Response.status(500).entity("Invalid credentials!").build();
			}
		} catch (UserNotFoundException e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	/**
	 * @param studentName
	 * @param branch
	 * @param rollno
	 * @param pwd
	 * @return
	 */
	
	@POST
	@Path("/studentRegistration")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(Student student){
	
		String uuid = UUID.randomUUID().toString();
		student.setuId(uuid);
		
		try {
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(new Date());
			Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
			student.setuCrDate(date1);
		}catch(ParseException e){
			System.out.println(e.getMessage());
		}
		
		try
		{
			studentInterface.addStudent(student);
		}
		catch(Exception ex)
		{
			return Response.status(500).entity("Something went wrong! Please try again.").build(); 
		}
		
		
		return Response.status(201).entity("Registration Successful for "+student.getuId()).build(); 
	}

}
