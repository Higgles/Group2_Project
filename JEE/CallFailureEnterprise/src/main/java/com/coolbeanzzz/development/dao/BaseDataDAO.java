/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.dao;

import java.util.Collection;

import com.coolbeanzzz.development.entities.FailureTable;

public interface BaseDataDAO extends FailureTableDAO{
	/**
	 * Queries the underlying table for unique event id and cause code combinations for an input phone type
	 * @param ueType input phone type code
	 * @return a collection of FailureTable rows from underlying table
	 */
	Collection<FailureTable> getUniqueEventIdsCauseCodeForPhoneType(int ueType);

	Collection<FailureTable> getFailCountByImsi(int ueType, String dateStart, String dateEnd);
}
