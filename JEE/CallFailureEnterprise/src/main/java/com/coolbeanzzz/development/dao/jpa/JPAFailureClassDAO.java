package com.coolbeanzzz.development.dao.jpa;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
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

import com.coolbeanzzz.development.dao.FailureClassDAO;
import com.coolbeanzzz.development.entities.FailureClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.json.Json;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



//import org.json.JSONArray;
//import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Default
@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class JPAFailureClassDAO implements FailureClassDAO {

	static Logger logger = Logger.getLogger("JPAFailureClassDAO");
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {
		logger.info("in JPAFailureClassDAO.init");
		logger.info(em.toString());
	}
	
	
	public Collection<FailureClass> getAllFailureClasses() {
		Query query = em.createQuery("from FailureClass");
		List<FailureClass> failureClasses = query.getResultList();
		
		return failureClasses;
	}
	
	
    public void populateFailureClassTable(File jsonFile) {
    	Query query = em.createQuery("from FailureClass");
           
        JSONParser parser = new JSONParser();
 
        try {
 
            Object obj = parser.parse(new FileReader(jsonFile));
            
            JSONArray rows = (JSONArray) obj;
            
            Iterator<Object> iterator = rows.iterator();
            
            while (iterator.hasNext()) {
              
                JSONObject failureClass = (JSONObject) iterator.next();
                FailureClass object = new FailureClass(Integer.parseInt(failureClass.get("Failure Class").toString()), failureClass.get("Description").toString());
                List<FailureClass> failures = query.getResultList();
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