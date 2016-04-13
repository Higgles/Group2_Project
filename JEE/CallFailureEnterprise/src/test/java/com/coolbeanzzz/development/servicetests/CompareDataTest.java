package com.coolbeanzzz.development.servicetests;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.coolbeanzzz.development.tools.CompareData;


public class CompareDataTest {
	
	@Test
	public void testConversion() throws IOException, ParseException {
		Collection<Integer> failureClasses = new ArrayList<Integer>();
		failureClasses.add(1);
		Collection<Integer> eventIds = new ArrayList<Integer>();
		eventIds.add(4098);
		Collection<Integer> causeCodes = new ArrayList<Integer>();
		causeCodes.add(0);
		Collection<Integer> mcc = new ArrayList<Integer>();
		mcc.add(344);
		Collection<Integer> mnc = new ArrayList<Integer>();
		mnc.add(930);
		Collection<Integer> ueTypes = new ArrayList<Integer>();
		ueTypes.add(21060800);
		
		JSONParser parser = new JSONParser();
		CompareData cp=new CompareData(eventIds,causeCodes,failureClasses,mcc,mnc,ueTypes);
		JSONArray baseDataArray = (JSONArray) parser.parse(new FileReader("test_baseData.json"));
		ArrayList<JSONArray> baseArray = cp.compareData(baseDataArray);
		
		JSONArray validDataArray = baseArray.get(0);
		JSONArray erroneousDataArray = baseArray.get(1);
		
		Iterator<?> iterator = baseDataArray.iterator();
		JSONObject baseData = (JSONObject) iterator.next();
		
		Iterator<?> iteratorVal = validDataArray.iterator();
		JSONObject validData = (JSONObject) iteratorVal.next();
	
		assertEquals(baseData.get("Event Id"), validData.get("Event Id"));
		
		Iterator<?> iteratorErr = erroneousDataArray.iterator();
		JSONObject erroneousData = (JSONObject) iteratorErr.next();
		baseData = (JSONObject) iterator.next();
		
		assertEquals(baseData.get("Event Id"), erroneousData.get("Event Id"));
	}

}
