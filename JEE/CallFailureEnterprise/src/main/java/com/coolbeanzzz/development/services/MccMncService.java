package com.coolbeanzzz.development.services;

import java.util.Collection;

import javax.ejb.Local;

@Local
public interface MccMncService extends Service{
	public Collection<Integer> getMNCs();
	public Collection<Integer> getMCCs();
}
