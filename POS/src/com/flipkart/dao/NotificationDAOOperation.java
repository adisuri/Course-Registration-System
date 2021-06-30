package com.flipkart.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.flipkart.utils.DBUtil;
import com.flipkart.bean.Notification;
import com.flipkart.constants.SQLQueriesConstanst;

/**
 * @author JEDI-7 Class to implement Notification Dao Operations Used for adding
 *         the notification to the database Used for getting the payment ID of
 *         the transaction using the notification ID Recording the payments
 */

public class NotificationDAOOperation implements NotificationDAOInterface {

	private static volatile NotificationDAOOperation instance = null;
	private static Logger logger = Logger.getLogger(NotificationDAOOperation.class);

	/**
	 * Default Constructor
	 */
	private NotificationDAOOperation() {

	}

	/**
	 * Method to make UserDAOImpl Singleton
	 * 
	 * @return
	 */
	public static NotificationDAOOperation getInstance() {

		if (instance == null) {
			synchronized (NotificationDAOOperation.class) {
				instance = new NotificationDAOOperation();
			}
		}
		return instance;
	}

	/**
	 * Send Notification using SQL commands
	 * 
	 * @param Message:     Message to be sent
	 * @param studentId:   student to be notified
	 * @param referenceId: ReferenceId of the payment
	 * @return notification id for the record added in the database
	 * @throws SQLException
	 */
	@Override
	public String sendNotification(String Message, String StudentId, String ReferenceId) throws SQLException {
		String notificationId = "";
		Connection connection = DBUtil.getConnection();
		try {
			// Insert Notification
			notificationId = UUID.randomUUID().toString();
			PreparedStatement ps = connection.prepareStatement(SQLQueriesConstanst.INSERT_NOTIFICATION);
			ps.setString(1, notificationId);
			ps.setString(2, Message);
			ps.setString(3, StudentId);
			ps.setString(4, ReferenceId);

			ps.executeUpdate();
		} catch (SQLException ex) {
			throw ex;
		}
		return notificationId;
	}

	/**
	 * @param notificatinId - Id of the notification received by the user
	 * @return ReferenceId - Payment Id of the transaction
	 * @throws SQLException
	 */
	@Override
	public String getReferenceId(String notificationId) throws SQLException {
		String referenceId = null;
		Connection connection = DBUtil.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstanst.GET_REFERENCE_ID);
			statement.setString(1, notificationId);

			ResultSet results = statement.executeQuery();
			if (results.next())
				referenceId = results.getString(4);
		} catch (SQLException ex) {
			throw ex;
		}
		return referenceId;
	}

	/**
	 * Perform Payment actions using SQL commands
	 * 
	 * @param studentId:     Id of the student for which the payment is done
	 * @param modeOfPayment: mode of payment used, defined in enum
	 * @param amount
	 * @return: reference id of the transaction
	 * @throws SQLException
	 */
	@Override
	public String addPayment(String StudentId, int amount, boolean status, String paymentType) throws SQLException {
		String referenceId;
		Connection connection = DBUtil.getConnection();

		try {
			referenceId = UUID.randomUUID().toString();
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstanst.INSERT_PAYMENT);
			statement.setString(1, referenceId);
			statement.setString(2, StudentId);
			statement.setInt(3, amount);
			statement.setBoolean(4, status);
			statement.setString(5, paymentType);
			statement.executeUpdate();
		} catch (SQLException ex) {
			throw ex;
		}
		return referenceId;
	}

	/**
	 * Perform Payment actions using SQL commands
	 * 
	 * @param studentId: Id of the student for which the payment is done
	 * @return: reference id of the transaction
	 * @throws SQLException
	 */
	@Override
	public String updatePayment(String StudentId, String Mode) throws SQLException {
		String referenceId = null;
		Connection connection = DBUtil.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstanst.GET_REFID_PAYMENT);
			statement.setString(1, StudentId);
			ResultSet results = statement.executeQuery();
			if (results.next())
				referenceId = results.getString(1);

			statement = connection.prepareStatement(SQLQueriesConstanst.UPDATE_PAYMENT);
			statement.setString(1, Mode);
			statement.setString(2, StudentId);
			statement.executeUpdate();
		} catch (SQLException ex) {
			throw ex;
		}
		return referenceId;
	}

	@Override
	public List<Notification> getAllNotifications(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		List<Notification> allnotifications = new ArrayList<Notification>();
		Connection connection = DBUtil.getConnection();

		try {
			// select * from notification where studentId=?
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstanst.GET_ALL_NOTIFICATIONS);
			statement.setString(1, studentId);

			ResultSet results = statement.executeQuery();

			while (results.next()) {
				allnotifications
						.add(new Notification(results.getString("notificationId"), results.getString("studentId"),
								results.getString("message"), results.getString("referenceId")));
			}
		} catch (SQLException ex) {
			throw ex;
		}

		return allnotifications;
	}

}
