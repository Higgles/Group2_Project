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

import com.coolbeanzzz.development.dao.UETableDAO;
import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.entities.UETable;

@Default
@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class JPAUETableDAO implements UETableDAO {
	static Logger logger = Logger.getLogger("JPAUETableDAO");
	
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {
		logger.info("in JPAUETable.init");
		logger.info(em.toString());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<FailureTable> getAllTableRows() {
		Query query = em.createQuery("from UETable");
		List<FailureTable> ueTables = query.getResultList();
		
		return ueTables;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Integer> getUETypes() {
		Query query = em.createQuery("select u.tac from UETable u GROUP BY u.tac");
		List<Integer> ueTables = query.getResultList();
		
		return ueTables;
	}
	
	@Override
	public void populateTable(JSONArray ueRows) {

		Iterator<?> iterator = ueRows.iterator();
		JSONObject ueTable = (JSONObject) iterator.next();
		
		while (iterator.hasNext()) {
			ueTable = (JSONObject) iterator.next();
			String tac = ueTable.keySet().toArray()[2].toString();
			String marketingName = ueTable.keySet().toArray()[0].toString();
			String manufacturer = ueTable.keySet().toArray()[6].toString();
			String accessCapability = ueTable.keySet().toArray()[7].toString();
			String model = ueTable.keySet().toArray()[4].toString();
			String vendorName = ueTable.keySet().toArray()[3].toString();
			String ueType = ueTable.keySet().toArray()[1].toString();
			String os = ueTable.keySet().toArray()[5].toString();
			String inputMode = ueTable.keySet().toArray()[8].toString();
			UETable object = new UETable(
					Integer.parseInt(ueTable.get(tac).toString()),
					ueTable.get(marketingName).toString(),
					ueTable.get(manufacturer).toString(),
					ueTable.get(accessCapability).toString(),
					ueTable.get(model).toString(),
					ueTable.get(vendorName).toString(),
					ueTable.get(ueType).toString(),
					ueTable.get(os).toString(), 
					ueTable.get(inputMode).toString());
			em.merge(object);
		}
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public void addNewEntry(FailureTable newEntry) {
		Query query = em.createQuery("from UETable");
		List<FailureTable> entries = query.getResultList(); 
		if (!entries.contains(newEntry))
			em.persist(newEntry);	
	}	
	
	@Override
	public void clearAllEntries() {
		Query query = em.createQuery("DELETE from UETable");
		query.executeUpdate();
	}

	@Override
	public Collection<String> getAllManufacturers() {
		Query query = em.createQuery("select distinct u.manufacturer from UETable u order by u.manufacturer");
		List<String> ueTables = query.getResultList();
		
		return ueTables;
	}

	@Override
	public Collection<String> getModelsForManufacturer(String manufacturer) {
		Query query = em.createQuery("select u.marketingName from UETable u where u.manufacturer=:manufacturer");
		query.setParameter("manufacturer", manufacturer);
		List<String> ueTables = query.getResultList();
		
		return ueTables;
	}

	@Override
	public Collection<Integer> getUETypes(String manufacturer, String model) {
		Query query = em.createQuery("select u.tac from UETable u where u.manufacturer=:manufacturer and u.marketingName=:marketingName");
		query.setParameter("manufacturer", manufacturer);
		query.setParameter("marketingName", model);
		List<Integer> ueTables = query.getResultList();
		
		return ueTables;
	}	
}
