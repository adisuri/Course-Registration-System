/**
 * 
 */
package com.flipkart.exception;

/**
 * Fee reference id not found exception
 * @author JEDI-07
 */
public class FeeReferenceIdNotFoundException {
	private String feePaymentId;
	
	/**
	 * Constructor
	 * @param feePaymentId
	 * */
	public void FeeReferenceIdNotFoundException(String feePaymentId) {
		this.feePaymentId = feePaymentId;
	}
	
	/*
	 * Message returned when exception is thrown
	 * @return String: Error Message
	 * */
	public String getMessage() {
		return "Payment ID " + this.feePaymentId + " not found";
	}
}
