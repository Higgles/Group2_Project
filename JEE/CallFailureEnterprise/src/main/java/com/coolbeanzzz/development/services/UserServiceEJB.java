package com.coolbeanzzz.development.services;

import java.io.File;
import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.json.simple.JSONArray;

import com.coolbeanzzz.development.dao.ErroneousDataDAO;
import com.coolbeanzzz.development.entities.ErroneousData;

@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class UserServiceEJB implements UserService{

Logger logger = Logger.getLogger("ErroneousDataServiceEJB");
	
	@PersistenceContext
	private EntityManager em;
		
	@Inject
	private UserDAO dao;
	
	@Resource
	private SessionContext context;

	@PostConstruct
	public void init() {
		logger.info("in UserServiceEJB.init");
		logger.info(em.toString());
	}
	
	public void setDao(UserDAO dao) {
		this.dao = dao;
	}
	
	public Collection<ErroneousData> getCatalog() {
		return dao.getAllUsers();  //???
	}
	
	public void populateUserTable(File filename){
		dao.populateUserTable(filename);
	}
	
}
