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
}