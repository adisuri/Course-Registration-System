/**
 * 
 */
package com.flipkart.restController;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.service.NotificationInterface;
import com.flipkart.service.NotificationOperation;
import com.flipkart.service.RegistrationInterface;
import com.flipkart.service.RegistrationOperation;
import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.exception.CourseLimitCrossed;
import com.flipkart.exception.CourseNotInCatalogException;
import com.flipkart.exception.CourseNotRemovedException;
import com.flipkart.exception.SeatNotAvailableException;

/**
 * @author JEDI-7
 *
 */

@Path("/student")
public class StudentRESTApi {
	
	RegistrationInterface registrationInterface = RegistrationOperation.getInstance();
	NotificationInterface notificationInterface = NotificationOperation.getInstance();
	/**
	 * Method to viewAvailableCourses 
	 * @param studentId
	 * @return
	 */
	@GET
	@Path("/viewAvailableCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewCourse(
			@NotNull
			@QueryParam("studentId") String studentId) {
		
			System.out.println(studentId);
			
			return registrationInterface.viewCourses(studentId);
		
	}
	
	
	/**
	 * Method to viewRegisteredCourses 
	 * @param studentId
	 * @return
	 */
	@GET
	@Path("/calculateFee")
	@Produces(MediaType.APPLICATION_JSON)
	public int calculateFee(
			@NotNull
			@QueryParam("studentId") String studentId){
		
			System.out.println(studentId);

			return registrationInterface.calculateFee(studentId);

	}
	
	/**
	 * Method to viewRegisteredCourses 
	 * @param studentId
	 * @return
	 */
	@GET
	@Path("/viewRegisteredCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewRegisteredCourses(
			@NotNull
			@QueryParam("studentId") String studentId){
		
			System.out.println(studentId);

			return registrationInterface.viewRegisteredCourses(studentId);

	}
	
	/**
	 * Method to getRegistrationStatus 
	 * @param studentId
	 * @return
	 */
	@GET
	@Path("/getRegistrationStatus")
	@Produces(MediaType.APPLICATION_JSON)
	public int getRegistrationStatus(
			@NotNull
			@QueryParam("studentId") String studentId){
		
			System.out.println(studentId);

			return registrationInterface.getRegistrationStatus(studentId);

	}
	
	
	
	/**
	 * Method to viewReportCard 
	 * @param studentId
	 * @return
	 */
	@GET
	@Path("/viewReportCard")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RegisteredCourse> viewReportCard(
			@NotNull
			@QueryParam("studentId") String studentId){
		
			System.out.println(studentId);

			return registrationInterface.viewReportCard(studentId);

	}
	
	/**
	 * Method to viewNotifications 
	 * @param studentId
	 * @return
	 */
	@GET
	@Path("/viewNotifications")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Notification> viewNotifications(
			@NotNull
			@QueryParam("studentId") String studentId){
		
			System.out.println(studentId);
			return notificationInterface.getAllNotifications(studentId);


	}
	
	/**
	 * Method to registerCourses 
	 * @param studentId
	 * @return
	 */
	@POST
	@Path("/registerCourses")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response  registerCourses(
			List<String> courseList,
			@NotNull
			@QueryParam("studentId") String studentId,
			@QueryParam("sem") int sem){
		
			System.out.println(studentId);
			int currentCourseCount = 0;
			for( String courseCode : courseList) {
				// System.out.println(courseCode + " " + courseList);
				try {
					List<Course> availableCourseList = registrationInterface.viewCourses(studentId);
					registrationInterface.addCourse(courseCode, studentId, availableCourseList, sem, (currentCourseCount < 4 ? 0 : 1));
				} catch (CourseNotInCatalogException | SeatNotAvailableException | CourseLimitCrossed e) {
					System.out.println(e.getMessage());
					return Response.status(500).entity("Course Addition Failed on : "+ courseCode  + " "+ e.getMessage()).build();
				}
				currentCourseCount += 1;
			}
			
			registrationInterface.setRegistrationStatus(studentId);
			
			return Response.status(201).entity( "Registration Courses Successful").build();

	}
	
	
	/**
	 * Method to addCourse 
	 * @param studentId
	 * @return
	 */
	@PUT
	@Path("/addCourse")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  addCourse(
			@NotNull
			@QueryParam("studentId") String studentId,
			@QueryParam("courseCode") String courseCode,
			@QueryParam("sem") int sem){
		
			System.out.println(studentId);
			
			
			
			if(registrationInterface.getRegistrationStatus(studentId) == 0) {
				return Response.status(200).entity("Please Register Before using Add/Drop").build();
			}
				
			
			try {
				List<Course> availableCourseList = registrationInterface.viewCourses(studentId);
				if (!registrationInterface.addCourse(courseCode, studentId, availableCourseList, sem, 0))
					Response.status(500).entity("Already Registered for the Course : "+ courseCode).build();
			} catch (CourseNotInCatalogException |  CourseLimitCrossed |SeatNotAvailableException e) {
				return Response.status(500).entity("Course Addition Failed on : "+ courseCode  + " "+ e.getMessage()).build();
			}
			
			return Response.status(201).entity( "Course Addition Successful").build();

	}
	
	
	/**
	 * Method to dropCourse 
	 * @param studentId
	 * @return
	 */
	@DELETE
	@Path("/dropCourse")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  dropCourse(
			@NotNull
			@QueryParam("studentId") String studentId,
			@QueryParam("courseCode") String courseCode,
			@QueryParam("sem") int sem){
		
			System.out.println(studentId);
			
			
			
			if(registrationInterface.getRegistrationStatus(studentId) == 0) {
				return Response.status(200).entity("Please Register Before using Add/Drop").build();
			}
				
			
			try {
				List<Course> availableCourseList = registrationInterface.viewRegisteredCourses(studentId);
				registrationInterface.dropCourse(courseCode, studentId, availableCourseList, sem);
			} catch (CourseNotRemovedException e) {
				return Response.status(500).entity("Course Remove Failed on : "+ courseCode  + " "+ e.getMessage()).build();
			}
			
			return Response.status(201).entity( "Course Drop Successful").build();

	}
	
	
	/**
	 * Method to makePayment 
	 * @param studentId
	 * @return
	 */
	@POST
	@Path("/makePayment")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  makePayment(
			@NotNull
			@QueryParam("studentId") String studentId,
			@QueryParam("paymentMode") String paymentMode){
		
			System.out.println(studentId);
			
			
			try {
				String referenceId = notificationInterface.updatePayment(studentId, paymentMode);
				String message = paymentMode + " Payment done";
				System.out.println(message);
				notificationInterface.sendNotification(message, studentId, referenceId);
				return Response.status(201).entity( "Payment Successful").build();
			} catch (Exception e) {

				return Response.status(500).entity("Payment Failed : "+ paymentMode  + " "+ e.getMessage()).build();
			}

	}

	
}
