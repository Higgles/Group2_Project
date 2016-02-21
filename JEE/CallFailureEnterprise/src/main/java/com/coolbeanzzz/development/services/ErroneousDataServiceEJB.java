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

import com.coolbeanzzz.development.dao.ErroneousDataDAO;
import com.coolbeanzzz.development.entities.ErroneousData;

@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class ErroneousDataServiceEJB implements ErroneousDataService {

	Logger logger = Logger.getLogger("ErroneousDataServiceEJB");
	
	@PersistenceContext
	private EntityManager em;
		
	@Inject
	private ErroneousDataDAO dao;
	
	@Resource
	private SessionContext context;

	@PostConstruct
	public void init() {
		logger.info("in ErroneousDataServiceEJB.init");
		logger.info(em.toString());
	}
	
	public void setDao(ErroneousDataDAO dao) {
		// do something really important on injection
		this.dao = dao;
	}
	
	public Collection<ErroneousData> getCatalog() {
		return dao.getAllErroneousData();
	}
}