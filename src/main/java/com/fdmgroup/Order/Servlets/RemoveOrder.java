package com.fdmgroup.Order.Servlets;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.DAO.OrderDAOJpa;
import com.fdmgroup.StockOrder.Order;

public class RemoveOrder extends HttpServlet {
	Logger logger = LogManager.getLogger(RemoveOrder.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session.getAttribute("loggedin") == null) {
			req.getRequestDispatcher("./").forward(req, resp);
		} else {
			req.getRequestDispatcher("RemoveOrder.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManagerFactory eMF = (EntityManagerFactory) this.getServletContext().getAttribute("emf");
		OrderDAOJpa orderDAO = new OrderDAOJpa(eMF);

		Order order = new Order();
		String reqParam = req.getParameter("orderId");
		if (orderDAO.getOrderById(Integer.parseInt(reqParam)).size() > 0) {
			order.setOrderId(Integer.parseInt(reqParam));
			order = orderDAO.getOrder(order);
			logger.trace("Valid Order (ID: " + order.getOrderId() + ") obtained.");
			orderDAO.removeOrderById(Integer.parseInt(req.getParameter("orderId")));
			logger.trace("Order (ID: " + order.getOrderId() + ") removed.");
		} else {
			order = null;
			logger.trace("Invalid Order (ID: null) entered.");
		}
		req.setAttribute("order", order);

		RequestDispatcher requestDispatcher = req.getRequestDispatcher("./WEB-INF/RemoveOrderSuccess.jsp");
		requestDispatcher.forward(req, resp);
	}

}
