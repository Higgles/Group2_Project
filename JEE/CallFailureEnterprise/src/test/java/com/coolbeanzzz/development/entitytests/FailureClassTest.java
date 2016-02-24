package com.coolbeanzzz.development.entitytests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.coolbeanzzz.development.entities.FailureClass;
import com.coolbeanzzz.development.tools.Convert;

public class FailureClassTest {
	
	

	@Test
	public void testEquals(){
		FailureClass fclass=new FailureClass(0, "desc");
		FailureClass fclass2=new FailureClass(0,"desc");
		FailureClass fclass3=new FailureClass(69,"desc");
		assertFalse(fclass.equals(null));
		assertTrue(fclass.equals(fclass));
		assertTrue(fclass.equals(fclass2));
		assertFalse(fclass.equals(new String()));
		assertFalse(fclass.equals(fclass3));
	}
	
	@Test
	public void testHashcode(){
		FailureClass fclass=new FailureClass(0, "desc");
		assertEquals(3080786,fclass.hashCode());
		
	}

}

