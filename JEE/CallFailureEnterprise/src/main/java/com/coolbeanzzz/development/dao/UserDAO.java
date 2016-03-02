package com.coolbeanzzz.development.dao;

import java.io.File;
import java.util.Collection;

import org.json.simple.JSONArray;

import com.coolbeanzzz.development.entities.*;  // ???
import com.coolbeanzzz.development.entities.User;

public interface UserDAO {
	
	Collection<User> getAllUsers();
	
	
	
}
