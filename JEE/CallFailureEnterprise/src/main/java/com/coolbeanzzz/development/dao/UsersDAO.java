/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.dao;

import java.util.Collection;

import com.coolbeanzzz.development.entities.Users;

public interface UsersDAO {
	
	/**
	 * Add a row to UsersTable
	 * @param Users object 
	 */
	void addUser(Users user);
	
	/**
	 * Retrieves all rows from UsersTable
	 * @return Collection of UsersTable rows
	 */
	Collection<Users> getAllUsers();
	
	/**
	 * Deletes a row from UsersTable
	 * @param User object
	 * @return Users object
	 */
	Users removeUser(String username);
	
	/**
	 * Edit a row in UsersTable
	 * @param user to be updated
	 */
	void updateUser(Users user);
	
	
	
}
