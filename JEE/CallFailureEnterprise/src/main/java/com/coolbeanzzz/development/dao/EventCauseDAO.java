package com.coolbeanzzz.development.dao;

import java.io.File;
import java.util.Collection;

import com.coolbeanzzz.development.entities.EventCause;

public interface EventCauseDAO {
	Collection<EventCause> getAllEventCauses();
	Collection<Integer> getAllUniqueEventIds();
	Collection<Integer> getAllUniqueCauseCodes();
	void populateEventCauseTable(File jsonFile);
}
