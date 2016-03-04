package com.coolbeanzzz.development.dao;

import java.util.Collection;

import com.coolbeanzzz.development.entities.Users;

public interface UsersDAO {
	
	void addUser(Users user);
	Collection<Users> getAllUsers();
	
	
	
}
