/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.services;
import java.util.Collection;

import javax.ejb.Local;

@Local
public interface FailureClassService extends FailureTableService{
	
	/**
	 * Gets all failure class values from the FailureClass table in the database
	 * @return a collection of failure class codes
	 */
	public Collection<Integer> getFailureClassCodes();
	
	/**
	 * Gets all failure class descriptions from the FailureClass table in the database
	 * @param pageLimit 
	 * @param searchTerm 
	 * @param page 
	 * @return a collection of failure class descriptions
	 */
	public Collection<String> getAllDescriptions(int page, String searchTerm, int pageLimit);
}