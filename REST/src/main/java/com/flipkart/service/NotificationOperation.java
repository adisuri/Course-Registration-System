/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.flipkart.bean.Notification;
import com.flipkart.dao.NotificationDAOInterface;
import com.flipkart.dao.NotificationDAOOperation;
import com.flipkart.dao.ProfessorDAOInterface;
import com.flipkart.dao.ProfessorDAOOperation;

/**
 * @author JEDI-7
 *
 */
public class NotificationOperation implements NotificationInterface{
	
	
	
	private static volatile NotificationOperation instance = null;
	NotificationDAOInterface notificationDAOInterface= NotificationDAOOperation.getInstance();
		
	
	private NotificationOperation()
	{
		
	}
	
	/**
	 * Method to make NotificationOpearation Singleton
	 * @return
	 */
	public static NotificationOperation getInstance(){
		
		if(instance == null){
			synchronized(NotificationOperation.class){
				instance= new NotificationOperation();
			}
		}
		return instance;
	}
	
	
	/**
	 * Method to send notification
	 * @param type: type of the notification to be sent
	 * @param studentId: student to be notified
	 * @param modeOfPayment: payment mode used
	 * @return notification id for the record added in the database
	 */
	@Override
	public String sendNotification(String message,String studentId,String referenceId) {
		String notificationId="";
		try
		{
			notificationId=notificationDAOInterface.sendNotification(message,studentId,referenceId);
			
		}
		catch(SQLException ex)
		{
			System.out.println("Error Occured :( " + ex.toString());
		}
		return notificationId;
	}
	
	@Override
	public String getReferenceId(String NotificationId) {
		String referenceId="";
		try {
			referenceId = notificationDAOInterface.getReferenceId(NotificationId);
		}
		catch(SQLException ex) {
			System.out.println("Error Occured :( " + ex.toString());
		}
		return referenceId;
	}

	@Override
	public List<Notification> getAllNotifications(String StudentId) {
		List<Notification> notifications = null;
		try {
			notifications = notificationDAOInterface.getAllNotifications(StudentId);
		} 
		catch(SQLException ex) {
			System.out.println("Error Occured :( " + ex.toString());
		}
		return notifications;
	}
	
	@Override
	public String addPayment(String StudentId,int amount,boolean status,String paymentType) throws SQLException {
		String referenceId = null;
		try {
			referenceId = notificationDAOInterface.addPayment(StudentId,amount,status,paymentType);
		} 
		catch(SQLException ex) {
			throw ex;
		}
		return referenceId;
	}
	
	/**
	 * @param StudentId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public String updatePayment(String StudentId,String Mode) throws SQLException{
		String referenceId = null;
		try {
			referenceId = notificationDAOInterface.updatePayment(StudentId,Mode);
		} 
		catch(SQLException ex) {
			throw ex;
		}
		return referenceId;
	}
}







