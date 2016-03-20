package com.coolbeanzzz.development.services;


import java.util.Collection;

import javax.ejb.Local;

import com.coolbeanzzz.development.entities.Users;


@Local
public interface UsersService {

	
	public void addUser(Users user);
	public Collection<Users> getAllUsers();
	public Users removeUser(String username);
	public void updateUser(Users user);
	 
}
