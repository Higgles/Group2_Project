package com.coolbeanzzz.development.services;


import java.util.Collection;
import javax.ejb.Local;
import com.coolbeanzzz.development.entities.Users;


@Local
public interface UsersService {

	public Collection<Users> getCatalog();
	 
}
