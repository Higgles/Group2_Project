/**
 * @author Coolbeanzzz
 */
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

import com.coolbeanzzz.development.dao.MccMncDAO;
import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.entities.MccMnc;

@Default
@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class JPAMccMncDAO implements MccMncDAO {
	static Logger logger = Logger.getLogger("JPAMccMncDAO");
	
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {
		logger.info("in JPAMccMncDAO.init");
		logger.info(em.toString());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<FailureTable> getAllTableRows() {
		Query query = em.createQuery("from MccMnc");
		List<FailureTable> mccMncs = query.getResultList();
		
		return mccMncs;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Integer> getAllUniqueMNCs() {
		Query query = em.createQuery("select m.mnc from MccMnc m GROUP BY m.mnc");
		List<Integer> mncs = query.getResultList();
		
		return mncs;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Integer> getAllUniqueMCCs() {
		Query query = em.createQuery("select m.mcc from MccMnc m GROUP BY m.mcc");
		List<Integer> mccs = query.getResultList();
		
		return mccs;
	}
	
	@Override
	public void populateTable(JSONArray mccMncRows) {
		
		Iterator<?> iterator = mccMncRows.iterator();
		JSONObject mccMnc = (JSONObject) iterator.next();
		
		while (iterator.hasNext()) {
			mccMnc = (JSONObject) iterator.next();
			String mcc = "MCC";
			String mnc = "MNC";
			String country = "Country";
			String operator = "Operator";
			
			MccMnc object = new MccMnc(
					Integer.parseInt(mccMnc.get(mcc).toString()),
					Integer.parseInt(mccMnc.get(mnc).toString()),
					mccMnc.get(country).toString(),
					mccMnc.get(operator).toString());
			em.merge(object);
		}
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public void addNewEntry(FailureTable newEntry) {
		Query query = em.createQuery("from MccMnc");
		List<FailureTable> entries = query.getResultList(); 
		if (!entries.contains(newEntry))
			em.persist(newEntry);	
	}	
	
	@Override
	public void clearAllEntries() {
		Query query = em.createQuery("DELETE from MccMnc");
		query.executeUpdate();
	}	
}
