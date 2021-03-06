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
import com.coolbeanzzz.development.tools.QueryOptions;

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
		when(mockedBaseDataDao.getUniqueEventIdsCauseCodeForPhoneType(isA(String.class), isA(String.class), isA(QueryOptions.class))).thenReturn(bds);
		when(mockedBaseDataDao.getEventIdsCauseCodeForIMSI(isA(String.class), isA(QueryOptions.class))).thenReturn(bds);
		when(mockedBaseDataDao.getAllImsiValues(anyInt(), isA(String.class), anyInt())).thenReturn(new ArrayList<String>());
		when(mockedBaseDataDao.getFailCountByPhoneModel(isA(String.class), isA(String.class), isA(String.class), isA(String.class),isA(QueryOptions.class))).thenReturn(bds);
		when(mockedBaseDataDao.getImsiListBetween2Dates(isA(String.class), isA(String.class), isA(QueryOptions.class))).thenReturn(bds);
		when(mockedBaseDataDao.getNoOfCallFailuresAndDurationForImsiInDateRange(isA(String.class), isA(String.class), isA(QueryOptions.class))).thenReturn(bds);
		when(mockedBaseDataDao.getUniqueEventIdsCauseCodeForPhoneType(isA(String.class), isA(String.class), isA(QueryOptions.class))).thenReturn(bds);
		when(mockedBaseDataDao.getFailCountByImsiAndDate(isA(String.class),isA(String.class), isA(String.class), isA(QueryOptions.class))).thenReturn(bds);
		when(mockedBaseDataDao.getTop10MarketOperatorCellBetween2Dates(isA(String.class), isA(String.class), isA(QueryOptions.class))).thenReturn(bds);
		when(mockedBaseDataDao.getIMSIsforFailureClass(isA(String.class), isA(QueryOptions.class))).thenReturn(bds);
		when(mockedBaseDataDao.getUniqueCauseCodeForIMSI(isA(String.class), isA(QueryOptions.class))).thenReturn(bds);
		when(mockedBaseDataDao.getTop10ImsiListBetween2Dates(isA(String.class), isA(String.class), isA(QueryOptions.class))).thenReturn(bds);
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
		QueryOptions options=new QueryOptions(0, 0, 0, false, "", 0, "asc");
		Collection<FailureTable> baseData = baseDataService.getUniqueEventIdsCauseCodeForPhoneType("", "", options);
		assertEquals(baseData1, baseData.iterator().next());
	}
	
	@Test
	public void getEventIdsCauseCodeForIMSItest() {
		QueryOptions options=new QueryOptions(0, 0, 0, false, "", 0, "asc");
		Collection<FailureTable> baseData = baseDataService.getEventIdsCauseCodeForIMSI("1", options);
		assertEquals(baseData1, baseData.iterator().next());
	}
	
	@Test
	public void getAllImsiValuestest() {
		Collection<String> allImsiValues = baseDataService.getAllImsiValues(1,"", -1);
		assertEquals(0,allImsiValues.size());
	}
	
	@Test
	public void getFailCountByPhoneModeltest() {
		QueryOptions options=new QueryOptions(0, 0, 0, false, "", 0, "asc");
		Collection<FailureTable> baseData = baseDataService.getFailCountByPhoneModel("", "", "", "", options);
		assertEquals(baseData1, baseData.iterator().next());
	}
	
	@Test
	public void getImsiListBetween2Dates() {
		QueryOptions options=new QueryOptions(0, 0, 0, false, "", 0, "asc");
		Collection<FailureTable> baseData = baseDataService.getImsiListBetween2Dates("", "", options);
		assertEquals(baseData1, baseData.iterator().next());
	}
	
	@Test
	public void getNoOfCallFailuresAndDurationForImsiInDateRangetest() {
		QueryOptions options=new QueryOptions(0, 0, 0, false, "", 0, "asc");
		Collection<FailureTable> baseData = baseDataService.getNoOfCallFailuresAndDurationForImsiInDateRange("", "", options);
		assertEquals(baseData1, baseData.iterator().next());
	}
	
	@Test
	public void getFailCountByImsiAndDate(){
		QueryOptions options=new QueryOptions(0, 0, 0, false, "", 0, "asc");
		Collection<FailureTable> baseData = baseDataService.getFailCountByImsiAndDate("", "", "", options);
		assertEquals(baseData1, baseData.iterator().next());
	}
	
	@Test
	public void getTop10MarketOperatorCellBetween2Dates(){
		QueryOptions options=new QueryOptions(0, 0, 0, false, "", 0, "asc");
		Collection<FailureTable> baseData = baseDataService.getTop10MarketOperatorCellBetween2Dates("", "", options);
		assertEquals(baseData1, baseData.iterator().next());
	}
	
	@Test
	public void getIMSIsforFailureClass(){
		QueryOptions options=new QueryOptions(0, 0, 0, false, "", 0, "asc");
		Collection<FailureTable> baseData = baseDataService.getIMSIsforFailureClass("", options);
		assertEquals(baseData1, baseData.iterator().next());
	}
	
	@Test
	public void getUniqueCauseCodeForIMSI() {
		QueryOptions options=new QueryOptions(0, 0, 0, false, "", 0, "asc");
		Collection<FailureTable> baseData = baseDataService.getUniqueCauseCodeForIMSI("1", options);
		assertEquals(baseData1, baseData.iterator().next());
	}
	
	@Test
	public void getTop10ImsiListBetween2Dates() {
		QueryOptions options=new QueryOptions(0, 0, 0, false, "", 0, "asc");
		Collection<FailureTable> baseData = baseDataService.getTop10ImsiListBetween2Dates("", "", options);
		assertEquals(baseData1, baseData.iterator().next());
	}
}
