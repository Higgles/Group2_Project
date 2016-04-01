/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.dao;

import java.math.BigInteger;
import java.util.Collection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.coolbeanzzz.development.entities.FailureTable;

public interface BaseDataDAO extends FailureTableDAO{
	/**
	 * Queries the underlying table for unique event id and cause code combinations for an input phone type
	 * @param manufacturer phone manufacturer to be checked
	 * @param model phone model to be checked
	 * @return a collection of FailureTable rows from underlying table
	 */
	Collection<FailureTable> getUniqueEventIdsCauseCodeForPhoneType(String manufacturer, String model);
	
	/**
	 * Queries the underlying table for no of call failures and total duration for each
	 * imsi value within a date period 
	 * @param ueType input phone type code
	 * @return a collection of FailureTable rows from underlying table
	 */
	Collection<FailureTable> getNoOfCallFailuresAndDurationForImsiInDateRange(String fromDate, String toDate);

	/**
	 * As a Support Engineer I want to see a list of all IMSIs with call failures during a given time period
	 * @param start date, end date 
	 * @return a collection of FailureTable rows from underlying table
	 */	
	Collection<FailureTable> getImsiListBetween2Dates(String date1,String date2 );
	
	/**
	 * As a Support Engineer I want to count, for a given model of phone, the number of call failures it 
	 * has had during a given time period.
	 * @param manufacturer phone manufacturer to be checked
	 * @param model phone model to be checked
	 * @param dateStart start date for time period
	 * @param dateEnd end date for time period
	 * @return a collection of FailureTable rows from underlying table
	 */	
	Collection<FailureTable> getFailCountByPhoneModel(String manufacturer, String model, String dateStart, String dateEnd);
	
	/**
	 * Queries the database for all unique imsi values
	 * @param page
	 * @param searchTerm 
	 * @param pageLimit 
	 * @return a collection of imsi values
	 */	
	Collection<String> getAllImsiValues(int page, String searchTerm, int pageLimit);

	 /** 
	 * Queries the underlying table for event id's and cause code combinations for an input IMSI
	 * @param input IMSI
	 * @return a collection of FailureTable rows from underlying table
	 */
	Collection<FailureTable> getEventIdsCauseCodeForIMSI(String IMSI); 
	
	/** 
	 * Queries the underlying table for unique cause code for an input IMSI
	 * @param input IMSI
	 * @return a collection of FailureTable rows from underlying table
	 */
	Collection<FailureTable> getUniqueCauseCodeForIMSI(String IMSI); 
	
	/**
	 * Queries the underlying table for a list of the top 10 IMSIs with call failures during a given time period
	 * @param start date, end date 
	 * @return a collection of FailureTable rows from underlying table
	 */	
	Collection<FailureTable> getTop10ImsiListBetween2Dates(String date1,String date2 );
	
}
