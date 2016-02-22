package com.coolbeanzzz.development.services;

import java.io.File;
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

import com.coolbeanzzz.development.dao.FailureClassDAO;
import com.coolbeanzzz.development.entities.FailureClass;

@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class FailureClassServiceEJB implements FailureClassService {

	Logger logger = Logger.getLogger("FailureClassServiceEJB");
	
	@PersistenceContext
	private EntityManager em;
		
	@Inject
	private FailureClassDAO dao;
	
	@Resource
	private SessionContext context;

	@PostConstruct
	public void init() {
		logger.info("in FailureClassServiceEJB.init");
		logger.info(em.toString());
	}
	
	public void setDao(FailureClassDAO dao) {
		// do something really important on injection
		this.dao = dao;
	}
	
	public Collection<FailureClass> getCatalog() {
		return dao.getAllFailureClasses();
	}
	
	public void populateTable(File jsonFile){
		dao.populateFailureClassTable(jsonFile);
	}
	
	public Collection<Integer> getFailureClasseCodes() {
		return dao.getFailureClasseCodes();
	}
}
