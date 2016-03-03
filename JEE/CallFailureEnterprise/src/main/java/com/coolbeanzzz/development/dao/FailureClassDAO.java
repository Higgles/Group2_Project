package com.coolbeanzzz.development.dao;

import java.util.Collection;


public interface FailureClassDAO extends FailureTableDAO{
	Collection<Integer> getFailureClasseCodes();
}
