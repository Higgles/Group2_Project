package com.coolbeanzzz.development.dao;

import java.util.Collection;

public interface MccMncDAO extends FailureTableDAO{
	Collection<Integer> getMNCs();
	Collection<Integer> getMCCs();
}
