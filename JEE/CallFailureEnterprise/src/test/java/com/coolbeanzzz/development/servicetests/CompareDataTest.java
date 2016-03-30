//package com.coolbeanzzz.development.servicetests;
//
//import static org.junit.Assert.*;
//
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Iterator;
//
//import org.apache.commons.io.FileUtils;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.junit.Test;
//
//import com.coolbeanzzz.development.tools.CompareData;
//
//
//public class CompareDataTest {
//	
//	@Test
//	public void testConversion() throws IOException, ParseException {
//		FileUtils.cleanDirectory(new File("/home/user1/json"));
//		Collection<Integer> failureClasses = new ArrayList<Integer>();
//		failureClasses.add(1);
//		Collection<Integer> eventIds = new ArrayList<Integer>();
//		eventIds.add(4098);
//		Collection<Integer> causeCodes = new ArrayList<Integer>();
//		causeCodes.add(0);
//		Collection<Integer> mcc = new ArrayList<Integer>();
//		mcc.add(344);
//		Collection<Integer> mnc = new ArrayList<Integer>();
//		mnc.add(930);
//		Collection<Integer> ueTypes = new ArrayList<Integer>();
//		ueTypes.add(21060800);
//		Files.copy(new File("/home/user1/test_baseData.json").toPath(), new File("/home/user1/json/Base Data.json").toPath());
//		CompareData cp=new CompareData(eventIds,causeCodes,failureClasses,mcc,mnc,ueTypes);
//		cp.compareData();
//		
//		JSONParser parser = new JSONParser();
//		
//		Object baseDataObj = parser.parse(new FileReader(new File("/home/user1/json/Base Data.json")));
//		JSONArray baseArray = (JSONArray) baseDataObj;
//		Iterator<?> iterator = baseArray.iterator();
//		JSONObject baseData = (JSONObject) iterator.next();
//		Object validDataObj = parser.parse(new FileReader(new File("/home/user1/json/validData.json")));
//		JSONArray validArray = (JSONArray) validDataObj;
//		Iterator<?> iteratorVal = validArray.iterator();
//		JSONObject validData = (JSONObject) iteratorVal.next();
//	
//		assertEquals(baseData.get("Event Id"), validData.get("Event Id"));
//		Object erroneousDataObj = parser.parse(new FileReader(new File("/home/user1/json/erroneousData.json")));
//		JSONArray erroneousArray = (JSONArray) erroneousDataObj;
//		Iterator<?> iteratorErr = erroneousArray.iterator();
//		JSONObject erroneousData = (JSONObject) iteratorErr.next();
//		baseData = (JSONObject) iterator.next();
//		assertEquals(baseData.get("Event Id"), erroneousData.get("Event Id"));
//		FileUtils.cleanDirectory(new File("/home/user1/json"));
//	}
//
//}
