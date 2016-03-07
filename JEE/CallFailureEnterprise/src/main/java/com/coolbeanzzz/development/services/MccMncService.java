/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.services;

import java.util.Collection;

import javax.ejb.Local;

@Local
public interface MccMncService extends FailureTableService{
	
	/**
	 * Gets all unique MNC values from the MccMnc table in the database
	 * @return unique MNCs
	 */
	public Collection<Integer> getAllUniqueMNCs();
	
	/**
	 * Gets all unique MCC values from the MccMnc table in the database
	 * @return unique MCCs
	 */
	public Collection<Integer> getAllUniqueMCCs();
}
