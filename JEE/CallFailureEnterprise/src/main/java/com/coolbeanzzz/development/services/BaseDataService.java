package com.coolbeanzzz.development.services;

import java.util.Collection;

import javax.ejb.Local;

import com.coolbeanzzz.development.entities.BaseData;

@Local
public interface BaseDataService {
	public Collection<BaseData> getCatalog() ;
}