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
	public void populateTable(File jsonFile) {           
        JSONParser parser = new JSONParser();
 
        try {
            Object obj = parser.parse(new FileReader(jsonFile));
            
            JSONArray rows = (JSONArray) obj;
            Iterator<?> iterator = rows.iterator();
            
            while (iterator.hasNext()) {
              
                JSONObject ueTable = (JSONObject) iterator.next();
                UETable object = new UETable(
                		Integer.parseInt(ueTable.get("TAC").toString()),
                		ueTable.get("MARKETING NAME").toString(),
                		ueTable.get("MANUFACTURER").toString(),
                		ueTable.get("ACCESS CAPABILITY").toString(),
                		ueTable.get("MODEL").toString(),
                		ueTable.get("VENDOR NAME").toString(),
                		ueTable.get("UE TYPE").toString(),
                		ueTable.get("OS").toString(), 
                		ueTable.get("INPUT_MODE").toString());
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
}
