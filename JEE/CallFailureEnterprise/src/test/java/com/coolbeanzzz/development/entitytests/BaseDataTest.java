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
		BaseData bData2 =
		new BaseData(0,"desc","desc","desc","desc","desc","desc","desc","desc",eventCause,mccMnc,ueTable,failureClass);
		
		// int id
		bData.setId(0);
		assertEquals(bData.getId(),0);
		// String dateTime
		bData.setDateTime("desc");
		assertEquals(bData.getDateTime(),"desc");
		// String cellId
		bData.setCellId("desc");
		assertEquals(bData.getCellId(),("desc"));
		// String duration, 
		bData.setDuration("desc");
		assertEquals(bData.getDuration(),"desc");
		//String neVersion, 
		bData.setNeVersion("desc");
		assertEquals(bData.getNeVersion(),"desc");
		//String imsi, 
		bData.setImsi("desc");
		assertEquals(bData.getImsi(),"desc");
		// String hier3_Id, 
		bData.setHier3_Id("desc");
		assertEquals(bData.getHier3_Id(),"desc");
		// String hier32_Id, 
		bData.setHier32_Id("desc");
		assertEquals(bData.getHier32_Id(),"desc");
		// String her321_Id
		bData.setHier321_Id("desc");
		assertEquals(bData.getHier321_Id(),"desc"); 
				
//		EventCause eventCause
		bData.setEventCause(eventCause);
		assertEquals(bData.getEventCause(),eventCause);
//		MccMnc mccmnc
		bData.setMccmnc(mccMnc);
		assertEquals(bData.getMccmnc(),mccMnc);
//		UETable ueTable
		bData.setUeTable(ueTable);
		assertEquals(bData.getUeTable(),ueTable);
//		FailureClass failureClass
		bData.setFailureClass(failureClass);
		assertEquals(bData.getFailureClass(),failureClass);
		
	}
	
	
}
