package com.coolbeanzzz.development.services;

import java.util.Collection;

import javax.ejb.Local;

import com.coolbeanzzz.development.entities.ErroneousData;

@Local
public interface ErroneousDataService {
	public Collection<ErroneousData> getCatalog() ;
}