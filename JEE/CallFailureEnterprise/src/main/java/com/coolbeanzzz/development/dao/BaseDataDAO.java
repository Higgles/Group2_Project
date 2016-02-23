package com.coolbeanzzz.development.dao;

import java.util.Collection;

import org.json.simple.JSONArray;

import com.coolbeanzzz.development.entities.BaseData;

public interface BaseDataDAO {
	Collection<BaseData> getAllBaseData();
	void populateBaseDataTable(JSONArray baseData);
}
