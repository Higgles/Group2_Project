package com.coolbeanzzz.development.services;

import java.util.Collection;

import javax.ejb.Local;

import com.coolbeanzzz.development.entities.UETable;

@Local
public interface UETableService {
	public Collection<UETable> getCatalog() ;
}
