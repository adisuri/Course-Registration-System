/**
 * 
 */
package com.flipkart.restController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorOperation;

/**
 * @author JEDI-7
 *
 */
@Path("/professor")
public class ProfessorRESTApi {
	ProfessorInterface professorInterface = ProfessorOperation.getInstance();

	/**
	 * @param profId
	 * @return
	 */
	@GET
	@Path("/viewRegisteredStudents")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RegisteredCourse> viewRegisteredStudents(@NotNull @QueryParam("profId") String profId) {

		List<RegisteredCourse> students = new ArrayList<RegisteredCourse>();
		try {
			students = professorInterface.viewRegisteredStudents(profId);
		} catch (Exception ex) {
			return null;
		}
		return students;
	}

	/**
	 * @param profId
	 * @return
	 */
	@GET
	@Path("/viewProfessorCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCourses(@NotNull @QueryParam("profId") String profId) {

		List<Course> courses = new ArrayList<Course>();
		try {
			courses = professorInterface.viewProfessorCourses(profId);
		} catch (Exception ex) {
			return null;
		}
		return courses;

	}

	/**
	 * @param studentId
	 * @param courseCode
	 * @param profId
	 * @param grade
	 * @return
	 */
	/*
	@POST
	@Path("/addGrade")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addGrade(@NotNull @QueryParam("studentId") String studentId,
			@NotNull @QueryParam("courseCode") String courseCode, @NotNull @QueryParam("profId") String profId,
			@QueryParam("grade") String grade) {

		try {
			if (professorInterface.addGrade(studentId, courseCode, new Grade(grade)))
				return Response.status(200).entity("Grade updated for student: " + studentId).build();
			else
				return Response.status(500).entity(" Grade Updation Failed, Please Try Again ! ").build();
		} catch (Exception ex) {
			return Response.status(500).entity("User Not Found, Please Try Again ! ").build();
		}

	}
	
	*/
	
	
	@POST
	@Path("/addGrade")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addGrade(@NotNull @QueryParam("studentId") String studentId,
			@NotNull @QueryParam("courseCode") String courseCode, @NotNull @QueryParam("profId") String profId,Grade grade) {

		try {
			if (professorInterface.addGrade(studentId, courseCode, grade))
				return Response.status(200).entity("Grade updated for student: " + studentId).build();
			else
				return Response.status(500).entity(" Grade Updation Failed, Please Try Again ! ").build();
		} catch (Exception ex) {
			return Response.status(500).entity("User Not Found, Please Try Again ! ").build();
		}

	}
	/**
	 * @param profId
	 * @return
	 */
	@GET
	@Path("/viewProfessorDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String,String> viewMyDetails(@NotNull @QueryParam("profId") String profId) {

		HashMap<String, String> map = new HashMap<>();
		try {
			Professor prof = new Professor(professorInterface.getProffProfleById(profId).getpDepartment(),professorInterface.getProffProfleById(profId).getpDesignation());
			map.put("Department",prof.getpDepartment() );
	        map.put("Designation",prof.getpDesignation());
			return map;
		} catch (Exception ex) {
			return null;
		}
		

	}
	

}
