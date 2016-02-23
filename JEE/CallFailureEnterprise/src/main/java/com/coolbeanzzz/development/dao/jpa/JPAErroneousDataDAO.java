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

import com.coolbeanzzz.development.dao.ErroneousDataDAO;
import com.coolbeanzzz.development.entities.ErroneousData;

@Default
@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class JPAErroneousDataDAO implements ErroneousDataDAO {
	static Logger logger = Logger.getLogger("JPAErroneousDataDAO");
	
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {
		logger.info("in JPAErroneousDataDAO.init");
		logger.info(em.toString());
	}
	
	
	public Collection<ErroneousData> getAllErroneousData() {
		Query query = em.createQuery("from ErroneousData");
		List<ErroneousData> erroneousData = query.getResultList();
		
		return erroneousData;
	}
}
