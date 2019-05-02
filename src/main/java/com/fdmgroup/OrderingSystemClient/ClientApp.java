package com.fdmgroup.OrderingSystemClient;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdmgroup.DAO.UsersDAOJpa;
import com.fdmgroup.StockOrder.Users;

public class ClientApp {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ordersystem");
		UsersDAOJpa userDAO = new UsersDAOJpa(emf);
		
		Users user = new Users();
		user.setUserName("alpha");
		user.setPassWord("chica");
		user.setRole(0);
		
		userDAO.addUser(user);
		
		System.out.println(userDAO.checkUserExist(user));
		
		List<String> newList = new ArrayList<String>();
		
		//OrderDAOJpa bookDao = new OrderDAOJpa(emf);
		//List<Order> allBooks = bookDao.listAllOrder();
		
		
		
	}
}
