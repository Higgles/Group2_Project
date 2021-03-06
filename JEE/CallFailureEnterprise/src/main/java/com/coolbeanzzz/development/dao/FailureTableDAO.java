/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.dao;

import java.util.Collection;

import org.json.simple.JSONArray;

import com.coolbeanzzz.development.entities.FailureTable;

public interface FailureTableDAO {
	/**
	 * Retrieves all rows from FailureTable
	 * @return Collection of FailureTable rows
	 */
	Collection<FailureTable> getAllTableRows();
	
	/**
	 * Populates Failure table with json data from file
	 * @param filename input json filename
	 */
	void populateTable(JSONArray objectArray);
	
	/**
	 * Adds Failure table record to database
	 * @param newEntry Failure table entry to be added to database
	 */
	void addNewEntry(FailureTable newEntry);
	
	/**
	 * Clears all table entries
	 * @param newEntry Failure table entry to be added to database
	 */
	void clearAllEntries();
}
