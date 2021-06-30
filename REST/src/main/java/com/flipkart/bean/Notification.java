/**
 * 
 */
package com.flipkart.bean;

/**
 * @author JEDI-7
 *
 */
public class Notification {
	private String notifyId;
	private String sId;
	private String msg;
	private String refId;
	
	
	
	/**
	 * @param notifyId
	 * @param sId
	 * @param msg
	 * @param refId
	 */
	public Notification(String notifyId, String sId, String msg, String refId) {
		super();
		this.notifyId = notifyId;
		this.sId = sId;
		this.msg = msg;
		this.refId = refId;
	}
	/**
	 * @return the notifyId
	 */
	public String getNotifyId() {
		return notifyId;
	}
	/**
	 * @param notifyId the notifyId to set
	 */
	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
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
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
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
	
}
