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

import com.coolbeanzzz.development.dao.EventCauseDAO;
import com.coolbeanzzz.development.entities.EventCause;
import com.coolbeanzzz.development.entities.FailureTable;

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
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<FailureTable> getAllTableRows() {
		Query query = em.createQuery("select e.eventId, e.causeCode from EventCause e");
		List<FailureTable> eventCauses = query.getResultList();
		
		return eventCauses;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Integer> getAllUniqueEventIds() {
		Query query = em.createQuery("select e.eventId from EventCause e GROUP BY e.eventId");
		List<Integer> eventIds = query.getResultList();
		
		return eventIds;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Integer> getAllUniqueCauseCodes() {
		Query query = em.createQuery("select e.causeCode from EventCause e GROUP BY e.causeCode");
		List<Integer> causeCodes = query.getResultList();
		
		return causeCodes;
	}
	
	@Override
	public void populateTable(JSONArray eventCauseRows) {

		Iterator<?> iterator = eventCauseRows.iterator();
		
		while (iterator.hasNext()) {
			JSONObject eventCause = (JSONObject) iterator.next();
			
			String causeCode = "Cause Code";
			String eventId = "Event Id";
			String description = "Description";
			
			EventCause object = new EventCause(
					Integer.parseInt(eventCause.get(causeCode).toString()),
					Integer.parseInt(eventCause.get(eventId).toString()),
					eventCause.get(description).toString());
			em.merge(object);
		}
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public void addNewEntry(FailureTable newEntry) {
		Query query = em.createQuery("from EventCause");
		List<FailureTable> entries = query.getResultList(); 
		if (!entries.contains(newEntry))
			em.persist(newEntry);	
	}
	
	@Override
	public void clearAllEntries() {
		Query query = em.createQuery("DELETE from EventCause");
		query.executeUpdate();
	}	
}
