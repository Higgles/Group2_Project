/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.services;

import java.io.File;
import java.util.Collection;

import org.json.simple.JSONArray;

import com.coolbeanzzz.development.entities.FailureTable;

public interface FailureTableService {
	
	/**
	 * Gets all FailureTable records from the database
	 * @return a collection of FailureTable records
	 */
	public Collection<FailureTable> getCatalog();
	
	/**
	 * Populates the FailureTable with the input json file
	 * @param jsonArray input json filename
	 */
	public void populateTable(JSONArray jsonArray);
	
	/**
	 * Adds Failure table record to database
	 * @param newEntry Failure table entry to be added to database
	 */
	public void addNewEntry(FailureTable newEntry);
	
	/**
	 * Deletes all table Entries
	 */
	public void clearAllEntries();
}
