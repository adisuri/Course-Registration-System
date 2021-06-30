package com.flipkart.service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.flipkart.bean.Notification;


/**
 * @author JEDI-7
 *
 */
public interface NotificationInterface {
	
	/**
	 * Method to send notification
	 * @param referenceId: Payment Id
	 * @param studentId: student to be notified
	 * @param message: Message to be sent
	 * @return notification id for the record added in the database
	 */

	public String sendNotification(String message,String studentId,String referenceId);
	
	/**
	 * Method to return UUID for a transaction
	 * @param id: notification id added in the database
	 * @return transaction id of the payment
	 */
	public String getReferenceId(String id); 
	
	/**
	 * @param StudentId
	 * @return List Of All Notifications
	 * @throws SQLException
	 */
	public List<Notification> getAllNotifications(String StudentId) throws SQLException;
	
	/**
	 * @param StudentId
	 * @param amount
	 * @param status
	 * @param paymentType
	 * @return
	 * @throws SQLException
	 */
	public String addPayment(String StudentId,int amount,boolean status,String paymentType) throws SQLException;
	
	/**
	 * @param StudentId
	 * @return 
	 * @throws SQLException
	 */
	public String updatePayment(String StudentId,String Mode) throws SQLException;
	
	
	
	
}