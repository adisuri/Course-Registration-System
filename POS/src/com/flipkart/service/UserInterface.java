/**
 * 
 */
package com.flipkart.service;

import com.flipkart.exception.UserNotFoundException;

/**
 * @author JEDI-7
 *
 */
public interface UserInterface {
	

	/**
	 * @param userName
	 * @param newPassword
	 * @return status of request
	 * @throws UserNotFoundException
	 */
	public boolean updatePassword(String userName,String newPassword) throws UserNotFoundException;
	

	/**
	 * @param userName
	 * @param password
	 * @return if the credentials are correct or wrong
	 * @throws UserNotFoundException
	 */
	public boolean verifyCredentials(String userName,String password) throws UserNotFoundException;
	

	/**
	 * @param userName
	 * @return String : user role type
	 * @throws UserNotFoundException
	 */
	public String getUserRole(String userName) throws UserNotFoundException;
	
	
	
}
