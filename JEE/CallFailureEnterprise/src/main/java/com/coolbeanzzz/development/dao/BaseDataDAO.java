/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.dao;

import java.math.BigInteger;
import java.util.Collection;

import com.coolbeanzzz.development.entities.FailureTable;

public interface BaseDataDAO extends FailureTableDAO{
	/**
	 * Queries the underlying table for unique event id and cause code combinations for an input phone type
	 * @param ueType input phone type code
	 * @return a collection of FailureTable rows from underlying table
	 */
	Collection<FailureTable> getUniqueEventIdsCauseCodeForPhoneType(int ueType);
	
	/**
	 * Queries the underlying table for event id's and cause code combinations for an input IMSI
	 * @param input IMSI
	 * @return a collection of FailureTable rows from underlying table
	 */
	Collection<FailureTable> getEventIdsCauseCodeForIMSI(String IMSI); 
}
