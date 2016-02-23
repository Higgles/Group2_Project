package com.coolbeanzzz.development.dao.jpa;

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

import com.coolbeanzzz.development.dao.BaseDataDAO;
import com.coolbeanzzz.development.entities.BaseData;

@Default
@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class JPABaseDataDAO implements BaseDataDAO {
	static Logger logger = Logger.getLogger("JPABaseDataDAO");
	
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {
		logger.info("in JPABaseDataDAO.init");
		logger.info(em.toString());
	}
	
	
	public Collection<BaseData> getAllBaseData() {
		Query query = em.createQuery("from BaseData");
		List<BaseData> basedata = query.getResultList();
		
		return basedata;
	}
	
	public void populateBaseDataTable(JSONArray baseData) {
		Query query = em.createQuery("from BaseData");

		Iterator<Object> iterator = baseData.iterator();

		int failureClass;
		int causeCode;
		int j = 0;
		while (iterator.hasNext()) {
			if(j < baseData.size()) j++;

			JSONObject baseRow = (JSONObject) iterator.next();
			
			if(baseRow.get("Failure Class").toString().contains("null")){
				failureClass = -1;
			}
			else{
				failureClass = Integer.parseInt(baseRow.get("Failure Class").toString());
			}
			
			if(baseRow.get("Cause Code").toString().contains("null")){
				causeCode = -1;
			}
			else{
				causeCode = Integer.parseInt(baseRow.get("Cause Code").toString());
			}
			
			BaseData object = new BaseData(
//					Integer.parseInt(erroneousRow.get("id").toString()),
					j,
					baseRow.get("Date / Time").toString(),
					Integer.parseInt(baseRow.get("Event Id").toString()),
//					failureClass,
					Integer.parseInt(baseRow.get("Failure Class").toString()),
					Integer.parseInt(baseRow.get("UE Type").toString()),
					Integer.parseInt(baseRow.get("Market").toString()),
					Integer.parseInt(baseRow.get("Operator").toString()),	
					baseRow.get("Cell Id").toString(),
					baseRow.get("Duration").toString(),
//					causeCode,
					Integer.parseInt(baseRow.get("Cause Code").toString()),
					baseRow.get("NE Version").toString(),
					baseRow.get("IMSI").toString(),
					baseRow.get("HIER3_ID").toString(),
					baseRow.get("HIER32_ID").toString(),
					baseRow.get("HIER321_ID").toString()
					);
//			List<BaseData> BaseData = query.getResultList();
			
			em.merge(object);

		} 
	}
}
