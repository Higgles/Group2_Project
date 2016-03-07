/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.dao;

import java.util.Collection;

public interface MccMncDAO extends FailureTableDAO{
	/**
	 * Retrieves all unique mnc values from underlying MncMcc table
	 * @return a collection of unique MNC integers from underlying table
	 */
	Collection<Integer> getAllUniqueMNCs();
	
	/**
	 * Retrieves all unique mcc values from underlying MncMcc table 
	 * @return a collection of unique MCC integers from underlying table
	 */
	Collection<Integer> getAllUniqueMCCs();
}
