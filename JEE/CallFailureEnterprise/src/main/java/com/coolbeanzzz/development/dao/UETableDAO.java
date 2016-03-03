package com.coolbeanzzz.development.dao;

import java.util.Collection;

public interface UETableDAO extends FailureTableDAO{
	Collection<Integer> getUETypes();
}