package com.coolbeanzzz.development.services;
import java.util.Collection;

import javax.ejb.Local;

@Local
public interface FailureClassService extends Service{
	public Collection<Integer> getFailureClasseCodes();
}