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

import com.coolbeanzzz.development.dao.EventCauseDAO;
import com.coolbeanzzz.development.entities.EventCause;
import com.coolbeanzzz.development.entities.MccMnc;

@Default
@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class JPAEventCauseDAO implements EventCauseDAO {
	static Logger logger = Logger.getLogger("JPAEventCauseDAO");
	
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {
		logger.info("in JPAEventCauseDAO.init");
		logger.info(em.toString());
	}
	
	
	public Collection<EventCause> getAllEventCauses() {
		Query query = em.createQuery("from EventCause");
		List<EventCause> eventCauses = query.getResultList();
		
		return eventCauses;
	}
	
	public Collection<Integer> getAllUniqueEventCauses() {
		Query query = em.createQuery("select e.eventId from EventCause e GROUP BY e.eventId");
		List<Integer> eventCauses = query.getResultList();
		
		return eventCauses;
	}
	
	public void populateEventCauseTable(File jsonFile) {
    	Query query = em.createQuery("from EventCause");
           
        JSONParser parser = new JSONParser();
 
        try {
 
            Object obj = parser.parse(new FileReader(jsonFile));
            
            JSONArray rows = (JSONArray) obj;
            Iterator<Object> iterator = rows.iterator();
            
            while (iterator.hasNext()) {
              
                JSONObject eventCause = (JSONObject) iterator.next();
                EventCause object = new EventCause(
                		Integer.parseInt(eventCause.get("Cause Code").toString()),
                		Integer.parseInt(eventCause.get("Event Id").toString()),
                		eventCause.get("Description").toString());
                List<EventCause> eventCauses = query.getResultList();
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
