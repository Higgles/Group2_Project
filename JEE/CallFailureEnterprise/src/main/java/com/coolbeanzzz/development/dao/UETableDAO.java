package com.coolbeanzzz.development.dao;

import java.io.File;
import java.util.Collection;

import com.coolbeanzzz.development.entities.UETable;

public interface UETableDAO {
	Collection<UETable> getAllUETables();
	void populateUETable(File jsonFile);
}