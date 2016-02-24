package com.coolbeanzzz.development.services;

import java.io.File;
import java.util.Collection;

import javax.ejb.Local;

import org.json.simple.JSONArray;

import com.coolbeanzzz.development.entities.BaseData;

@Local
public interface BaseDataService {
	public Collection<BaseData> getCatalog();
	public void populateBaseDataTable(File filename);
}