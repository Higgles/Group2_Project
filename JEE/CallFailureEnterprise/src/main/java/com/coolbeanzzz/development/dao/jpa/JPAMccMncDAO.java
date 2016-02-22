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
import com.coolbeanzzz.development.entities.MccMnc;
import com.coolbeanzzz.development.entities.UETable;

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
	
	
	public Collection<MccMnc> getAllMccMncs() {
		Query query = em.createQuery("from MccMnc");
		List<MccMnc> mccMncs = query.getResultList();
		
		return mccMncs;
	}
	
	public void populateMccMncTable(File jsonFile) {
    	Query query = em.createQuery("from MccMnc");
           
        JSONParser parser = new JSONParser();
 
        try {
 
            Object obj = parser.parse(new FileReader(jsonFile));
            
            JSONArray rows = (JSONArray) obj;
            Iterator<Object> iterator = rows.iterator();
            
            while (iterator.hasNext()) {
              
                JSONObject mccMnc = (JSONObject) iterator.next();
                MccMnc object = new MccMnc(
                		Integer.parseInt(mccMnc.get("MCC").toString()),
                		Integer.parseInt(mccMnc.get("MNC").toString()),
                		mccMnc.get("COUNTRY").toString(),
                		mccMnc.get("OPERATOR").toString());
                List<MccMnc> mccMncs = query.getResultList();
        		em.persist(object);
            }
           
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
         
    }
}
