/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.dao;

import org.json.simple.JSONArray;

public interface ErroneousDataDAO extends FailureTableDAO{

	void populateTableObject(JSONArray erroneousData);
	
}