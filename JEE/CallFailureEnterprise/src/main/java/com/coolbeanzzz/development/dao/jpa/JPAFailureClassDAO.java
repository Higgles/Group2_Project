/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.dao.jpa;

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

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.coolbeanzzz.development.dao.FailureClassDAO;
import com.coolbeanzzz.development.entities.FailureClass;
import com.coolbeanzzz.development.entities.FailureTable;

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
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<FailureTable> getAllTableRows() {
		Query query = em.createQuery("from FailureClass");
		List<FailureTable> failureClasses = query.getResultList();
		
		return failureClasses;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Integer> getFailureClassCodes() {
		Query query = em.createQuery("select f.failureClass from FailureClass f GROUP BY f.failureClass");
		List<Integer> failureClassIds = query.getResultList();
		
		return failureClassIds;
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public Collection<String> getAllDescriptions(int page, String searchTerm, int pageLimit) {
		Query query = em.createQuery("select distinct fc.description from FailureClass fc where fc.description like :searchTerm");
		query.setParameter("searchTerm", "%"+searchTerm+"%");
		if(pageLimit != -1){
			int start = pageLimit * (page - 1);
			query.setFirstResult(start).setMaxResults(pageLimit);
		}
		List<String> descriptions = query.getResultList();
		return descriptions;
	}
	
	@Override
    public void populateTable(JSONArray failureClassRows) {

		Iterator<?> iterator = failureClassRows.iterator();
		
		while (iterator.hasNext()) {
			JSONObject failureClass = (JSONObject) iterator.next();
			String failureCode = "Failure Class";
			String description = "Description";
			FailureClass object = new FailureClass(Integer.parseInt(failureClass.get(failureCode).toString()), failureClass.get(description).toString());
			em.merge(object);
		}
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public void addNewEntry(FailureTable newEntry) {
		Query query = em.createQuery("from FailureClass");
		List<FailureTable> entries = query.getResultList(); 
		if (!entries.contains(newEntry))
			em.persist(newEntry);	
	}	
	
	@Override
	public void clearAllEntries() {
		Query query = em.createQuery("DELETE from FailureClass");
		query.executeUpdate();
	}	
}