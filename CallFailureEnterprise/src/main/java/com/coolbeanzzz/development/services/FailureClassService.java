package com.coolbeanzzz.development.services;
import java.io.File;
import java.util.Collection;

import javax.ejb.Local;

import com.coolbeanzzz.development.entities.FailureClass;

@Local
public interface FailureClassService {

	public Collection<FailureClass> getCatalog() ;
	public void populateTable(File jsonFile);
	
}