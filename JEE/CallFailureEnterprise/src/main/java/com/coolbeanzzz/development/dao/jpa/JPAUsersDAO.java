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
	public Collection<Users> getAllUsers(int page, String searchTerm, int pageLimit) {
		
		Query query = em.createQuery("from Users us where us.username like :searchTerm");
		query.setParameter("searchTerm", "%"+searchTerm+"%");
		if(pageLimit != -1){
			int start = pageLimit * (page - 1);
			query.setFirstResult(start).setMaxResults(pageLimit);
		}
		List<Users> users = query.getResultList();
		return users;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override	
	public void addUser(Users user) {
		Query query = em.createQuery("from Users");
		List<Users> users = query.getResultList();
		if (!users.contains(user)){
			em.merge(user);
		}        
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public Users removeUser(String username) {
		logger.info(username);
		Query query = em.createQuery("from Users us where us.username=:username");
		query.setParameter("username", username);
		List<Users> users = query.getResultList();
		Users chosenUser=null;
		for(Users u:users){
			if(u.getUsername().equals(username)){
				chosenUser=u;
				break;
			}
		}
		em.remove(chosenUser);
		return chosenUser;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateUser(Users user) {
		Query query = em.createQuery("from Users");
		List<Users> users = query.getResultList();
		Users existinguser=null;
		for(Users thisUser:users){
			if(thisUser.getUsername().equals(user.getUsername())){
				existinguser=thisUser;
				user.setId(existinguser.getId());
				
			}
		}
		em.merge(user);
	}

}