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

import com.coolbeanzzz.development.dao.BaseDataDAO;
import com.coolbeanzzz.development.entities.FailureTable;

@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class BaseDataServiceEJB implements BaseDataService {

	Logger logger = Logger.getLogger("BaseDataServiceEJB");
	
	@PersistenceContext
	private EntityManager em;
		
	@Inject
	private BaseDataDAO dao;
	
	@Resource
	private SessionContext context;

	@PostConstruct
	public void init() {
		logger.info("in BaseDataServiceEJB.init");
		logger.info(em.toString());
	}
	
	public void setDao(BaseDataDAO dao) {
		// do something really important on injection
		this.dao = dao;
	}
	
	public Collection<FailureTable> getCatalog() {
		return dao.getAllTableRows();
	}
	
	public void populateTable(File filename) {
		dao.populateTable(filename);
	}

	@Override
	public Collection<FailureTable> getUniqueEventIdsCauseCodeForPhoneType(int ueType) {
		return dao.getUniqueEventIdsCauseCodeForPhoneType(ueType);
	}
}
