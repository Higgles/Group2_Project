package com.coolbeanzzz.development.services;

import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.coolbeanzzz.development.dao.MccMncDAO;
import com.coolbeanzzz.development.entities.MccMnc;

@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class MccMncServiceEJB implements MccMncService {

	Logger logger = Logger.getLogger("MccMncServiceEJB");
	
	@PersistenceContext
	private EntityManager em;
		
	@Inject
	private MccMncDAO dao;
	
	@Resource
	private SessionContext context;

	@PostConstruct
	public void init() {
		logger.info("in MccMncServiceEJB.init");
		logger.info(em.toString());
	}
	
	public void setDao(MccMncDAO dao) {
		this.dao = dao;
	}
	
	public Collection<MccMnc> getCatalog() {
		return dao.getAllMccMncs();
	}
}