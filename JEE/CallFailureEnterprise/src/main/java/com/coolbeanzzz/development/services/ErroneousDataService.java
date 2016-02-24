package com.coolbeanzzz.development.services;

import java.io.File;
import java.util.Collection;

import javax.ejb.Local;

import org.json.simple.JSONArray;

import com.coolbeanzzz.development.entities.ErroneousData;

@Local
public interface ErroneousDataService {
	public Collection<ErroneousData> getCatalog() ;
	public void populateErroneousDataTable(File filename);
}
