package com.coolbeanzzz.development.services;

import java.io.File;
import java.util.Collection;

import javax.ejb.Local;

import com.coolbeanzzz.development.entities.MccMnc;

@Local
public interface MccMncService {
	public Collection<MccMnc> getCatalog() ;
	public void populateTable(File jsonFile);
	public Collection<Integer> getMNCs();
	public Collection<Integer> getMCCs();
}
