package com.coolbeanzzz.development.servicetests;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.coolbeanzzz.development.dao.FailureClassDAO;
import com.coolbeanzzz.development.dao.jpa.JPAFailureClassDAO;
import com.coolbeanzzz.development.entities.FailureClass;
import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.services.FailureClassServiceEJB;

public class FailureClassServiceTest {
	private FailureClassServiceEJB failureClassService;
	private FailureClass failureClass1;
	private FailureClass failureClass2;
	
	@Before
	public void setUp() throws Exception {
		FailureClassDAO mockedFailureClassDao = mock(JPAFailureClassDAO.class);
		failureClass1 = new FailureClass(0,"");
		failureClass2 = new FailureClass(1, "");
		Collection<FailureTable> fcs = new ArrayList<>();
		fcs.add(failureClass1);
		fcs.add(failureClass2);
		when(mockedFailureClassDao.getAllTableRows()).thenReturn(fcs);
		
		Collection<Integer> failureClasses = new ArrayList<>();
		failureClasses.add(failureClass1.getFailureClass());
		failureClasses.add(failureClass2.getFailureClass());
		when(mockedFailureClassDao.getFailureClassCodes()).thenReturn(failureClasses);
				
		failureClassService=new FailureClassServiceEJB();
		failureClassService.setDao(mockedFailureClassDao);
	}

	@Test
	public void getCatalogtest() {
		Collection<FailureTable> failureClass = failureClassService.getCatalog();
		assertEquals(failureClass1, failureClass.iterator().next());
	}
	
	@Test
	public void getAllUniqueFailureClasses(){
		Collection<Integer> uniqueCodes = failureClassService.getFailureClassCodes();
		Iterator<Integer> uniqueValues = uniqueCodes.iterator();
		
		assertEquals(failureClass1.getFailureClass(), uniqueValues.next().intValue());
		assertEquals(failureClass2.getFailureClass(), uniqueValues.next().intValue());
	}
}
