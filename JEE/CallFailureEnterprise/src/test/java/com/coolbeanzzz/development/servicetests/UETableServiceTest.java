package com.coolbeanzzz.development.servicetests;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.coolbeanzzz.development.dao.UETableDAO;
import com.coolbeanzzz.development.dao.jpa.JPAUETableDAO;
import com.coolbeanzzz.development.entities.UETable;
import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.services.UETableServiceEJB;

public class UETableServiceTest {
	private UETableServiceEJB ueTableService;
	private UETable uetable1;
	private UETable uetable2;
	
	@Before
	public void setUp() throws Exception {
		UETableDAO mockedUETableDao = mock(JPAUETableDAO.class);
		uetable1 = new UETable(0, "", "", "", "", "", "", "", "");
		uetable2 = new UETable(1, "", "", "", "", "", "", "", "");
		Collection<FailureTable> ues = new ArrayList<>();
		ues.add(uetable1);
		ues.add(uetable2);
		when(mockedUETableDao.getAllTableRows()).thenReturn(ues);
		
		Collection<Integer> tacs = new ArrayList<>();
		tacs.add(uetable1.getTac());
		tacs.add(uetable2.getTac());
		when(mockedUETableDao.getUETypes()).thenReturn(tacs);
				
		ueTableService=new UETableServiceEJB();
		ueTableService.setDao(mockedUETableDao);
	}

	@Test
	public void getCatalogtest() {
		Collection<FailureTable> uetables = ueTableService.getCatalog();
		assertEquals(uetable1, uetables.iterator().next());
	}
	
	@Test
	public void getAllUniqueUETablees(){
		Collection<Integer> uniqueCodes = ueTableService.getUETypes();
		Iterator<Integer> uniqueValues = uniqueCodes.iterator();
		
		assertEquals(uetable1.getTac(), uniqueValues.next().intValue());
		assertEquals(uetable2.getTac(), uniqueValues.next().intValue());
	}
}