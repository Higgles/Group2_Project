package com.coolbeanzzz.development.dao;

import java.io.File;
import java.util.Collection;

import org.json.simple.JSONArray;

import com.coolbeanzzz.development.entities.ErroneousData;

public interface ErroneousDataDAO {
	Collection<ErroneousData> getAllErroneousData();
	void populateErroneousDataTable(JSONArray erroneous);
}
