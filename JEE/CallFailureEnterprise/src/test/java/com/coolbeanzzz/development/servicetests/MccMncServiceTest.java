package com.coolbeanzzz.development.servicetests;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.coolbeanzzz.development.dao.MccMncDAO;
import com.coolbeanzzz.development.dao.jpa.JPAMccMncDAO;
import com.coolbeanzzz.development.entities.MccMnc;
import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.services.MccMncServiceEJB;

public class MccMncServiceTest {
	private MccMncServiceEJB mccService;
	private MccMnc mccmnc1;
	private MccMnc mccmnc2;
	
	@Before
	public void setUp() throws Exception {
		MccMncDAO mockedMccMncDao = mock(JPAMccMncDAO.class);
		mccmnc1 = new MccMnc(0,0,"","");
		mccmnc2 = new MccMnc(1,1,"","");
		Collection<FailureTable> mccmncs = new ArrayList<>();
		mccmncs.add(mccmnc1);
		mccmncs.add(mccmnc2);
		when(mockedMccMncDao.getAllTableRows()).thenReturn(mccmncs);
		
		Collection<Integer> uniqueMccs = new ArrayList<>();
		uniqueMccs.add(mccmnc1.getMcc());
		uniqueMccs.add(mccmnc2.getMcc());
		when(mockedMccMncDao.getMCCs()).thenReturn(uniqueMccs);
		
		Collection<Integer> uniqueMncs = new ArrayList<>();
		uniqueMncs.add(mccmnc1.getMnc());
		uniqueMncs.add(mccmnc2.getMnc());
		when(mockedMccMncDao.getMNCs()).thenReturn(uniqueMncs);
		
		mccService=new MccMncServiceEJB();
		mccService.setDao(mockedMccMncDao);
	}

	@Test
	public void getCatalogtest() {
		Collection<FailureTable> mccmncs = mccService.getCatalog();
		assertEquals(mccmnc1, mccmncs.iterator().next());
	}
	
	@Test
	public void getAllUniqueCauseCodes(){
		Collection<Integer> uniqueMccs = mccService.getMCCs();
		Iterator<Integer> uniqueValues = uniqueMccs.iterator();
		
		assertEquals(mccmnc1.getMcc(), uniqueValues.next().intValue());
		assertEquals(mccmnc2.getMcc(), uniqueValues.next().intValue());
	}
	
	@Test
	public void getAllUniqueEventIds(){
		Collection<Integer> uniqueMncs = mccService.getMNCs();
		Iterator<Integer> uniqueValues = uniqueMncs.iterator();
		
		assertEquals(mccmnc1.getMnc(), uniqueValues.next().intValue());
		assertEquals(mccmnc2.getMnc(), uniqueValues.next().intValue());
	}
}
