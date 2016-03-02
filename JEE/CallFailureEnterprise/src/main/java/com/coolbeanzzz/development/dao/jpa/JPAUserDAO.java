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
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.coolbeanzzz.development.dao.UserDAO;
import com.coolbeanzzz.development.entities.User;

@Default
@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class JPAUserDAO implements UserDAO{

static Logger logger = Logger.getLogger("JPAUserDAO");
	
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {
		logger.info("in JPAUserDAO.init");
		logger.info(em.toString());
	}
	
	
	public Collection<MccMnc> getAllUsers() {
		Query query = em.createQuery("from User");
		List<MccMnc> users = query.getResultList();
		
		return users;
	}
	
	
	
public void populateUserTable() {
    	Query query = em.createQuery("from User");
           
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
}
