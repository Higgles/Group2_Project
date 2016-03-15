package com.coolbeanzzz.development.servicetests;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.coolbeanzzz.development.dao.BaseDataDAO;
import com.coolbeanzzz.development.dao.jpa.JPABaseDataDAO;
import com.coolbeanzzz.development.entities.BaseData;
import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.services.BaseDataServiceEJB;

public class BaseDataServiceTest {
	private BaseDataServiceEJB baseDataService;
	private BaseData baseData1;
	private BaseData baseData2;
	
	@Before
	public void setUp() throws Exception {
		BaseDataDAO mockedBaseDataDao = mock(JPABaseDataDAO.class);
		baseData1 = new BaseData(0, "", "", "", "", "", "", "", "", null, null, null, null);
		baseData2 = new BaseData(1, "", "", "", "", "", "", "", "", null, null, null, null);
		Collection<FailureTable> bds = new ArrayList<>();
		bds.add(baseData1);
		bds.add(baseData2);
		when(mockedBaseDataDao.getAllTableRows()).thenReturn(bds);
		when(mockedBaseDataDao.getUniqueEventIdsCauseCodeForPhoneType(anyInt())).thenReturn(bds);
		when(mockedBaseDataDao.getEventIdsCauseCodeForIMSI(isA(String.class))).thenReturn(bds);
		baseDataService=new BaseDataServiceEJB();
		baseDataService.setDao(mockedBaseDataDao);
	}

	@Test
	public void getCatalogtest() {
		Collection<FailureTable> baseData = baseDataService.getCatalog();
		assertEquals(baseData1, baseData.iterator().next());
	}
	
	@Test
	public void getUniqueEventIdsCauseCodeForPhoneTypetest() {
		Collection<FailureTable> baseData = baseDataService.getUniqueEventIdsCauseCodeForPhoneType(1);
		assertEquals(baseData1, baseData.iterator().next());
	}
	
	@Test
	public void getEventIdsCauseCodeForIMSItest() {
		Collection<FailureTable> baseData = baseDataService.getEventIdsCauseCodeForIMSI("1");
		assertEquals(baseData1, baseData.iterator().next());
	}
}
