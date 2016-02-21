package com.coolbeanzzz.development.services;

import java.util.Collection;

import javax.ejb.Local;
import com.coolbeanzzz.development.entities.MccMnc;

@Local
public interface MccMncService {
	public Collection<MccMnc> getCatalog() ;
}
