package com.coolbeanzzz.development.services;

import java.io.File;
import java.util.Collection;

import com.coolbeanzzz.development.entities.FailureTable;

public interface Service {
	public Collection<FailureTable> getCatalog();
	public void populateTable(File filename);
}
