/**
 * 
 */
package com.flipkart.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import com.flipkart.bean.*;


/**
 * @author JEDI-07
 *
 */
public interface NotificationDAOInterface {

	/**
	 * Send Notification using SQL commands
	 * @param message: Message to be sent
	 * @param studentId: student to be notified
	 * @param referenceId: ID for the transaction
	 * @return notification id for the notification added in the database
	 * @throws SQLException
	 */
	public String sendNotification(String message,String studentId,String referenceId) throws SQLException;
	
	/**
	 * @param notificationId
	 * @return reference Id of payment
	 * @throws SQLException
	 */
	public String getReferenceId(String notificationId) throws SQLException;
	
	/**
	 * @param StudentId
	 * @param amount
	 * @param status
	 * @param paymentType
	 * @return reference ID of payment
	 * @throws SQLException
	 */
	public String addPayment(String StudentId,int amount,boolean status,String paymentType) throws SQLException;
	
	/**
	 * @param StudentId
	 * @return
	 * @throws SQLException
	 */
	public String updatePayment(String StudentId,String mode) throws SQLException;
	
	/**
	 * @param studentId
	 * @return list of notifications
	 * @throws SQLException
	 */
	public List<Notification> getAllNotifications(String studentId) throws SQLException;
	
}
