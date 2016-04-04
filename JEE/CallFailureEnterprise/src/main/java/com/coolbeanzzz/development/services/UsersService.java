package com.coolbeanzzz.development.services;


import java.util.Collection;

import javax.ejb.Local;

import com.coolbeanzzz.development.entities.Users;


@Local
public interface UsersService {

	/**
	 * Add a user to Users table in database
	 * @param users object
	 */
	public void addUser(Users user);
	
	/**
	 * Gets all users from the Users within the database
	 * @return a collection of users
	 */
	public Collection<Users> getAllUsers();
	
	/**
	 * Remove a user
	 * @param username
	 */
	public Users removeUser(String username);
	
	/**
	 * update a user
	 * @param users object
	 */
	public void updateUser(Users user);
	 
}
