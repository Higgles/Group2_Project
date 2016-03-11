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

import com.coolbeanzzz.development.dao.UsersDAO;
import com.coolbeanzzz.development.entities.Users;

@Default
@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class JPAUsersDAO implements UsersDAO{

static Logger logger = Logger.getLogger("JPAUserDAO");
	
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {
		logger.info("in JPAUserDAO.init");
		logger.info(em.toString());
	}
	
	@SuppressWarnings("unchecked")
	@Override	
	public Collection<Users> getAllUsers() {
		Query query = em.createQuery("from Users");
		List<Users> users = query.getResultList();
		return users;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override	
	public void addUser(Users user) {
		Query query = em.createQuery("from Users");
		List<Users> users = query.getResultList();
		if (!users.contains(user)){
			em.persist(user);
		}        
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public Users removeUser(Users user) {
		Query query = em.createQuery("from Users");
		List<Users> users = query.getResultList();
		if (users.contains(user)){
			em.remove(user);	
			return user;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Users removeUser(int id) {
		Query query = em.createQuery("from Users");
		List<Users> users = query.getResultList();
		Users userById=null;
		for(Users user:users){
			if(user.getId()==id){
				userById=user;
			}
		}
		if (userById!=null){
			em.remove(userById);
			return userById;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateUserUsingId(int id) {
		Query query = em.createQuery("from Users");
		List<Users> users = query.getResultList();
		Users user=null;
		for(Users thisUser:users){
			if(thisUser.getId()==id){
				user=thisUser;
			}
		}
		em.merge(user);
	}

}