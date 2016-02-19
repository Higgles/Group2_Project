package com.coolbeanzzz.development.dao.jpa;

import java.util.ArrayList;
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

import com.coolbeanzzz.development.dao.FailureClassDAO;
import com.coolbeanzzz.development.entities.FailureClass;

@Default
@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class JPAFailureClassDAO implements FailureClassDAO {

	static Logger logger = Logger.getLogger("JPAFailureClassDAO");
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {
		logger.info("in JPAFailureClassDAO.init");
		logger.info(em.toString());
	}
	
	
	public Collection<FailureClass> getAllFailureClasses() {
		Query query = em.createQuery("from FailureClass");
		List<FailureClass> failureClasses = query.getResultList();
		
		return failureClasses;
	}
}