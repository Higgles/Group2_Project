/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.services;

import java.math.BigInteger;
import java.util.Collection;

import javax.ejb.Local;

import com.coolbeanzzz.development.entities.FailureTable;


@Local
public interface BaseDataService extends FailureTableService{
	
	/**
	 * Gets all unique event id and cause code combinations for a given phone type from the database
	 * @param ueType input phone type
	 * @return a collection of FailureTable results
	 */
	public Collection<FailureTable> getUniqueEventIdsCauseCodeForPhoneType(int ueType);
	
	/**
	 * Gets event id's and cause code combinations for an input IMSI from the database
	 * @param input IMSI
	 * @return a collection of FailureTable results
	 */
	public Collection<FailureTable> getEventIdsCauseCodeForIMSI(String IMSI); 

}