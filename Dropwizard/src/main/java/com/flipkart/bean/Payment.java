/**
 * 
 */
package com.flipkart.bean;

/**
 * @author JEDI-7
 *
 */
public class Payment {
  
	private String refId;
	private String sId;
	private int amount;
	private boolean status;
	private String pType;
	/**
	 * @return the refId
	 */
	public String getRefId() {
		return refId;
	}
	/**
	 * @param refId the refId to set
	 */
	public void setRefId(String refId) {
		this.refId = refId;
	}
	/**
	 * @return the sId
	 */
	public String getsId() {
		return sId;
	}
	/**
	 * @param sId the sId to set
	 */
	public void setsId(String sId) {
		this.sId = sId;
	}
	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * @return the pType
	 */
	public String getpType() {
		return pType;
	}
	/**
	 * @param pType the pType to set
	 */
	public void setpType(String pType) {
		this.pType = pType;
	}
	
}
