package com.coolbeanzzz.development.dao;

import java.io.File;
import java.util.Collection;

import com.coolbeanzzz.development.entities.FailureClass;


public interface FailureClassDAO {
	Collection<FailureClass> getAllFailureClasses();
	void populateFailureClassTable(File jsonFile);
}
