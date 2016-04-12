package com.coolbeanzzz.development.entitytests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coolbeanzzz.development.entities.UETable;

public class UETableTest {

	@Test
	public void testEquals(){
		UETable ueTable=new UETable(0,"desc","desc","desc","desc","desc","desc","desc","desc");
		UETable ueTable2=new UETable(0,"desc","desc","desc","desc","desc","desc","desc","desc");

		assertFalse(ueTable.equals(null));

		assertTrue(ueTable.equals(ueTable));
		assertFalse(ueTable.equals(new String()));
		assertTrue(ueTable.equals(ueTable2));
		assertTrue(ueTable.equals(ueTable));
		
		ueTable2.setInputMode("wrong desc");
		assertFalse(ueTable.equals(ueTable2));
		ueTable.setInputMode(null);
		assertFalse(ueTable.equals(ueTable2));
		
		ueTable2.setOs("wrong desc");
		assertFalse(ueTable.equals(ueTable2));
		ueTable.setOs(null);
		assertFalse(ueTable.equals(ueTable2));
		
		ueTable2.setUeType("wrong desc");
		assertFalse(ueTable.equals(ueTable2));
		ueTable.setUeType(null);
		assertFalse(ueTable.equals(ueTable2));
		
		ueTable2.setVendorName("wrong desc");
		assertFalse(ueTable.equals(ueTable2));
		ueTable.setVendorName(null);
		assertFalse(ueTable.equals(ueTable2));
		
		ueTable2.setModel("wrong desc");
		assertFalse(ueTable.equals(ueTable2));
		ueTable.setModel(null);
		assertFalse(ueTable.equals(ueTable2));
		
		ueTable2.setAccessCapability("wrong desc");
		assertFalse(ueTable.equals(ueTable2));
		ueTable.setAccessCapability(null);
		assertFalse(ueTable.equals(ueTable2));

		ueTable2.setManufacturer("wrong desc");
		assertFalse(ueTable.equals(ueTable2));
		ueTable.setManufacturer(null);
		assertFalse(ueTable.equals(ueTable2));
		
		ueTable2.setMarketingName("wrong desc");
		assertFalse(ueTable.equals(ueTable2));
		ueTable.setMarketingName(null);
		assertFalse(ueTable.equals(ueTable2));
		
		ueTable2.setTac(69);
		assertFalse(ueTable.equals(ueTable2));
	}
	
	
	@Test
	public void testHashcode(){
		UETable ueTable=new UETable(0,"desc","desc","desc","desc","desc","desc","desc","desc");
		assertEquals(-221738593,ueTable.hashCode());
		
	}
	
}
