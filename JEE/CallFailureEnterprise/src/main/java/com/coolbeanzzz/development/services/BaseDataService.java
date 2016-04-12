/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.services;

import java.util.Collection;

import javax.ejb.Local;

import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.tools.QueryOptions;


@Local
public interface BaseDataService extends FailureTableService{
	
	/**
	 * Gets all unique event id and cause code combinations for a given phone type from the database
	 * @param options 
	 * @param ueType input phone type
	 * @return a collection of FailureTable results
	 */
	public Collection<FailureTable> getUniqueEventIdsCauseCodeForPhoneType(String manufacturer, String model, QueryOptions options);
	
	/**
	 * Gets the no of call failures and total duration for each imsi value within a date period 
	 * @param options 
	 * @param manufacturer phone manufacturer
	 * @param model phone model
	 * @return a collection of FailureTable results
	 */
	public Collection<FailureTable> getNoOfCallFailuresAndDurationForImsiInDateRange(String date1, String date2, QueryOptions options);
	
	/**
	 * Display IMSIs with call failures during a given time period
	 * @param options 
	 * @param start date, end date 
	 * @return a collection of FailureTable results
	 */	
	public Collection<FailureTable> getImsiListBetween2Dates(String date1,String date2, QueryOptions options );
	
	/**
	 * Get the count for all failures for a phone model
	 * @param manufacturer phone manufacturer
	 * @param model phone model
	 * @param dateStart date to check from for time period
	 * @param dateEnd date to check to for time period
	 * @param options 
	 * @return a collection of FailureTable results
	 */
	public Collection<FailureTable> getFailCountByPhoneModel(String manufacturer, String model, String dateStart, String dateEnd, QueryOptions options);
	
	/**
	 * Gets all unique imsi values from database
	 * @param page 
	 * @param searchTerm 
	 * @param pageLimit 
	 * @return a collection of unique imsi values
	 */
	public Collection<String> getAllImsiValues(int page, String searchTerm, int pageLimit);

	/** 
	 * Gets event id's and cause code combinations for an input IMSI from the database 
	 * @param IMSI
	 * @param options
	 * @return a collection of FailureTable results
	 */
	public Collection<FailureTable> getEventIdsCauseCodeForIMSI(String IMSI, QueryOptions options);
	
	/**
	 * Count the number of failures, for a given IMSI, during a given time period.
	 * @param imsi to be checked
	 * @param dateStart start date for time period
	 * @param dateEnd end date for time period
	 * @param options 
	 * @return a collection of FailureTable rows from underlying table
	 */	
	public Collection<FailureTable> getFailCountByImsiAndDate(String IMSI, String dateStart, String dateEnd, QueryOptions options);
	
	/**
	 * Top 10 Market/Operator/Cell ID combinations that had call failures during a time period
	 * @param dateStart start date for time period
	 * @param dateEnd end date for time period
	 * @param options 
	 * @return a collection of FailureTable rows from underlying table
	 */
	public Collection<FailureTable> getTop10MarketOperatorCellBetween2Dates(String dateStart, String dateEnd, QueryOptions options);

	/**
	 * Display the IMSIs that were affected for a given failure Cause Class.
	 * @param options 
	 * @param Failure Class
	 * @return a collection of IMSI rows from underlying table
	 */
	public Collection<FailureTable> getIMSIsforFailureClass(String failureClass, QueryOptions options);

	/** 
	 * Gets unique cause code for an input IMSI from the database
	 * @param options 
	 * @param input IMSI
	 * @return a collection of FailureTable results
	 */
	Collection<FailureTable> getUniqueCauseCodeForIMSI(String IMSI, QueryOptions options); 
	
	/**
	 * Gets a list of the top 10 IMSIs with call failures during a given time period from the database
	 * @param options 
	 * @param start date, end date 
	 * @return a collection of FailureTable results
	 */	
	public Collection<FailureTable> getTop10ImsiListBetween2Dates(String date1,String date2, QueryOptions options );
}

