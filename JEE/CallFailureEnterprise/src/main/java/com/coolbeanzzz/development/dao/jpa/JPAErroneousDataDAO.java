package com.coolbeanzzz.development.dao.jpa;

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
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.coolbeanzzz.development.dao.ErroneousDataDAO;
import com.coolbeanzzz.development.entities.ErroneousData;
import com.coolbeanzzz.development.entities.EventCause;
import com.coolbeanzzz.development.entities.FailureClass;
import com.coolbeanzzz.development.entities.MccMnc;
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


	public Collection<ErroneousData> getAllErroneousData() {
		Query query = em.createQuery("from ErroneousData");
		List<ErroneousData> erroneousData = query.getResultList();

		return erroneousData;
	}


	public void populateErroneousDataTable(JSONArray erroneous) {
		Query query = em.createQuery("from ErroneousData");

		Iterator<Object> iterator = erroneous.iterator();

		String failureClass;
		String causeCode;
		int j = 0;
		while (iterator.hasNext()) {
			if(j < erroneous.size()) j++;
			JSONObject erroneousRow = (JSONObject) iterator.next();
			
			if(erroneousRow.get("Failure Class").toString().contains("null")){
				failureClass = "NULL";
			}
			else{
			//	failureClass = Integer.parseInt(erroneousRow.get("Failure Class").toString());
				failureClass = erroneousRow.get("Failure Class").toString();
			}
			
			if(erroneousRow.get("Cause Code").toString().contains("null")){
				causeCode = "NULL";
			}
			else{
//				causeCode = Integer.parseInt(erroneousRow.get("Cause Code").toString());
				causeCode = erroneousRow.get("Cause Code").toString();
			}
			
			ErroneousData object = new ErroneousData(
//					Integer.parseInt(erroneousRow.get("id").toString()),
					j,
					erroneousRow.get("Date / Time").toString(),
					Integer.parseInt(erroneousRow.get("Event Id").toString()),
					failureClass,
//					Integer.parseInt(erroneousRow.get("Failure Class").toString()),
					Integer.parseInt(erroneousRow.get("UE Type").toString()),
					Integer.parseInt(erroneousRow.get("Market").toString()),
					Integer.parseInt(erroneousRow.get("Operator").toString()),
					erroneousRow.get("Cell Id").toString(),
					erroneousRow.get("Duration").toString(),
					causeCode,
//					Integer.parseInt(erroneousRow.get("Cause Code").toString()),
					erroneousRow.get("NE Version").toString(),
					erroneousRow.get("IMSI").toString(),
					erroneousRow.get("HIER3_ID").toString(),
					erroneousRow.get("HIER32_ID").toString(),
					erroneousRow.get("HIER321_ID").toString()
					);
//			List<ErroneousData> ErroneousData = query.getResultList();
			
			em.merge(object);
		}
	}
}
