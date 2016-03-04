package com.coolbeanzzz.development.entitytests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coolbeanzzz.development.entities.Users;

public class UsersTest {

	@Test
	public void testEquals(){
		Users userTable=new Users(0,"desc","desc","CSR");
		Users userTable2=new Users(0,"desc","desc","CSR");

		assertFalse(userTable.equals(null));
		assertTrue(userTable.equals(userTable));
		assertFalse(userTable.equals(new String()));
		assertTrue(userTable.equals(userTable2));
		assertTrue(userTable.equals(userTable));

		userTable2.setUserType("wrong desc");
		assertFalse(userTable.equals(userTable2));
		userTable.setUserType(null);
		assertFalse(userTable.equals(userTable2));

		userTable2.setPasskey("wrong desc");
		assertFalse(userTable.equals(userTable2));
		userTable.setPasskey(null);
		assertFalse(userTable.equals(userTable2));

		userTable2.setUsername("wrong desc");
		assertFalse(userTable.equals(userTable2));
		userTable.setUsername(null);
		assertFalse(userTable.equals(userTable2));
		
		userTable2.setId(2);
		assertFalse(userTable.equals(userTable2));

	}
	
	@Test
	public void testHashcode(){
		Users userTable=new Users(0,"desc","desc","desc");
		assertEquals(-1235777550,userTable.hashCode());

	}
}
