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
	
	/**
	 * Gets all unique manufacturer values from database
	 * @param pageLimit 
	 * @param searchTerm 
	 * @param page 
	 * @return a collection of unique manufacturer values
	 */
	public Collection<String> getAllManufacturers(int page, String searchTerm, int pageLimit);
	
	/**
	 * Gets all models relating to manufacturer from database
	 * @param pageLimit 
	 * @param searchTerm 
	 * @param page 
	 * @return a collection of model values
	 */
	public Collection<String> getModelsForManufacturer(String manufacturer, int page, String searchTerm, int pageLimit);
}
