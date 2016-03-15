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
	 * Retrieves all ue type values from the UETable
	 * @return a collection of ue type integers from the underlying UETable
	 */
	Collection<Integer> getUETypes(String manufacturer, String model);
	
	/**
	 * Gets all manufacturers from database
	 * @return a collection of manufacturers from database
	 */	
	Collection<String> getAllManufacturers();
	
	/**
	 * Gets all models for given manufacturer
	 * @return a collection of models relating to the input manufacturer from database
	 */	
	Collection<String> getModelsForManufacturer(String manufacturer);
}