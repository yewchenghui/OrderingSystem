package com.fdmgroup.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.StockOrder.Users;

public class UsersDAOJpa {
	private EntityManagerFactory emf;
	private static final Logger logger = LogManager.getLogger(UsersDAOJpa.class);

	public UsersDAOJpa(EntityManagerFactory emfPassed) {
		this.emf = emfPassed;
	}
	
	public boolean checkUserExist(Users user) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT COUNT(u) from Users u WHERE UPPER(u.userName)=:userName");
		query.setParameter("userName", user.getUserName().toUpperCase());
		Long count = (long) query.getSingleResult();
		em.close();
	    return ( ( count.equals(0L) ) ? false : true );		
	}
	
	public void addUser(Users user) {
		if (checkUserExist(user) == false) { 
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			em.close();
			logger.info("Completed adding user: " + user.toString());
		} else {
			logger.info("Duplicate user found.");
		}
	}
	
	public Users getUserByUserName(String username) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Users> query = em.createQuery("SELECT u from Users u WHERE UPPER(u.userName)=:userName", Users.class);
		query.setParameter("userName", username.toUpperCase());
		Users user = (Users) query.getSingleResult();
		em.close();
		logger.info("User found.");
	    return user;
	}
	
	public Users getUser(Users user) {
		EntityManager em = emf.createEntityManager();
		Users foundUser = em.find(Users.class, user.getUserName());
		em.close();
		logger.info("User found: " + foundUser.toString());
		return foundUser;
	}
	
	public void updateUser(Users user) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		em.close();
		logger.info("User updated: " + user.toString());
	}
}
