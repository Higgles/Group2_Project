package com.coolbeanzzz.development.services;

import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.coolbeanzzz.development.dao.EventCauseDAO;
import com.coolbeanzzz.development.entities.EventCause;

@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class EventCauseServiceEJB implements EventCauseService {

	Logger logger = Logger.getLogger("EventCauseServiceEJB");
	
	@PersistenceContext
	private EntityManager em;
		
	@Inject
	private EventCauseDAO dao;
	
	@Resource
	private SessionContext context;

	@PostConstruct
	public void init() {
		logger.info("in EventCauseServiceEJB.init");
		logger.info(em.toString());
	}
	
	public void setDao(EventCauseDAO dao) {
		// do something really important on injection
		this.dao = dao;
	}
	
	public Collection<EventCause> getCatalog() {
		return dao.getAllEventCauses();
	}
}
