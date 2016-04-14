/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.services;

import java.util.Collection;

import javax.ejb.Local;

import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.tools.QueryOptions;

@Local
public interface ErroneousDataService extends FailureTableService{

	/**
	 * Gets all erroneous data paginated
	 * @param options pagination options
	 * @return List of erroneous records
	 */
	Collection<FailureTable> getAllResults(QueryOptions options);
	
}
