package com.coolbeanzzz.development.services;

import java.io.File;
import java.util.Collection;

import javax.ejb.Local;

import com.coolbeanzzz.development.entities.EventCause;

@Local
public interface EventCauseService {
	public Collection<EventCause> getCatalog() ;
	public void populateTable(File jsonFile);
	public Collection<Integer> getAllUniqueEventIds();
	public Collection<Integer> getAllUniqueCauseCodes();
}
