/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.services;

import java.io.File;
import java.util.Collection;

import com.coolbeanzzz.development.entities.FailureTable;

public interface FailureTableService {
	
	/**
	 * Gets all FailureTable records from the database
	 * @return a collection of FailureTable records
	 */
	public Collection<FailureTable> getCatalog();
	
	/**
	 * Populates the FailureTable with the input json file
	 * @param filename input json filename
	 */
	public void populateTable(File filename);
}
