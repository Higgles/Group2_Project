/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.dao.jpa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
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
public class JPABaseDataDAO implements BaseDataDAO {
	private static final String[] uniqueEventIdsCauseCodeForPhoneTypeHeadings=
			new String[]{"EventId", "CauseCode", "UEType", "Total", "Manufacturer", "MarketingName"};
	private static final String[] baseDataHeadings=
			new String[]{"dateTime","EventId", "FailureClass", "UEType", "Market", "Operator", "CellId", "Duration", "CauseCode", "NeVersion", "IMSI", "HIER3_ID", "HIER32_ID", "HIER321_ID"};
	static Logger logger = Logger.getLogger("JPABaseDataDAO");
	
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {
		logger.info("in JPABaseDataDAO.init");
		logger.info(em.toString());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Collection<FailureTable> getAllTableRows() {
		Query query = em.createQuery("select bd.dateTime, bd.eventCause.eventId, bd.failureClass.failureClass, bd.ueTable.tac, bd.mccmnc.mcc, bd.mccmnc.mnc, bd.cellId, bd.duration, bd.eventCause.causeCode, bd.neVersion, bd.imsi, bd.hier3_Id, bd.hier32_Id, bd.hier321_Id from BaseData bd");
		List basedata = query.getResultList();
		basedata.add(0,baseDataHeadings);
		return basedata;
	}
	
	@Override
	public void populateTable(File filename) {	
		JSONObject baseRow;
		BaseData object;
		
		int j = 0;

		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(new FileReader(filename));

			JSONArray rows = (JSONArray) obj;

			Iterator<?> iteratorFile = rows.iterator();
			
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
						em.find(EventCause.class, new EventCausePrimaryKey(Integer.parseInt(baseRow.get("Event Id").toString()), Integer.parseInt(baseRow.get("Cause Code").toString()))),
						em.find(MccMnc.class, new MccMncPrimaryKey(Integer.parseInt(baseRow.get("Market").toString()),Integer.parseInt(baseRow.get("Operator").toString()))),
						em.find(UETable.class, Integer.parseInt(baseRow.get("UE Type").toString())),
						em.find(FailureClass.class, Integer.parseInt(baseRow.get("Failure Class").toString()))
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


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Collection<FailureTable> getUniqueEventIdsCauseCodeForPhoneType(int ueType) {
		Query query = em.createQuery(" select bd.eventCause.eventId, bd.eventCause.causeCode, bd.ueTable.tac, count(bd.id), bd.ueTable.manufacturer, bd.ueTable.marketingName"
				+ " from BaseData bd where bd.ueTable.tac=:ueType"
				+ " group by bd.eventCause.eventId, bd.eventCause.causeCode");
		query.setParameter("ueType", ueType);
		List basedata = query.getResultList();
		basedata.add(0,uniqueEventIdsCauseCodeForPhoneTypeHeadings);
		return basedata;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Collection<FailureTable> getNoOfCallFailuresAndDurationForImsiInDateRange(
			String fromDate, String toDate) {
		Query query = em.createQuery("select bd.dateTime, bd.imsi, count(bd.id), sum(duration) "
				+ "from BaseData bd "
				+ "where bd.dateTime>=:date1 and bd.dateTime<:date2 "
				+ "group by bd.imsi");
		query.setParameter("date1", fromDate);
		query.setParameter("date2", toDate);
		List basedata = query.getResultList();
		basedata.add(0,new Object[]{"DateTime", "IMSI", "Occurrences", "TotalCallDuration"});
		return basedata;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Collection<FailureTable> getImsiListBetween2Dates(String date1,String date2) {
		Query query = em.createQuery(""
		+"select bd.id, bd.imsi, bd.dateTime, bd.cellId, bd.neVersion "
		+"from BaseData bd "
		+"where bd.dateTime >=:date1 and bd.dateTime <:date2 "
		+"group by bd.imsi");
		query.setParameter("date1", date1);
		query.setParameter("date2", date2);
		List basedata = query.getResultList();
		basedata.add(0, new Object[]{"Id", "Imsi", "dateTime", "cellId", "neVersion"});
		return basedata;
	}	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Collection<FailureTable> getFailCountByPhoneModel(int ueType, String dateStart, String dateEnd) {
		Query query = em.createQuery(""
		+"select count(bd.id) "
		+"from BaseData bd where bd.ueTable.tac=:ueType "
		+"and bd.dateTime >=:dateStart and bd.dateTime <:dateEnd ");
		query.setParameter("ueType", ueType);
		query.setParameter("dateStart", dateStart);
		query.setParameter("dateEnd", dateEnd);
		List basedata = query.getResultList();
		basedata.add(0, new Object[]{"Count"});
		return basedata;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addNewEntry(FailureTable newEntry) {
		Query query = em.createQuery("from BaseData");
		List<FailureTable> entries = query.getResultList(); 
		if (!entries.contains(newEntry))
			em.persist(newEntry);	
	}

	@Override
	public void clearAllEntries() {
		Query query = em.createQuery("DELETE from BaseData");
		query.executeUpdate();
	}

	@Override
	public Collection<String> getAllImsiValues() {
		Query query = em.createQuery("select distinct bd.imsi from BaseData bd");
		List<String> basedata = query.getResultList();
		return basedata;
	}
	
	
	/**
	 * User Story 4
	 * 
	 * As Customer Service Rep. I want to display, for a given affected IMSI, 
	 * the Event ID and Cause Code for any / all failures affecting that IMSI
	 * 
	 */
	
	@Override
	public Collection<FailureTable> getEventIdsCauseCodeForIMSI(String IMSI) {
		Query query = em.createQuery(" select bd.eventCause.eventId, bd.eventCause.causeCode, bd.imsi "				//QUERY
				+ " from BaseData bd where bd.imsi=:IMSI"
				+ " group by bd.eventCause.eventId, bd.eventCause.causeCode");
		query.setParameter("IMSI", IMSI);
		List basedata = query.getResultList();
		basedata.add(0, new Object[]{"EventId", "CauseCode", "IMSI"});
		return basedata;
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
