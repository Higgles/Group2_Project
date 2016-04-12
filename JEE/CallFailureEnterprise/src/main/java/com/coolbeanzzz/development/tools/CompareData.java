package com.coolbeanzzz.development.tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
	 * Compare imported base data JSON array against the reference tables
	 * to split it into erroneous data and valid data JSON arrays.
	 * Return a list of JSON arrays for importing to the database.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<JSONArray> compareData(JSONArray baseDataRows){
		
		int eventId;
		int causeCode;
		int failureCode;
		int mcc;
		int mnc;
		int ueType;
		
		String dateTime;
		ArrayList<JSONArray> baseDataList = new ArrayList<JSONArray>();

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

			if(!uniqueEventIds.contains(eventId)){
				erroneousData.add(baseData);
			}
			else if(!uniqueCauseCodes.contains(causeCode)){
				erroneousData.add(baseData);
			}
			else if(!mccs.contains(mcc)){
				erroneousData.add(baseData);
			}
			else if(!mncs.contains(mnc)){
				erroneousData.add(baseData);
			}
			else if(!ueTypes.contains(ueType)){
				erroneousData.add(baseData);
			}
			else if(!uniqueFailureCodes.contains(failureCode)){
				erroneousData.add(baseData);
			}
			else if(dateCheck == false){
				erroneousData.add(baseData);
			}
			else{
				validData.add(baseData);
			}
		}

		baseDataList.add(validData);
		baseDataList.add(erroneousData);
		
		return baseDataList;
	}
	
	/**
	 * Format the date for the database tables
	 * @param dateTime
	 */
	@SuppressWarnings("unchecked")
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
		try{
			return Integer.parseInt(intCheck);
		}
		catch(NumberFormatException e){
			return -1;
		}
	}
}
