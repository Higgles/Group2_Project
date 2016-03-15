/**
 * @author Coolbeanzzz
 */
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
	
	/**
	 * Sets the attached DAO to the EJB service
	 * @param dao new dao
	 */
	public void setDao(UETableDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public Collection<FailureTable> getCatalog() {
		return dao.getAllTableRows();
	}
	
	@Override
	public void populateTable(File jsonFile){
		dao.populateTable(jsonFile);
	}
	
	@Override
	public Collection<Integer> getUETypes() {
		return dao.getUETypes();
	}

	@Override
	public void addNewEntry(FailureTable newEntry) {
		dao.addNewEntry(newEntry);
	}
	
	@Override
	public void clearAllEntries() {
		dao.clearAllEntries();
	}

	@Override
	public Collection<String> getAllManufacturers() {
		return dao.getAllManufacturers();
	}

	@Override
	public Collection<String> getModelsForManufacturer(String manufacturer) {
		return dao.getModelsForManufacturer(manufacturer);
	}

	@Override
	public Collection<Integer> getUETypes(String manufacturer, String model) {
		return dao.getUETypes(manufacturer, model);
	}
}
