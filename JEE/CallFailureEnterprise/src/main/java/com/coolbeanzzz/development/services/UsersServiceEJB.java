package com.coolbeanzzz.development.services;

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

import com.coolbeanzzz.development.dao.UsersDAO;
import com.coolbeanzzz.development.entities.Users;

@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class UsersServiceEJB implements UsersService{

Logger logger = Logger.getLogger("UsersEJB");
	
	@PersistenceContext
	private EntityManager em;
		
	@Inject
	private UsersDAO dao;
	
	@Resource
	private SessionContext context;

	@PostConstruct
	public void init() {
		logger.info("in UsersServiceEJB.init");
		logger.info(em.toString());
	}
	
	/**
	 * Sets the attached DAO to the EJB service
	 * @param dao new dao
	 */
	public void setDao(UsersDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public Collection<Users> getAllUsers() {
		return dao.getAllUsers();  
	}
	
	@Override
	public void addUser(Users user){
		dao.addUser(user);		
	}

	@Override
	public Users removeUser(Users user) {
		return dao.removeUser(user);
				
	}

	@Override
	public Users removeUser(int id) {
		return dao.removeUser(id);
		
	}

	@Override
	public void updateUserUsingId(int id) {
		dao.updateUserUsingId(id);
		
	}
	
	
}
