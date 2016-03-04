package com.coolbeanzzz.development.services;

import java.io.File;
import java.util.Collection;

import javax.ejb.Local;

import org.json.simple.JSONArray;

import com.coolbeanzzz.development.entities.Users;


@Local
public interface UsersService {

	public Collection<Users> getCatalog();
	 
}
