package com.coolbeanzzz.development.dao;

import java.io.File;
import java.util.Collection;

import com.coolbeanzzz.development.entities.MccMnc;

public interface MccMncDAO {
	Collection<MccMnc> getAllMccMncs();
	void populateMccMncTable(File jsonFile);
}
