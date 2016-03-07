/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.dao;

import java.util.Collection;


public interface FailureClassDAO extends FailureTableDAO{
	/**
	 * Retrieves all failure class codes from Failure class table
	 * @return a collection of failure class integers from Failure class table
	 */
	Collection<Integer> getFailureClassCodes();
}
