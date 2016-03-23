/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.dao;

import java.math.BigInteger;
import java.util.Collection;

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
	
	//************************* MIKE G ****************************** Tues 22/3/16
	/**
	 * As a Customer Service Rep, I want to count, for a given IMSI, 
     * the number of failures they have had during a given time period.
	 * @param imsi to be checked
	 * @param dateStart start date for time period
	 * @param dateEnd end date for time period
	 * @return a collection of FailureTable rows from underlying table
	 */	
	Collection<FailureTable> getFailCountByImsiAndDate(String IMSI, String dateStart, String dateEnd);
	
	//************************* MIKE G ****************************** Weds 23/3/16
	/**
	 * Top 10 Market/Operator/Cell ID combinations that had call failures during a time period
	 * @param dateStart start date for time period
	 * @param dateEnd end date for time period
	 * @return a collection of FailureTable rows from underlying table
	 */
	Collection<FailureTable> getTop10MarketOperatorCellBetween2Dates(String dateStart, String dateEnd);
		
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
}
