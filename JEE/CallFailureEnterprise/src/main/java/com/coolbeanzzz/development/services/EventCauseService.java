/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.services;

import java.util.Collection;

import javax.ejb.Local;

@Local
public interface EventCauseService extends FailureTableService{
	
	/**
	 * Gets all unique event ids from the EventCause table in the database
	 * @return a collection of unique EventIds 
	 */
	public Collection<Integer> getAllUniqueEventIds();
	
	/**
	 *Gets all unique cause code from the EventCause table in the database
	 * @return a collection of unique cause code value 
	 */
	public Collection<Integer> getAllUniqueCauseCodes();
}
