/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.dao;

import java.util.Collection;

public interface UETableDAO extends FailureTableDAO{
	/**
	 * Retrieves all ue type values from the UETable
	 * @return a collection of ue type integers from the underlying UETable
	 */
	Collection<Integer> getUETypes();
	
	/**
	 * Gets all manufacturers from database
	 * @param pageLimit 
	 * @param searchTerm 
	 * @param page 
	 * @return a collection of manufacturers from database
	 */	
	Collection<String> getAllManufacturers(int page, String searchTerm, int pageLimit);
	
	/**
	 * Gets all models for given manufacturer
	 * @param pageLimit 
	 * @param searchTerm 
	 * @param page 
	 * @return a collection of models relating to the input manufacturer from database
	 */	
	Collection<String> getModelsForManufacturer(String manufacturer, int page, String searchTerm, int pageLimit);
}