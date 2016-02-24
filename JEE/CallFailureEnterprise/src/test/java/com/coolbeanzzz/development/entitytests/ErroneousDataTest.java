package com.coolbeanzzz.development.entitytests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.coolbeanzzz.development.entities.ErroneousData;


public class ErroneousDataTest {
	
	
	@Test
	public void testSettersGetters(){
		
		ErroneousData eD = new ErroneousData();
		ErroneousData eData = new ErroneousData(0,"desc",0,"desc",0,0,0,"desc","desc","desc","desc","desc","desc","desc","desc");
		// int id
		eData.setId(0);
		assertEquals(eData.getId(),0);
		// String dateTime
		eData.setDateTime("desc");
		assertEquals(eData.getDateTime(),"desc");
		// int eventId
		eData.setEventId(0);
		assertEquals(eData.getEventId(),0);
		// String failureClass, 
		eData.setFailureClass("desc");
		assertEquals(eData.getFailureClass(),"desc");
		// int ueType,
		eData.setUeType(0);
		assertEquals(eData.getUeType(),0);
		// int market, 
		eData.setMarket(0);
		assertEquals(eData.getMarket(),0);
		// int operator,
		eData.setOperator(0);
		assertEquals(eData.getOperator(),0);
		// String cellId, 
		eData.setCellId("desc");
		assertEquals(eData.getCellId(),("desc"));
		// String duration, 
		eData.setDuration("desc");
		assertEquals(eData.getDuration(),"desc");
		// String causeCode, 
		eData.setCauseCode("desc");
		assertEquals(eData.getCauseCode(),"desc");
		// String neVersion,
		eData.setNeVersion("desc");
		assertEquals(eData.getNeVersion(),"desc");
		// String imsi, 
		eData.setImsi("desc");
		assertEquals(eData.getImsi(),"desc");
		// String hier3_Id, 
		eData.setHier3_Id("desc");
		assertEquals(eData.getHier3_Id(),"desc");
		// String hier32_Id, 
		eData.setHier32_Id("desc");
		assertEquals(eData.getHier32_Id(),"desc");
		// String her321_Id
		eData.setHier321_Id("desc");
		assertEquals(eData.getHier321_Id(),"desc");
	}
	
	
	
}
