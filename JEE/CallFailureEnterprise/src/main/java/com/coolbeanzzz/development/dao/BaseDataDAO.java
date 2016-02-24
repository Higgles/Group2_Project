package com.coolbeanzzz.development.dao;

import java.io.File;
import java.util.Collection;

import org.json.simple.JSONArray;

import com.coolbeanzzz.development.entities.BaseData;

public interface BaseDataDAO {
	Collection<BaseData> getAllBaseData();
	void populateBaseDataTable(File filename);
}
