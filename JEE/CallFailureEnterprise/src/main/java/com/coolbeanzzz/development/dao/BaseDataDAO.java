/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.dao;

import java.util.Collection;

import com.coolbeanzzz.development.entities.FailureTable;

public interface BaseDataDAO extends FailureTableDAO{
	/**
	 * Queries the underlying table for unique event id and cause code combinations for an input phone type
	 * @param ueType input phone type code
	 * @return a collection of FailureTable rows from underlying table
	 */
	Collection<FailureTable> getUniqueEventIdsCauseCodeForPhoneType(int ueType);
	
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
	 * @param ueType phone model to be checked
	 * @param dateStart start date for time period
	 * @param dateEnd end date for time period
	 * @return a collection of FailureTable rows from underlying table
	 */	
	Collection<FailureTable> getFailCountByPhoneModel(int ueType, String dateStart, String dateEnd);
	
	/**
	 * Queries the database for all unique imsi values
	 * @return a collection of imsi values
	 */	
	Collection<String> getAllImsiValues();
}
