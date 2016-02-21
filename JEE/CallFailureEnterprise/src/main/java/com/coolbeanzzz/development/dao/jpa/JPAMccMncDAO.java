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

import com.coolbeanzzz.development.dao.MccMncDAO;
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
	
	
	public Collection<MccMnc> getAllMccMncs() {
		Query query = em.createQuery("from MccMnc");
		List<MccMnc> mccMncs = query.getResultList();
		
		return mccMncs;
	}
}
