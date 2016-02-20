package com.coolbeanzzz.development.dao.jpa;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.coolbeanzzz.development.dao.EventCauseDAO;
import com.coolbeanzzz.development.entities.EventCause;

@Default
@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class JPAEventCauseDAO implements EventCauseDAO {
	static Logger logger = Logger.getLogger("JPAEventCuaseDAO");
	
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {
		logger.info("in JPAEventCauseDAO.init");
		logger.info(em.toString());
	}
	
	
	public Collection<EventCause> getAllEventCauses() {
		Query query = em.createQuery("from EventCause");
		List<EventCause> eventCauses = query.getResultList();
		
		return eventCauses;
	}
}
