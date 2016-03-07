/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.dao;

import java.util.Collection;

public interface EventCauseDAO extends FailureTableDAO{
	/**
	 * Retrieves all unique event ids from EventCause table
	 * @return a collection of event id integers from underlying table
	 */
	Collection<Integer> getAllUniqueEventIds();
	
	/**
	 * Retrieves all unique cause codes from EventCause table
	 * @return a collection of cause code integers from underlying table
	 */
	Collection<Integer> getAllUniqueCauseCodes();
}
