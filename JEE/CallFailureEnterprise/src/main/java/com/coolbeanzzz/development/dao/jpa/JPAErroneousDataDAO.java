/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.dao.jpa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.coolbeanzzz.development.dao.ErroneousDataDAO;
import com.coolbeanzzz.development.entities.BaseData;
import com.coolbeanzzz.development.entities.ErroneousData;
import com.coolbeanzzz.development.entities.EventCause;
import com.coolbeanzzz.development.entities.EventCausePrimaryKey;
import com.coolbeanzzz.development.entities.FailureClass;
import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.entities.MccMnc;
import com.coolbeanzzz.development.entities.MccMncPrimaryKey;
import com.coolbeanzzz.development.entities.UETable;

@Default
@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class JPAErroneousDataDAO implements ErroneousDataDAO {
	static Logger logger = Logger.getLogger("JPAErroneousDataDAO");

	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {
		logger.info("in JPAErroneousDataDAO.init");
		logger.info(em.toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<FailureTable> getAllTableRows() {
		Query query = em.createQuery("from ErroneousData");
		List<FailureTable> erroneousData = query.getResultList();

		return erroneousData;
	}
	
	@Override
	public void populateTable(JSONArray erroneousData){
		Iterator<?> iteratorErroneous = erroneousData.iterator();
		
		String failureClass;
		String causeCode;
		
		while(iteratorErroneous.hasNext()){
			JSONObject erroneousRow = (JSONObject) iteratorErroneous.next();
			
			if(erroneousRow.get("Failure Class").toString().contains("null")){
				failureClass = "NULL";
			}
			else{
				failureClass = erroneousRow.get("Failure Class").toString();
			}
			
			if(erroneousRow.get("Cause Code").toString().contains("null")){
				causeCode = "NULL";
			}
			else{
				causeCode = erroneousRow.get("Cause Code").toString();
			}
			
			ErroneousData erroneousObj = new ErroneousData(
					0,
					erroneousRow.get("Date / Time").toString(),
					Integer.parseInt(erroneousRow.get("Event Id").toString()),
					failureClass,
					Integer.parseInt(erroneousRow.get("UE Type").toString()),
					Integer.parseInt(erroneousRow.get("Market").toString()),
					Integer.parseInt(erroneousRow.get("Operator").toString()),
					erroneousRow.get("Cell Id").toString(),
					erroneousRow.get("Duration").toString(),
					causeCode,
					erroneousRow.get("NE Version").toString(),
					erroneousRow.get("IMSI").toString(),
					erroneousRow.get("HIER3_ID").toString(),
					erroneousRow.get("HIER32_ID").toString(),
					erroneousRow.get("HIER321_ID").toString()
					);
			
			em.persist(erroneousObj);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addNewEntry(FailureTable newEntry) {
		Query query = em.createQuery("from ErroneousData");
		List<FailureTable> entries = query.getResultList(); 
		if (!entries.contains(newEntry))
			em.persist(newEntry);	
	}
	
	@Override
	public void clearAllEntries() {
		Query query = em.createQuery("DELETE from ErroneousData");
		query.executeUpdate();
	}	
}
