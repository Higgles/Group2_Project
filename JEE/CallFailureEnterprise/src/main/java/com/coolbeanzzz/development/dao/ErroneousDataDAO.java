/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.dao;

import java.util.Collection;

import com.coolbeanzzz.development.tools.QueryOptions;
import com.coolbeanzzz.development.entities.FailureTable;

public interface ErroneousDataDAO extends FailureTableDAO{

	/**
	 * Gets all erroneous data paginated
	 * @param options pagination options
	 * @return List of erroneous records
	 */
	Collection<FailureTable> getAllErroneousRecords(QueryOptions options);
	
}