package com.coolbeanzzz.development.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CompareData {
	private JSONArray erroneousData = new JSONArray();
	private JSONArray validData = new JSONArray();
	
	private JSONObject baseData;
	private boolean dateCheck = true;
	
	private Collection<Integer> uniqueEventIds;
	private Collection<Integer> uniqueCauseCodes;
	
	private Collection<Integer> uniqueFailureCodes;
	
	private Collection<Integer> mccs;
	private Collection<Integer> mncs;
	
	private Collection<Integer> ueTypes;	
	
	public CompareData(Collection<Integer> uniqueEventIds, Collection<Integer> uniqueCauseCodes,
	Collection<Integer> uniqueFailureCodes, Collection<Integer> mccs, Collection<Integer> mncs,
	Collection<Integer> ueTypes){
		this.uniqueEventIds = uniqueEventIds;
		this.uniqueCauseCodes = uniqueCauseCodes;
		this.uniqueFailureCodes = uniqueFailureCodes;
		this.mccs = mccs;
		this.mncs = mncs;
		this.ueTypes = ueTypes;
	}
	
	/**
	 * Compare imported base data table against the reference tables
	 * to split it into erroneous data and valid data.
	 */
	public void compareData(){
		
		JSONParser parser = new JSONParser();
		
		int eventId;
		int causeCode;
		int failureCode;
		int mcc;
		int mnc;
		int ueType;
		
		String dateTime;

		try {

			Object baseDataObj = parser.parse(new FileReader(new File("/home/user1/json/Base Data.json")));

			JSONArray baseDataRows = (JSONArray) baseDataObj;
			
			Iterator<?> iterator = baseDataRows.iterator();
			
			while (iterator.hasNext()) {

				baseData = (JSONObject) iterator.next();
				
				dateTime = baseData.get("Date / Time").toString();
				
				dateFormatter(dateTime);
				
				eventId = checkInt(baseData.get("Event Id").toString());
				causeCode = checkInt(baseData.get("Cause Code").toString());
				failureCode = checkInt(baseData.get("Failure Class").toString());
				mcc = checkInt(baseData.get("Market").toString());
				mnc = checkInt(baseData.get("Operator").toString());
				ueType = checkInt(baseData.get("UE Type").toString());
				
				if(!uniqueEventIds.contains(eventId) || !uniqueCauseCodes.contains(causeCode) ||
						!uniqueFailureCodes.contains(failureCode) || !mccs.contains(mcc) || !mncs.contains(mnc)||
						!ueTypes.contains(ueType) || dateCheck == false){
					erroneousData.add(baseData);
				}
				else{
					validData.add(baseData);
				}
			}
			
			FileWriter fwValid = new FileWriter(new File("/home/user1/json/validData.json"));
			Iterator<?> iteratorValid = validData.iterator();
			fwValid.write("[");
			while (iteratorValid.hasNext()) {
				JSONObject validObject = (JSONObject) iteratorValid.next();
				fwValid.write(validObject.toJSONString() + ",");
			}
			fwValid.write("]");
			fwValid.close();
			
			FileWriter fwErroneous = new FileWriter(new File("/home/user1/json/erroneousData.json"));
			Iterator<?> iteratorErroneous = erroneousData.iterator();
			fwErroneous.write("[");
			while (iteratorErroneous.hasNext()) {
				JSONObject erroneousObject = (JSONObject) iteratorErroneous.next();
				fwErroneous.write(erroneousObject.toJSONString() + ",");
			}
			fwErroneous.write("]");
			fwErroneous.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException: " + e.toString());
		} catch (IOException e) {
			System.out.println("IOException: " + e.toString());
		} catch (ParseException e) {
			System.out.println("ParseException: " +e.toString());
		}
	}
	
	/**
	 * Format the date for the database tables
	 * @param dateTime
	 */
	private void dateFormatter(String dateTime) {
		
		Calendar cal = Calendar.getInstance();
		
		String output="";
		
		SimpleDateFormat inFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date = new Date();
		inFormat.setLenient(false);
		
		try {
			output = outFormat.format(inFormat.parse(dateTime));
			date = outFormat.parse(output);
			
			if(cal.getTime().after(date)){
				baseData.put("Date / Time", output);
				dateCheck = true;
			}
			else{
				dateCheck = false;
			}
			
		} catch (java.text.ParseException e) {
			dateCheck = false;
		}
		
	}
	
	/**
	 * Check for null int values
	 * @param intCheck
	 * @return
	 */
	private int checkInt(String intCheck){
		if(intCheck.contains("null")){
			return -1;
		}
		else{
			return Integer.parseInt(intCheck);
		}
	}
}
