package com.coolbeanzzz.development.dao;

import java.io.File;
import java.util.Collection;

import com.coolbeanzzz.development.entities.FailureTable;

public interface FailureTableDAO {
	Collection<FailureTable> getAllTableRows();
	void populateTable(File filename);
}
