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
	Users removeUser(Users user);
	
	/**
	 * Deletes a row from UsersTable
	 * @param int of user id
	 * @return Users object
	 */
	Users removeUser(int id);
	
	/**
	 * Edit a row in UsersTable
	 * @param int of user id
	 */
	void updateUserUsingId(int id);
	
	
	
}
