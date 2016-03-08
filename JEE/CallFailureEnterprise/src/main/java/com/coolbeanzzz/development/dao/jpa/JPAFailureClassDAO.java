/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.dao.jpa;

import java.io.File;
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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
	
	@Override
	public Collection<FailureTable> getAllTableRows() {
		Query query = em.createQuery("from FailureClass");
		List<FailureTable> failureClasses = query.getResultList();
		
		return failureClasses;
	}
	
	@Override
	public Collection<Integer> getFailureClassCodes() {
		Query query = em.createQuery("select f.failureClass from FailureClass f GROUP BY f.failureClass");
		List<Integer> failureClassIds = query.getResultList();
		
		return failureClassIds;
	}
	
	@Override
    public void populateTable(File jsonFile) {
           
        JSONParser parser = new JSONParser();
 
        try {
 
            Object obj = parser.parse(new FileReader(jsonFile));
            
            JSONArray rows = (JSONArray) obj;
            
            Iterator<?> iterator = rows.iterator();
            
            while (iterator.hasNext()) {
                JSONObject failureClass = (JSONObject) iterator.next();
                FailureClass object = new FailureClass(Integer.parseInt(failureClass.get("Failure Class").toString()), failureClass.get("Description").toString());
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