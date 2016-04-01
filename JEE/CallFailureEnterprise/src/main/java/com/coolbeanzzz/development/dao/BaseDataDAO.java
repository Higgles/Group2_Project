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
	 * Display IMSIs with call failures during a given time period
	 * @param start date, end date 
	 * @return a collection of FailureTable rows from underlying table
	 */	
	Collection<FailureTable> getImsiListBetween2Dates(String date1,String date2 );
	
	/**
	 * Count call failures, for a given model of phone, during a given time period.
	 * @param manufacturer phone manufacturer to be checked
	 * @param model phone model to be checked
	 * @param dateStart start date for time period
	 * @param dateEnd end date for time period
	 * @return a collection of FailureTable rows from underlying table
	 */	
	Collection<FailureTable> getFailCountByPhoneModel(String manufacturer, String model, String dateStart, String dateEnd);
	
	/**
	 * Count the number of failures, for a given IMSI, during a given time period.
	 * @param imsi to be checked
	 * @param dateStart start date for time period
	 * @param dateEnd end date for time period
	 * @return a collection of FailureTable rows from underlying table
	 */	
	Collection<FailureTable> getFailCountByImsiAndDate(String IMSI, String dateStart, String dateEnd);
	
	/**
	 * Top 10 Market/Operator/Cell ID combinations that had call failures during a time period
	 * @param dateStart start date for time period
	 * @param dateEnd end date for time period
	 * @return a collection of FailureTable rows from underlying table
	 */
	Collection<FailureTable> getTop10MarketOperatorCellBetween2Dates(String dateStart, String dateEnd);
	
	/**
	 * Display the IMSIs that were affected for a given failure Cause Class.
	 * @param Failure Class
	 * @return a collection of IMSI rows from underlying table
	 */
	Collection<FailureTable> getIMSIsforFailureClass(String failureClass);
		
	/**
	 * Queries the database for all unique imsi values
	 * @return a collection of imsi values
	 */	
	Collection<String> getAllImsiValues();

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
	
	/**
	 * Queries the database for all unique failure values
	 * @return a collection of failure values
	 */	
	Collection<String> getAllFailureValues();
	
}
