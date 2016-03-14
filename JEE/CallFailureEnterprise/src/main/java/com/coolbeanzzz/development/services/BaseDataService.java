/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.services;

import java.util.Collection;

import javax.ejb.Local;

import com.coolbeanzzz.development.entities.FailureTable;


@Local
public interface BaseDataService extends FailureTableService{
	
	/**
	 * Gets all unique event id and cause code combinations for a given phone type from the database
	 * @param ueType input phone type
	 * @return a collection of FailureTable results
	 */
	public Collection<FailureTable> getUniqueEventIdsCauseCodeForPhoneType(int ueType);
	
	/**
	 * Gets the no of call failures and total duration for each imsi value within a date period 
	 * @param ueType input phone type
	 * @return a collection of FailureTable results
	 */
	public Collection<FailureTable> getNoOfCallFailuresAndDurationForImsiInDateRange(String date1, String date2);
	
	/**
	 * As a Support Engineer I want to see a list of all IMSIs with call failures during a given time period
	 * @param start date, end date 
	 * @return a collection of FailureTable results
	 */	
	public Collection<FailureTable> getImsiListBetween2Dates(String date1,String date2 );
	
	/**
	 * Get the count for all failures for a phone model
	 * @param ueType phone type
	 * @param dateStart date to check from for time period
	 * @param dateEnd date to check to for time period
	 * @return a collection of FailureTable results
	 */
	public Collection<FailureTable> getFailCountByPhoneModel(int ueType, String dateStart, String dateEnd);
	
	/**
	 * Gets all unique imsi values from database
	 * @return a collection of unique imsi values
	 */
	public Collection<String> getAllImsiValues();
}
