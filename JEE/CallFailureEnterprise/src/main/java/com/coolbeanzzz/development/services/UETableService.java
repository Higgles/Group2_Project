package com.coolbeanzzz.development.services;

import java.util.Collection;

import javax.ejb.Local;

@Local
public interface UETableService extends Service{
	public Collection<Integer> getUETypes();
}
