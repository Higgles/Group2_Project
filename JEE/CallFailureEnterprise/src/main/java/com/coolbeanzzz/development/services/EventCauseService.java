package com.coolbeanzzz.development.services;

import java.util.Collection;

import javax.ejb.Local;

@Local
public interface EventCauseService extends Service{
	public Collection<Integer> getAllUniqueEventIds();
	public Collection<Integer> getAllUniqueCauseCodes();
}
