package com.coolbeanzzz.development.dao;

import java.util.Collection;

public interface EventCauseDAO extends FailureTableDAO{
	Collection<Integer> getAllUniqueEventIds();
	Collection<Integer> getAllUniqueCauseCodes();
}
