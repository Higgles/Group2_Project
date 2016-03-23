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
	public void populateTable(File jsonFile) {           
        JSONParser parser = new JSONParser();
 
        try {
 
            Object obj = parser.parse(new FileReader(jsonFile));
            
            JSONArray rows = (JSONArray) obj;
            Iterator<?> iterator = rows.iterator();
            
            while (iterator.hasNext()) {  
                JSONObject mccMnc = (JSONObject) iterator.next();
                String mnc = mccMnc.keySet().toArray()[1].toString();
                String mcc = mccMnc.keySet().toArray()[2].toString();
                String country = mccMnc.keySet().toArray()[0].toString();
                String operator = mccMnc.keySet().toArray()[3].toString();
                MccMnc object = new MccMnc(
                		Integer.parseInt(mccMnc.get(mnc).toString()),
                		Integer.parseInt(mccMnc.get(mcc).toString()),
                		mccMnc.get(country).toString(),
                		mccMnc.get(operator).toString());
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
