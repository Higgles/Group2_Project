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

import com.coolbeanzzz.development.dao.BaseDataDAO;
import com.coolbeanzzz.development.entities.BaseData;
import com.coolbeanzzz.development.entities.EventCause;
import com.coolbeanzzz.development.entities.FailureClass;
import com.coolbeanzzz.development.entities.MccMnc;
import com.coolbeanzzz.development.entities.UETable;

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
	
	public void populateBaseDataTable(File filename) {
		Query query = em.createQuery("from BaseData");

//		Iterator<Object> iterator = baseData.iterator();
		
		JSONObject baseRow;
		BaseData object;
		
		EventCause eventCause;
		MccMnc mccmnc;
		UETable ueTable;
		FailureClass failureClass;
		
		int j = 0;

		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(new FileReader(filename));

			JSONArray rows = (JSONArray) obj;

			Iterator<Object> iteratorFile = rows.iterator();
			
			while(iteratorFile.hasNext()){
				j++;
				
				baseRow = (JSONObject) iteratorFile.next();
				
				object = new BaseData(
						j,
						baseRow.get("Date / Time").toString(),
						baseRow.get("Cell Id").toString(),
						baseRow.get("Duration").toString(),
						baseRow.get("NE Version").toString(),
						baseRow.get("IMSI").toString(),
						baseRow.get("HIER3_ID").toString(),
						baseRow.get("HIER32_ID").toString(),
						baseRow.get("HIER321_ID").toString(),
						eventCause = new EventCause(Integer.parseInt(baseRow.get("Cause Code").toString()),
								Integer.parseInt(baseRow.get("Event Id").toString()), ""),
						mccmnc = new MccMnc(Integer.parseInt(baseRow.get("Market").toString()),
								Integer.parseInt(baseRow.get("Operator").toString()), "", ""),
						ueTable = new UETable(Integer.parseInt(baseRow.get("UE Type").toString()), "", "", "", "", "",
								"", "", ""),
						failureClass = new FailureClass(Integer.parseInt(baseRow.get("Failure Class").toString()), "")
						);
				
				em.merge(object);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/*public void populateBaseDataTableJSON(JSONArray baseData) {
		Query query = em.createQuery("from BaseData");

		Iterator<Object> iterator = baseData.iterator();
		
		JSONObject baseRow;
		BaseData object;
		
		EventCause eventCause;
		MccMnc mccmnc;
		UETable ueTable;
		FailureClass failureClass;
		
		int j = 0;
		
		Iterator<Object> iteratorFile = baseData.iterator();

		while (iterator.hasNext()) {
			if(j < baseData.size()) j++;

			baseRow = (JSONObject) iterator.next();

			object = new BaseData(
					j,
					baseRow.get("Date / Time").toString(),
					baseRow.get("Cell Id").toString(),
					baseRow.get("Duration").toString(),
					baseRow.get("NE Version").toString(),
					baseRow.get("IMSI").toString(),
					baseRow.get("HIER3_ID").toString(),
					baseRow.get("HIER32_ID").toString(),
					baseRow.get("HIER321_ID").toString(),
					eventCause = new EventCause(Integer.parseInt(baseRow.get("Cause Code").toString()),
							Integer.parseInt(baseRow.get("Event Id").toString()), ""),
					mccmnc = new MccMnc(Integer.parseInt(baseRow.get("Market").toString()),
									Integer.parseInt(baseRow.get("Operator").toString()), "", ""),
					ueTable = new UETable(Integer.parseInt(baseRow.get("UE Type").toString()), "", "", "", "", "",
											"", "", ""),
					failureClass = new FailureClass(Integer.parseInt(baseRow.get("Failure Class").toString()), "")
					);

			em.merge(object);

		}
	}*/
}
