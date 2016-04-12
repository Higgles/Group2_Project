package com.coolbeanzzz.development.entitytests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coolbeanzzz.development.entities.EventCause;

public class EventCauseTest {

	@Test
	public void testEquals(){
		EventCause eCause=new EventCause(0, 0,"desc");
		EventCause eCause2=new EventCause(0, 0,"desc");
		EventCause eCause3=new EventCause(69,0,"desc");
		EventCause eCause4=new EventCause(0,69,"desc");
		assertFalse(eCause.equals(null));
		assertTrue(eCause.equals(eCause));
		assertFalse(eCause.equals(new String()));
		assertTrue(eCause.equals(eCause2));
		assertFalse(eCause.equals(eCause3));
		assertFalse(eCause.equals(eCause4));
	}
	
	
	@Test
	public void testHashcode(){
		EventCause eCause=new EventCause(0, 0,"desc");
		assertEquals(961,eCause.hashCode());
		
	}
}
