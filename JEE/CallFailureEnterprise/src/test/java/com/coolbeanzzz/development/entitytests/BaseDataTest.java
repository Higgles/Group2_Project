package com.coolbeanzzz.development.entitytests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.coolbeanzzz.development.entities.BaseData;
import com.coolbeanzzz.development.entities.EventCause;
import com.coolbeanzzz.development.entities.FailureClass;
import com.coolbeanzzz.development.entities.MccMnc;
import com.coolbeanzzz.development.entities.UETable;

public class BaseDataTest {
	
	@Test
	public void testSettersGetters(){
		BaseData bData = new BaseData();
		
		EventCause eventCause = new EventCause();
		MccMnc mccMnc = new MccMnc();
		UETable ueTable = new UETable();
		FailureClass failureClass = new FailureClass();
		
		bData.setId(0);
		assertEquals(bData.getId(),0);
		
		bData.setDateTime("desc");
		assertEquals(bData.getDateTime(),"desc");
		
		bData.setCellId("desc");
		assertEquals(bData.getCellId(),("desc"));
		bData.setDuration("desc");
		assertEquals(bData.getDuration(),"desc");
		bData.setNeVersion("desc");
		assertEquals(bData.getNeVersion(),"desc");
		bData.setImsi("desc");
		assertEquals(bData.getImsi(),"desc");
		bData.setHier3_Id("desc");
		assertEquals(bData.getHier3_Id(),"desc");
		bData.setHier32_Id("desc");
		assertEquals(bData.getHier32_Id(),"desc");
		// String her321_Id
		bData.setHier321_Id("desc");
		assertEquals(bData.getHier321_Id(),"desc"); 
			
		bData.setEventCause(eventCause);
		assertEquals(bData.getEventCause(),eventCause);
		
		bData.setMccmnc(mccMnc);
		assertEquals(bData.getMccmnc(),mccMnc);

		bData.setUeTable(ueTable);
		assertEquals(bData.getUeTable(),ueTable);

		bData.setFailureClass(failureClass);
		assertEquals(bData.getFailureClass(),failureClass);
		
	}
	
	
}
