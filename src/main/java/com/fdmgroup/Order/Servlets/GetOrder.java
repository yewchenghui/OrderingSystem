package com.fdmgroup.Order.Servlets;

import java.io.IOException;
import java.util.ArrayList;
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

public class GetOrder extends HttpServlet {
	Logger logger = LogManager.getLogger(GetOrder.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("GetOrder.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManagerFactory eMF = (EntityManagerFactory) this.getServletContext().getAttribute("emf");
		OrderDAOJpa orderDAO = new OrderDAOJpa(eMF);
		
		List<Order> foundOrder = new ArrayList<Order>();
		String reqParam = req.getParameter("orderId");
		if (reqParam.equals("")) {
			reqParam = "-1";
		}
		if (orderDAO.getOrderById(Integer.parseInt(reqParam)).size() > 0) {
			foundOrder = orderDAO.getOrderById(Integer.parseInt(reqParam));
			logger.trace("Valid Order (Size: " + foundOrder.size() + ") obtained.");
		} else {
			foundOrder = null;
			logger.trace("Invalid Order (ID: null) entered.");
		}
		
		req.setAttribute("orders", foundOrder);
		req.getRequestDispatcher("./WEB-INF/GetAllOrders.jsp").forward(req, resp);
	}
}
