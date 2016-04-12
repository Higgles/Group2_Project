package com.coolbeanzzz.development.servicetests;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.coolbeanzzz.development.dao.UsersDAO;
import com.coolbeanzzz.development.dao.jpa.JPAUsersDAO;
import com.coolbeanzzz.development.entities.Users;
import com.coolbeanzzz.development.services.UsersServiceEJB;

public class UsersServiceTest {
	private UsersServiceEJB UsersService;
	private Users users1; 
	
	@Before
	public void setUp() throws Exception {
		UsersDAO mockedUsersDao = mock(JPAUsersDAO.class);
		users1 = new Users(0, "", "", "");
		Collection<Users> bds = new ArrayList<>();
		bds.add(users1);
		
	
		when(mockedUsersDao.getAllUsers(1, "", -1)).thenReturn(bds);
		when(mockedUsersDao.removeUser("")).thenReturn(users1);
		UsersService=new UsersServiceEJB();
		UsersService.setDao(mockedUsersDao);
	}

	@Test
	public void getUsersTest() {
		Collection<Users> users = UsersService.getAllUsers(1, "", -1);
		assertEquals(users1, users.iterator().next());
		assertTrue(users.iterator().hasNext());
	}
	
	@Test
	public void removeUsersTest() {
		Users user = UsersService.removeUser("");
		assertEquals(users1,user);
	}
	
//	@Test
//	public void addUserTest() {
//		
//		users3 = new Users(1, "", "kony", "");
//		UsersService.addUser(users3);
//		Collection<Users> users = UsersService.getAllUsers();
//		assertEquals(users3, users.iterator().next());
//		
//	}	

	
}
