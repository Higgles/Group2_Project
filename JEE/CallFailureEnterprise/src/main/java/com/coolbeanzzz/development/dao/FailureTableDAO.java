/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.dao;

import java.io.File;
import java.util.Collection;

import com.coolbeanzzz.development.entities.FailureTable;

public interface FailureTableDAO {
	/**
	 * Retrieves all rows from FailureTable
	 * @return Collection of FailureTable rows
	 */
	Collection<FailureTable> getAllTableRows();
	
	/**
	 * Populates Failure table with json data from ile
	 * @param filename input json filename
	 */
	void populateTable(File filename);
}
