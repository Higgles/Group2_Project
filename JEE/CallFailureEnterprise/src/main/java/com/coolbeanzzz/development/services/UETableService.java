/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.services;

import java.util.Collection;

import javax.ejb.Local;

@Local
public interface UETableService extends FailureTableService{
	
	/**
	 * Gets all ue types from the UETable within the database
	 * @return a collection of uetype values
	 */
	public Collection<Integer> getUETypes();
}
