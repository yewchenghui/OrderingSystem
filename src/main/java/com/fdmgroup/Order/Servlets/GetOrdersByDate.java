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

public class GetOrdersByDate extends HttpServlet {
	Logger logger = LogManager.getLogger(GetOrdersByDate.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("GetDate.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManagerFactory eMF = (EntityManagerFactory) this.getServletContext().getAttribute("emf");
		OrderDAOJpa orderDAO = new OrderDAOJpa(eMF);
		
		List<Order> ordersList = orderDAO.getOrderByDate(req.getParameter("orderDate"));
		logger.trace("List of orders size " + ordersList.size() + " obtained.");
		req.setAttribute("orders", ordersList);
		
		req.getRequestDispatcher("./WEB-INF/GetAllOrders.jsp").forward(req, resp);
	}
}
