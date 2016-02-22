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

import com.coolbeanzzz.development.dao.BaseDataDAO;
import com.coolbeanzzz.development.entities.BaseData;

@Default
@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class JPABaseDataDAO implements BaseDataDAO {
	static Logger logger = Logger.getLogger("JPABaseDataDAO");
	
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {
		logger.info("in JPABaseDataDAO.init");
		logger.info(em.toString());
	}
	
	
	public Collection<BaseData> getAllBaseData() {
		Query query = em.createQuery("from BaseData");
		List<BaseData> basedata = query.getResultList();
		
		return basedata;
	}
}
