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
			new String[]{"Event Id", "Cause Code", "Number of Occurences", "UE Type", "Manufacturer", "Marketing Name"};
	private static final String[] getImsiListBetween2DatesHeadings=
			new String[]{"IMSI", "Market", "Operator", "Total Duration"};
	private static final String[] noOfCallFailuresAndDurationForImsiInDateRangeHeadings=
			new String[]{"IMSI", "Number of Failures", "Total Duration"};
	private static final String[] failCountByPhoneModelHeadings=
			new String[]{"Manufacturer", "Model","Number of Failures", "Total Duration"};
	private static final String[] uniqueCauseCodeForImsiHeadings=
			new String[]{"Event Id", "Cause Code", "Description", "Number of Occurences"};
	private static final String[] allEventIdsCauseCodeForImsiHeadings=
			new String[]{"Date/Time","Event Id", "Cause Code", "Description"};
	private static final String[] top10ImsiListBetween2DatesHeadings=
			new String[]{"IMSI", "Market", "Operator", "Number of Occurences"};
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
	public void populateTable(JSONArray objectArray){
		Iterator<?> iteratorValid = objectArray.iterator();
		
		while(iteratorValid.hasNext()){
			JSONObject validObj = (JSONObject) iteratorValid.next();
			
			BaseData baseDataObject = new BaseData(
					0,
					validObj.get("Date / Time").toString(),
					validObj.get("Cell Id").toString(),
					validObj.get("Duration").toString(),
					validObj.get("NE Version").toString(),
					validObj.get("IMSI").toString(),
					validObj.get("HIER3_ID").toString(),
					validObj.get("HIER32_ID").toString(),
					validObj.get("HIER321_ID").toString(),
					em.find(EventCause.class, new EventCausePrimaryKey(Integer.parseInt(validObj.get("Event Id").toString()), Integer.parseInt(validObj.get("Cause Code").toString()))),
					em.find(MccMnc.class, new MccMncPrimaryKey(Integer.parseInt(validObj.get("Market").toString()),Integer.parseInt(validObj.get("Operator").toString()))),
					em.find(UETable.class, Integer.parseInt(validObj.get("UE Type").toString())),
					em.find(FailureClass.class, Integer.parseInt(validObj.get("Failure Class").toString()))
					);
			
			em.merge(baseDataObject);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Collection<FailureTable> getUniqueEventIdsCauseCodeForPhoneType(String manufacturer, String model) {
		Query query = em.createQuery(" select bd.eventCause.eventId, bd.eventCause.causeCode, count(bd.id), bd.ueTable.tac, bd.ueTable.manufacturer, bd.ueTable.marketingName"
				+ " from BaseData bd where bd.ueTable.manufacturer=:manufacturer and bd.ueTable.marketingName=:marketingName"
				+ " group by bd.eventCause.eventId, bd.eventCause.causeCode");
		query.setParameter("manufacturer", manufacturer);
		query.setParameter("marketingName", model);
		List basedata = query.getResultList();
		basedata.add(0,uniqueEventIdsCauseCodeForPhoneTypeHeadings);
		return basedata;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Collection<FailureTable> getNoOfCallFailuresAndDurationForImsiInDateRange(
			String fromDate, String toDate) {
		Query query = em.createQuery("select bd.imsi, count(bd.id), sum(duration) "
				+ "from BaseData bd "
				+ "where bd.dateTime>=:date1 and bd.dateTime<=:date2 "
				+ "group by bd.imsi");
		query.setParameter("date1", fromDate);
		query.setParameter("date2", toDate);
		List basedata = query.getResultList();
		basedata.add(0, noOfCallFailuresAndDurationForImsiInDateRangeHeadings);
		return basedata;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Collection<FailureTable> getImsiListBetween2Dates(String date1,String date2) {
		Query query = em.createQuery(""
		+"select bd.imsi, bd.mccmnc.country, bd.mccmnc.operator, sum(bd.duration) "
		+"from BaseData bd "
		+"where bd.dateTime >=:date1 and bd.dateTime <=:date2 "
		+"group by bd.imsi");
		query.setParameter("date1", date1);
		query.setParameter("date2", date2);
		List basedata = query.getResultList();
		basedata.add(0, getImsiListBetween2DatesHeadings);
		return basedata;
	}	
	
	//************************* MIKE G ****************************** 22/3/16
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Collection<FailureTable> getFailCountByImsiAndDate(String imsi, String date1, String date2){
		Query query = em.createQuery(""
				+"select count(bd.id), sum(duration) "
				+"from BaseData bd "
				+"where bd.imsi=:imsi "
				+"and bd.dateTime >=:date1 and bd.dateTime <:date2 ");

		query.setParameter("imsi", imsi);
		query.setParameter("date1", date1);
		query.setParameter("date2", date2);
		List basedata = query.getResultList();
		basedata.add(0, new Object[]{"Count", "Total Duration"});
		return basedata;
	}
	
	//************************* MIKE G ****************************** 23/3/16
	//Top 10 Market/Operator/Cell ID combinations that had call failures during a time period
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Collection<FailureTable> getTop10MarketOperatorCellBetween2Dates(String dateStart, String dateEnd){
		Query query = em.createQuery(""
				+"select bd.mccmnc.country, bd.mccmnc.operator, bd.cellId, count(bd.id) "  
				+"from BaseData bd "
				+"where bd.dateTime >=:date1 and bd.dateTime <:date2 "
				+"group by bd.mccmnc.country, bd.mccmnc.operator, bd.cellId "						
				+"order by count(bd.id) desc ");
		query.setParameter("date1", dateStart);
		query.setParameter("date2", dateEnd);
		List basedata = query.setMaxResults(10).getResultList();
		basedata.add(0, new Object[]{"Market", "Operator","Cell Id","Count"});
		return basedata;	
	}
	
	//************************* MIKE G ****************************** 24/3/16
	//As a Support Engineer I want to display, for a given failure Cause Class, the IMSIs that were affected.
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Collection<FailureTable> getIMSIsforFailureClass(String failureClass){
			Query query = em.createQuery(""
					+"select bd.imsi, count(bd.id) "  
					+"from BaseData bd "
					+"where bd.failureClass.description =:failureClass "
					+"group by bd.imsi ");
			query.setParameter("failureClass",failureClass);
			List basedata = query.getResultList();
			basedata.add(0, new Object[]{"IMSIs","Count"});
			return basedata;	
		}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Collection<FailureTable> getFailCountByPhoneModel(String manufacturer, String model, String dateStart, String dateEnd) {
		Query query = em.createQuery(""
		+"select bd.ueTable.manufacturer, bd.ueTable.marketingName, count(bd.id), sum(bd.duration) "
		+"from BaseData bd where bd.ueTable.manufacturer=:manufacturer and bd.ueTable.marketingName=:marketingName "
		+"and bd.dateTime >=:dateStart and bd.dateTime <=:dateEnd ");
		query.setParameter("manufacturer", manufacturer);
		query.setParameter("marketingName", model);
		query.setParameter("dateStart", dateStart);
		query.setParameter("dateEnd", dateEnd);
		List<Object[]> results = query.getResultList();
		if(results.get(0)[0]==null){
			results.set(0, new Object[]{manufacturer,model, 0, 0});
		}
		List basedata = results;
		basedata.add(0, failCountByPhoneModelHeadings);
		
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
	 * As Customer Service Rep. I want to display, for a given affected IMSI, 
	 * the Event ID and Cause Code for any / all failures affecting that IMSI
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Collection<FailureTable> getEventIdsCauseCodeForIMSI(String IMSI) {
		Query query = em.createQuery(" select bd.dateTime, bd.eventCause.eventId, bd.eventCause.causeCode, bd.eventCause.description "				//QUERY
				+ " from BaseData bd where bd.imsi=:IMSI");
		query.setParameter("IMSI", IMSI);
		List basedata = query.getResultList();
		basedata.add(0, allEventIdsCauseCodeForImsiHeadings);
		return basedata;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Collection<FailureTable> getUniqueCauseCodeForIMSI(String IMSI) {
		Query query = em.createQuery(" select bd.eventCause.eventId, bd.eventCause.causeCode, bd.eventCause.description, count(bd.eventCause.causeCode) "
				+ " from BaseData bd where bd.imsi=:IMSI"
				+ " group by bd.eventCause.eventId, bd.eventCause.causeCode");
		query.setParameter("IMSI", IMSI);
		List basedata = query.getResultList();
		basedata.add(0,uniqueCauseCodeForImsiHeadings);
		return basedata;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Collection<FailureTable> getTop10ImsiListBetween2Dates(String date1,String date2) {
		Query query = em.createQuery(""
				+"select bd.imsi, bd.mccmnc.country, bd.mccmnc.operator, count(bd.imsi) "
				+"from BaseData bd "
				+"where bd.dateTime >=:date1 and bd.dateTime <=:date2 "
				+"group by bd.imsi "
				+"order by count(bd.imsi) desc ");
				query.setParameter("date1", date1);
				query.setParameter("date2", date2);
				List basedata = query.setMaxResults(10).getResultList();
				basedata.add(0, top10ImsiListBetween2DatesHeadings);
				return basedata;
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public Collection<String> getAllFailureValues() {
		Query query = em.createQuery("select distinct bd.failureClass.description from FailureClass bd");
		List<String> basedata = query.getResultList();
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
