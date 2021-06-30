/**
 * 
 */
package com.flipkart.bean;

/**
 * @author JEDI-7
 *
 */
public class Student extends User {

	private String sBranch;
	private int sRollNo;
	private boolean isApproved; // Reg approval of student by admin.

	/**
	 * @return the sBranch
	 */
	public String getsBranch() {
		return sBranch;
	}

	/**
	 * @param sBranch the sBranch to set
	 */
	public void setsBranch(String sBranch) {
		this.sBranch = sBranch;
	}

	/**
	 * @return the sRollNo
	 */
	public int getsRollNo() {
		return sRollNo;
	}

	/**
	 * @param sRollNo the sRollNo to set
	 */
	public void setsRollNo(int sRollNo) {
		this.sRollNo = sRollNo;
	}

	/**
	 * @return the isApproved
	 */
	public boolean isApproved() {
		return isApproved;
	}

	/**
	 * @param isApproved the isApproved to set
	 */
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

}
