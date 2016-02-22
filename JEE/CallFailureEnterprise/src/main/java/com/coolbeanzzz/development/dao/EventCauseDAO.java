package com.coolbeanzzz.development.dao;

import java.io.File;
import java.util.Collection;

import com.coolbeanzzz.development.entities.EventCause;

public interface EventCauseDAO {
	Collection<EventCause> getAllEventCauses();
	Collection<Integer> getAllUniqueEventCauses();
	void populateEventCauseTable(File jsonFile);
}
