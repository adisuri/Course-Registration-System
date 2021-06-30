/**
 * 
 */
package com.flipkart.bean;

/**
 * @author JEDI-7
 *
 */
public class ReportCard {
	private int studentId;
	private String[] courses;
	private String[] grade;
	private float cgpa;
	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the courses
	 */
	public String[] getCourses() {
		return courses;
	}
	/**
	 * @param courses the courses to set
	 */
	public void setCourses(String[] courses) {
		this.courses = courses;
	}
	/**
	 * @return the grade
	 */
	public String[] getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String[] grade) {
		this.grade = grade;
	}
	/**
	 * @return the cgpa
	 */
	public float getCgpa() {
		return cgpa;
	}
	/**
	 * @param cgpa the cgpa to set
	 */
	public void setCgpa(float cgpa) {
		this.cgpa = cgpa;
	}

	
}
