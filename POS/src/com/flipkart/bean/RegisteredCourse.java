/**
 * 
 */
package com.flipkart.bean;

/**
 * @author JEDi-7
 *
 */
public class RegisteredCourse {
	private String cCode;
	private String studentId;
	private int sem;
	private Grade grade;
	
	/**
	 * @param cCode
	 * @param studentId
	 * @param sem
	 * @param grade
	 */
	public RegisteredCourse(String cCode, String studentId, int sem, Grade grade) {
		super();
		this.cCode = cCode;
		this.studentId = studentId;
		this.sem = sem;
		this.grade = grade;
	}
	/**
	 * @return the cCode
	 */
	public String getcCode() {
		return cCode;
	}
	/**
	 * @param cCode the cCode to set
	 */
	public void setcCode(String cCode) {
		this.cCode = cCode;
	}
	/**
	 * @return the sRollNo
	 */
	public String getsstudentId() {
		return studentId;
	}
	/**
	 * @param sRollNo the sRollNo to set
	 */
	public void setsstudentId(String studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the sem
	 */
	public int getSem() {
		return sem;
	}
	/**
	 * @param sem the sem to set
	 */
	public void setSem(int sem) {
		this.sem = sem;
	}
	/**
	 * @return the grade
	 */
	public Grade getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
}
