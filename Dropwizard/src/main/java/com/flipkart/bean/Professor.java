/**
 * 
 */
package com.flipkart.bean;

/**
 * @author JEDI-7
 *
 */
public class Professor extends User {
	private String pDepartment;
	/**
	 * @param pDepartment
	 * @param pDesignation
	 */
	public Professor() {
		
	}
	public Professor(String pDepartment, String pDesignation) {
		//super();
		this.pDepartment = pDepartment;
		this.pDesignation = pDesignation;
	}

	private String pDesignation;

	/**
	 * @return the pDepartment
	 */
	public String getpDepartment() {
		return pDepartment;
	}

	/**
	 * @param pDepartment the pDepartment to set
	 */
	public void setpDepartment(String pDepartment) {
		this.pDepartment = pDepartment;
	}

	/**
	 * @return the pDesignation
	 */
	public String getpDesignation() {
		return pDesignation;
	}

	/**
	 * @param pDesignation the pDesignation to set
	 */
	public void setpDesignation(String pDesignation) {
		this.pDesignation = pDesignation;
	}

}
