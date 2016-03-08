package com.coolbeanzzz.development.servicetests;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.coolbeanzzz.development.dao.EventCauseDAO;
import com.coolbeanzzz.development.dao.jpa.JPAEventCauseDAO;
import com.coolbeanzzz.development.entities.EventCause;
import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.services.EventCauseServiceEJB;

public class EventCauseServiceTest {
	private EventCauseServiceEJB eventCauseService;
	private EventCause eventCause1;
	private EventCause eventCause2;
	
	@Before
	public void setUp() throws Exception {
		EventCauseDAO mockedEventCauseDao = mock(JPAEventCauseDAO.class);
		eventCause1 = new EventCause(0, 0, "");
		eventCause2 = new EventCause(1, 1, "");
		Collection<FailureTable> ecs = new ArrayList<>();
		ecs.add(eventCause1);
		ecs.add(eventCause2);
		when(mockedEventCauseDao.getAllTableRows()).thenReturn(ecs);
		
		Collection<Integer> uniqueCodes = new ArrayList<>();
		uniqueCodes.add(eventCause1.getCauseCode());
		uniqueCodes.add(eventCause2.getCauseCode());
		when(mockedEventCauseDao.getAllUniqueCauseCodes()).thenReturn(uniqueCodes);
		
		Collection<Integer> uniqueEventIds = new ArrayList<>();
		uniqueEventIds.add(eventCause1.getEventId());
		uniqueEventIds.add(eventCause2.getEventId());
		when(mockedEventCauseDao.getAllUniqueEventIds()).thenReturn(uniqueEventIds);
		
		eventCauseService=new EventCauseServiceEJB();
		eventCauseService.setDao(mockedEventCauseDao);
	}

	@Test
	public void getCatalogtest() {
		Collection<FailureTable> eventCauses = eventCauseService.getCatalog();
		assertEquals(eventCause1, eventCauses.iterator().next());
	}
	
	@Test
	public void getAllUniqueCauseCodes(){
		Collection<Integer> uniqueCodes = eventCauseService.getAllUniqueCauseCodes();
		Iterator<Integer> uniqueValues = uniqueCodes.iterator();
		
		assertEquals(eventCause1.getCauseCode(), uniqueValues.next().intValue());
		assertEquals(eventCause2.getCauseCode(), uniqueValues.next().intValue());
	}
	
	@Test
	public void getAllUniqueEventIds(){
		Collection<Integer> uniqueIds = eventCauseService.getAllUniqueEventIds();
		Iterator<Integer> uniqueValues = uniqueIds.iterator();
		
		assertEquals(eventCause1.getEventId(), uniqueValues.next().intValue());
		assertEquals(eventCause2.getEventId(), uniqueValues.next().intValue());
	}
}
