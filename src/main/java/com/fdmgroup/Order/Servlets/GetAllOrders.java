package com.fdmgroup.Order.Servlets;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.DAO.OrderDAOJpa;
import com.fdmgroup.StockOrder.Order;

public class GetAllOrders extends HttpServlet {
	Logger logger = LogManager.getLogger(GetAllOrders.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManagerFactory eMF = (EntityManagerFactory) this.getServletContext().getAttribute("emf");
		OrderDAOJpa orderDAO = new OrderDAOJpa(eMF);

		List<Order> ordersList = orderDAO.listAllOrder();
		logger.trace("List of orders size " + ordersList.size() + " obtained.");
		req.setAttribute("orders", ordersList);
		
		req.getRequestDispatcher("./WEB-INF/GetAllOrders.jsp").forward(req, resp);
	}
}
