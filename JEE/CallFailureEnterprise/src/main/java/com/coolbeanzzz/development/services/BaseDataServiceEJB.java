/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.services;

import java.io.File;
import java.math.BigInteger;
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
import org.json.simple.JSONObject;

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
	
	/**
	 * Sets the attached DAO to the EJB service
	 * @param dao new dao
	 */
	public void setDao(BaseDataDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public Collection<FailureTable> getCatalog() {
		return dao.getAllTableRows();
	}
	
	@Override
	public void populateTable(File filename) {
		dao.populateTable(filename);
	}

	@Override
	public void populateTableObject(JSONArray validObj) {
		dao.populateTableObject(validObj);
	}
	
	@Override
	public Collection<FailureTable> getUniqueEventIdsCauseCodeForPhoneType(String manufacturer, String model) {
		return dao.getUniqueEventIdsCauseCodeForPhoneType(manufacturer, model);
	}

	@Override
	public Collection<FailureTable> getNoOfCallFailuresAndDurationForImsiInDateRange(String date1, String date2) {
		return dao.getNoOfCallFailuresAndDurationForImsiInDateRange(date1, date2);
	}

	@Override
	public Collection<FailureTable> getImsiListBetween2Dates(String date1,String date2) {
		return dao.getImsiListBetween2Dates(date1, date2);
	}
	
	@Override
	public Collection<FailureTable> getFailCountByPhoneModel(String manufacturer, String model, String dateStart, String dateEnd) {
		return dao.getFailCountByPhoneModel(manufacturer, model, dateStart, dateEnd);
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
	public Collection<String> getAllImsiValues() {
		return dao.getAllImsiValues();
	}
	
	@Override
	public Collection<FailureTable> getEventIdsCauseCodeForIMSI(String IMSI) {																						//EJB
		return dao.getEventIdsCauseCodeForIMSI(IMSI);
	}
}
