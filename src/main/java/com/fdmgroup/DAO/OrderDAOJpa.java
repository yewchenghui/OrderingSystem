package com.fdmgroup.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.StockOrder.Order;
import com.fdmgroup.StockOrder.StockOrder;

public class OrderDAOJpa implements StockOrder {
	private EntityManagerFactory emf;
	private static final Logger logger = LogManager.getLogger(OrderDAOJpa.class);

	public OrderDAOJpa(EntityManagerFactory emfPassed) {
		this.emf = emfPassed;
	}

	@Override
	public void addOrder(Order order) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(order);
		em.getTransaction().commit();
		em.close();
		logger.info("Completed adding item: " + order.toString());
	}

	@Override
	public Order getOrder(Order order) {
		EntityManager em = emf.createEntityManager();
		Order foundOrder = em.find(Order.class, order.getOrderId());
		em.close();
		logger.info("Order found: " + foundOrder.toString());
		return foundOrder;
	}

	public List<Order> getOrderById(int orderCode) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o WHERE o.orderId=:orderId", Order.class);
		query.setParameter("orderId", orderCode);
		List<Order> foundOrder = query.getResultList();
		em.close();
		logger.info("Order found: " + foundOrder.toString());
		return foundOrder;
	}

	public List<Order> getOrderByStore(String storeName) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o WHERE o.storeName=:name ORDER BY orderId ASC",
				Order.class);
		query.setParameter("name", storeName);
		List<Order> foundOrder = query.getResultList();
		em.close();
		logger.info("Order found: " + foundOrder.toString());
		return foundOrder;
	}

	public List<Order> getOrderByState(int orderState, boolean inclusive) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Order> query = null;
		if (inclusive) {
			query = em.createQuery("SELECT o FROM Order o WHERE o.orderState < :state ORDER BY orderId ASC",
					Order.class);
		} else {
			query = em.createQuery("SELECT o FROM Order o WHERE o.orderState = :state ORDER BY orderId ASC",
					Order.class);
		}
		query.setParameter("state", orderState);
		List<Order> foundOrder = query.getResultList();
		em.close();
		logger.info("Orders found: " + foundOrder.size() + " for State.");
		return foundOrder;
	}

	public List<Order> getOrderByDate(String orderDate) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Order> query = null;
		query = em.createQuery("SELECT o FROM Order o WHERE o.orderCreatedDate=:date ORDER BY orderId ASC",
				Order.class);
		query.setParameter("date", orderDate);
		List<Order> foundOrder = query.getResultList();
		em.close();
		logger.info("Orders found: " + foundOrder.size() + " for " + orderDate + ".");
		return foundOrder;
	}

	@Override
	public void removeOrder(Order order) {
		EntityManager em = emf.createEntityManager();
		Order foundOrder = em.find(Order.class, order.getOrderId());
		em.getTransaction().begin();
		em.remove(foundOrder);
		em.getTransaction().commit();
		em.close();
		logger.info("Order removed: " + foundOrder.toString());
	}

	public void removeOrderById(int orderCode) {
		EntityManager em = emf.createEntityManager();
		Order foundOrder = em.find(Order.class, orderCode);
		em.getTransaction().begin();
		em.remove(foundOrder);
		em.getTransaction().commit();
		em.close();
		logger.info("Order removed: " + foundOrder.toString());
	}

	@Override
	public void updateOrder(Order order) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(order);
		em.getTransaction().commit();
		em.close();
		logger.info("Order updated: " + order.toString());
	}

	@Override
	public List<Order> listAllOrder() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o ORDER BY orderId ASC", Order.class);
		List<Order> allBooks = query.getResultList();
		em.close();
		logger.info("List of Orders returned: " + allBooks.size());
		return allBooks;
	}
}
