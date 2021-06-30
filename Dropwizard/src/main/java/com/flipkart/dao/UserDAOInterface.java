/**
 * 
 */
package com.flipkart.dao;

import com.flipkart.exception.UserNotFoundException;

/**
 * @author prafu
 *
 */
public interface UserDAOInterface {
	
	/**
	 * Method to Verify Credentials for Logging In
	 * @param userId
	 * @param password
	 * @throws UserNotFoundException
	 * @return
	 */
	public boolean verifyCredentials(String userName,String password) throws UserNotFoundException;
	
	/**
	 * Method to obtain the user role
	 * @param userName
	 * @throws UserNotFoundException 
	 * @return
	 */
	public String getUserRole(String userName) throws UserNotFoundException;
	
	
	/**
	 * Method to update password of an account
	 * @param userID
	 * @param newPassword
	 * @throws UserNotFoundException 
	 * @return
	 */
	public boolean updatePassword(String userName, String newPassword) throws UserNotFoundException;

}
