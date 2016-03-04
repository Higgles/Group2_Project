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
	
		when(mockedUsersDao.getAllUsers()).thenReturn(bds);
						
		UsersService=new UsersServiceEJB();
		UsersService.setDao(mockedUsersDao);
	}

	@Test
	public void getCatalogtest() {
		Collection<Users> users = UsersService.getCatalog();
		assertEquals(users1, users.iterator().next());
	}
}
