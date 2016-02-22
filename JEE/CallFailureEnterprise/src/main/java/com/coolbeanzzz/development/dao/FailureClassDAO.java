package com.coolbeanzzz.development.dao;

import java.io.File;
import java.util.Collection;

import com.coolbeanzzz.development.entities.FailureClass;


public interface FailureClassDAO {
	Collection<FailureClass> getAllFailureClasses();
	Collection<Integer> getFailureClasseCodes();
	void populateFailureClassTable(File jsonFile);
}
