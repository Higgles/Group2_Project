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

import com.coolbeanzzz.development.dao.MccMncDAO;
import com.coolbeanzzz.development.entities.FailureTable;

@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class MccMncServiceEJB implements MccMncService {

	Logger logger = Logger.getLogger("MccMncServiceEJB");
	
	@PersistenceContext
	private EntityManager em;
		
	@Inject
	private MccMncDAO dao;
	
	@Resource
	private SessionContext context;

	@PostConstruct
	public void init() {
		logger.info("in MccMncServiceEJB.init");
		logger.info(em.toString());
	}
	
	/**
	 * Sets the attached DAO to the EJB service
	 * @param dao new dao
	 */
	public void setDao(MccMncDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public Collection<FailureTable> getCatalog() {
		return dao.getAllTableRows();
	}
	
	@Override
	public void populateTable(JSONArray mccMncArray){
		dao.populateTable(mccMncArray);
	}
	
	@Override
	public Collection<Integer> getAllUniqueMNCs() {
		return dao.getAllUniqueMNCs();
	}
	
	@Override
	public Collection<Integer> getAllUniqueMCCs() {
		return dao.getAllUniqueMCCs();
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
