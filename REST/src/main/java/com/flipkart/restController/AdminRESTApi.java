/**
 * 
 */
package com.flipkart.restController;

import java.util.List;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseAlreadyInCatalogException;
import com.flipkart.exception.CourseNotInCatalogException;
import com.flipkart.exception.CourseNotRemovedException;
import com.flipkart.exception.ProfessorAdditionFailedException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.exception.UserNameAlreadyInUseException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminOperation;

/**
 * @author JEDI-7
 *
 */
@Path("/admin")

public class AdminRESTApi {

	AdminInterface adminOperation = AdminOperation.getInstance();

	/**
	 * @param courseCode
	 * @param professorId
	 * @return
	 */
	@POST
	@Path("/assignProfessor")
	@Produces(MediaType.APPLICATION_JSON)
	public Response assignProfessor(
			@NotNull 
			@QueryParam("courseCode") String courseCode,
			@QueryParam("professorId") String professorId) {
		
			try {
				
				adminOperation.assignProfessor(courseCode, professorId);
				return Response.status(201).entity("courseCode: " + courseCode + " assigned to professor: " + professorId).build();
				
			} catch (CourseNotInCatalogException | UserNotFoundException e) {
				
				return Response.status(409).entity(e.getMessage()).build();
				
			}
	}
	
	/**
	 * @param professor
	 * @return
	 */
	@POST
	@Path("/addProfessor")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProfessor(Professor professor) {
		 
		try {
			
			adminOperation.addProfessor(professor);
			return Response.status(201).entity("Professor with professorId: " + professor.getuId() + " added").build();
			
		} catch (ProfessorAdditionFailedException | UserNameAlreadyInUseException e) {
			
			return Response.status(409).entity(e.getMessage()).build();
			
		}
		
	}
	
	/**
	 * @return
	 */
	@GET
	@Path("/viewPendingStudents")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> viewPendingStudents() {
		
		return adminOperation.viewPendingStudents();
		
	}
	
	
	/**
	 * @param studentId
	 * @return
	 */
	@PUT
	@Path("/approveStudents")
	@Produces(MediaType.APPLICATION_JSON)
	public Response approveStudents(
			@NotNull
			@QueryParam("studentId") String studentId) {
		List<Student> studentList = adminOperation.viewPendingStudents();
		
		try {
			
			adminOperation.approveStudents(studentId, studentList);
			return Response.status(201).entity("Student with studentId: " + studentId + " approved").build();
		
		} catch (StudentNotFoundForApprovalException e) {
			
			return Response.status(409).entity(e.getMessage()).build();
		
		}
		
	}
	
	/**
	 * @return
	 */
	@GET
	@Path("/viewCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewCourses() {
		
		return adminOperation.viewCourses();
		
	}
	/**
	 * @param courseCode
	 * @return
	 */	
	@PUT
	@Path("/deleteCourse")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCourse(@QueryParam("courseCode") String courseCode) {
		List<Course> courseList = adminOperation.viewCourses();
		try {
			
			adminOperation.deleteCourse(courseCode, courseList);
			return Response.status(201).entity("Course with courseCode: " + courseCode + " deleted from catalog").build();
		
		} catch (CourseNotInCatalogException | CourseNotRemovedException e) {
			
			return Response.status(409).entity(e.getMessage()).build();
		
		}	
	}
	
	/**
	 * @param course
	 * @return
	 */
	@POST
	@Path("/addCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(Course course) {
		List<Course> courseList = adminOperation.viewCourses();
		
		try {
			
			adminOperation.addCourse(course, courseList);
			return Response.status(201).entity("Course with courseCode: " + course.getcCode() + " added to catalog").build();
		
		} catch (CourseAlreadyInCatalogException e) {
			
			return Response.status(409).entity(e.getMessage()).build();
		
		}
			
	}
	
	/**
	 * @param studentId
	 * @return
	 */
	@GET
	@Path("/generateReportCard")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RegisteredCourse> generateReportCard(
			@NotNull
			@QueryParam("studentId") String studentId){
		
			System.out.println(studentId);

			return adminOperation.generateReportCard(studentId);

	}
	
}