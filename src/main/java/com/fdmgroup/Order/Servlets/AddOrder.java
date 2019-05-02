package com.fdmgroup.Order.Servlets;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.DAO.OrderDAOJpa;
import com.fdmgroup.StockOrder.Order;

import javassist.expr.NewArray;

public class AddOrder extends HttpServlet {

	Logger logger = LogManager.getLogger(AddOrder.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("AddOrder.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManagerFactory eMF = (EntityManagerFactory) this.getServletContext().getAttribute("emf");
		OrderDAOJpa orderDao = new OrderDAOJpa(eMF);

		Order newOrder = new Order();
		newOrder.setStoreName(req.getParameter("storeName"));
		newOrder.setCustomerName(req.getParameter("customerName"));
		newOrder.setCustomerPhoneNumber(Integer.parseInt(req.getParameter("customerPhone")));
		newOrder.setCustomerAddress(req.getParameter("customerAddress"));
		newOrder.setOrderTotal(Double.parseDouble(req.getParameter("orderTotal")));
		newOrder.setCustomerPaymentType(Integer.parseInt(req.getParameter("customerPayment")));
		orderDao.addOrder(newOrder);
		logger.trace("New Order added.");
		
		req.setAttribute("order", newOrder);

		RequestDispatcher requestDispatcher = req.getRequestDispatcher("./WEB-INF/AddOrderSuccess.jsp");
		requestDispatcher.forward(req, resp);
	}
}
