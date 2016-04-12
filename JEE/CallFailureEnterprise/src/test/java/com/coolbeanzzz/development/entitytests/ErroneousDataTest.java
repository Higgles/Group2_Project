package com.coolbeanzzz.development.entitytests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.coolbeanzzz.development.entities.ErroneousData;


public class ErroneousDataTest {
	
	
	@Test
	public void testSettersGetters(){
		
		ErroneousData eData = new ErroneousData(0,"desc",0,"desc",0,0,0,"desc","desc","desc","desc","desc","desc","desc","desc");
		
		eData.setId(0);
		assertEquals(eData.getId(),0);
		
		eData.setDateTime("desc");
		assertEquals(eData.getDateTime(),"desc");
		
		eData.setEventId(0);
		assertEquals(eData.getEventId(),0);
		
		eData.setFailureClass("desc");
		assertEquals(eData.getFailureClass(),"desc");
		
		eData.setUeType(0);
		assertEquals(eData.getUeType(),0);
		 
		eData.setMarket(0);
		assertEquals(eData.getMarket(),0);
		
		eData.setOperator(0);
		assertEquals(eData.getOperator(),0);
		
		eData.setCellId("desc");
		assertEquals(eData.getCellId(),("desc"));
		
		eData.setDuration("desc");
		assertEquals(eData.getDuration(),"desc");
		 
		eData.setCauseCode("desc");
		assertEquals(eData.getCauseCode(),"desc");
		
		eData.setNeVersion("desc");
		assertEquals(eData.getNeVersion(),"desc");
		
		eData.setImsi("desc");
		assertEquals(eData.getImsi(),"desc");
		
		eData.setHier3_Id("desc");
		assertEquals(eData.getHier3_Id(),"desc");
		
		eData.setHier32_Id("desc");
		assertEquals(eData.getHier32_Id(),"desc");
		
		eData.setHier321_Id("desc");
		assertEquals(eData.getHier321_Id(),"desc");
	}
	
	
	
}
