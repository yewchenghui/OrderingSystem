package com.fdmgroup.Order.Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.DAO.OrderDAOJpa;
import com.fdmgroup.StockOrder.Order;

public class ModifyOrder extends HttpServlet {
	Logger logger = LogManager.getLogger(ModifyOrder.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session.getAttribute("loggedin") == null) {
			req.getRequestDispatcher("./").forward(req, resp);
		} else {
			req.getRequestDispatcher("ModifyOrder.jsp").forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManagerFactory eMF = (EntityManagerFactory) this.getServletContext().getAttribute("emf");
		OrderDAOJpa orderDAO = new OrderDAOJpa(eMF);

		Order foundOrder = new Order();
		String reqParam = req.getParameter("orderId");
		if (reqParam.equals("")) {
			reqParam = "-1";
		}
		if (orderDAO.getOrderById(Integer.parseInt(reqParam)).size() > 0) {
			foundOrder.setOrderId(Integer.parseInt(reqParam));
			foundOrder = orderDAO.getOrder(foundOrder);
			logger.trace("Valid Order (ID: " + foundOrder.getOrderId() + ") obtained.");
		} else {
			foundOrder = null;
			logger.trace("Invalid Order (ID: null) entered.");
		}
		
		req.setAttribute("order", foundOrder);
		req.getRequestDispatcher("./WEB-INF/ModifyOrderResult.jsp").forward(req, resp);
	}
}
