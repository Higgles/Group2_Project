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

import com.coolbeanzzz.development.dao.UETableDAO;
import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.entities.UETable;

@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class UETableServiceEJB implements UETableService {

	Logger logger = Logger.getLogger("UETableServiceEJB");
	
	@PersistenceContext
	private EntityManager em;
		
	@Inject
	private UETableDAO dao;
	
	@Resource
	private SessionContext context;

	@PostConstruct
	public void init() {
		logger.info("in UETableServiceEJB.init");
		logger.info(em.toString());
	}
	
	public void setDao(UETableDAO dao) {
		this.dao = dao;
	}
	
	public Collection<FailureTable> getCatalog() {
		return dao.getAllTableRows();
	}
	
	public void populateTable(File jsonFile){
		dao.populateTable(jsonFile);
	}
	
	public Collection<Integer> getUETypes() {
		return dao.getUETypes();
	}
}
