/**
 * 
 */
package com.flipkart.bean;

import java.util.Date;

/**
 * @author JEDI-7
 *
 */
public class User {
	private String uId;
	private String uName;
	private String uPwd;
	private Date uCrDate;

	/**
	 * @return the uId
	 */
	public String getuId() {
		return uId;
	}

	/**
	 * @param uId the uId to set
	 */
	/**
	 * @param uId
	 */
	public void setuId(String uId) {
		this.uId = uId;
	}

	/**
	 * @return the uName
	 */
	public String getuName() {
		return uName;
	}

	/**
	 * @param uName the uName to set
	 */
	public void setuName(String uName) {
		this.uName = uName;
	}

	/**
	 * @return the uPwd
	 */
	public String getuPwd() {
		return uPwd;
	}

	/**
	 * @param uPwd the uPwd to set
	 */
	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}

	/**
	 * @return the uCrDate
	 */
	public Date getuCrDate() {
		return uCrDate;
	}

	/**
	 * @param createDate the uCrDate to set
	 */
	public void setuCrDate(Date createDate) {
		this.uCrDate = createDate;
	}

}
