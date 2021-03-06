/**
 * @author Coolbeanzzz
 */
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

import org.json.simple.JSONArray;

import com.coolbeanzzz.development.dao.FailureClassDAO;
import com.coolbeanzzz.development.entities.FailureTable;

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
	
	/**
	 * Sets the attached DAO to the EJB service
	 * @param dao new dao
	 */
	public void setDao(FailureClassDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public Collection<FailureTable> getCatalog() {
		return dao.getAllTableRows();
	}
	
	@Override
	public void populateTable(JSONArray failureClassArray){
		dao.populateTable(failureClassArray);
	}
	
	@Override
	public Collection<Integer> getFailureClassCodes() {
		return dao.getFailureClassCodes();
	}
	
	@Override
	public Collection<String> getAllDescriptions(int page, String searchTerm, int pageLimit) {
		return dao.getAllDescriptions(page, searchTerm, pageLimit);
	}

	@Override
	public void addNewEntry(FailureTable newEntry) {
		dao.addNewEntry(newEntry);
	}
	
	@Override
	public void clearAllEntries() {
		dao.clearAllEntries();
	}
}
