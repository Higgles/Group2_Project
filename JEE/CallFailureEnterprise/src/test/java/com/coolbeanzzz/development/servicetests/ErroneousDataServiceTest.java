package com.coolbeanzzz.development.servicetests;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.coolbeanzzz.development.dao.ErroneousDataDAO;
import com.coolbeanzzz.development.dao.jpa.JPAErroneousDataDAO;
import com.coolbeanzzz.development.entities.ErroneousData;
import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.services.ErroneousDataServiceEJB;

public class ErroneousDataServiceTest {
	private ErroneousDataServiceEJB baseDataService;
	private ErroneousData erroneousData1;
	private ErroneousData erroneousData2;
	
	@Before
	public void setUp() throws Exception {
		ErroneousDataDAO mockedErroneousDataDao = mock(JPAErroneousDataDAO.class);
		erroneousData1 = new ErroneousData(0, "", 0, "", 0, 0, 0, "", "", "", "", "", "", "", "");
		erroneousData2 = new ErroneousData(1, "", 0, "", 0, 0, 0, "", "", "", "", "", "", "", "");
		Collection<FailureTable> eds = new ArrayList<>();
		eds.add(erroneousData1);
		eds.add(erroneousData2);
		when(mockedErroneousDataDao.getAllTableRows()).thenReturn(eds);
						
		baseDataService=new ErroneousDataServiceEJB();
		baseDataService.setDao(mockedErroneousDataDao);
	}

	@Test
	public void getCatalogtest() {
		Collection<FailureTable> erroneousData = baseDataService.getCatalog();
		assertEquals(erroneousData1, erroneousData.iterator().next());
	}
}
