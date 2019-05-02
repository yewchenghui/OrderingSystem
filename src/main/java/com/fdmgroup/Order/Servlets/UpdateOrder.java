package com.fdmgroup.Order.Servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.DAO.OrderDAOJpa;
import com.fdmgroup.StockOrder.Order;

public class UpdateOrder extends HttpServlet {
	Logger logger = LogManager.getLogger(UpdateOrder.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("ModifyOrder.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManagerFactory eMF = (EntityManagerFactory) this.getServletContext().getAttribute("emf");
		OrderDAOJpa orderDAO = new OrderDAOJpa(eMF);
		
		Order updatedOrder = new Order();
		updatedOrder.setOrderId(Integer.parseInt(req.getParameter("orderId")));
		updatedOrder = orderDAO.getOrder(updatedOrder);
		
		updatedOrder.setStoreName(req.getParameter("storeName"));
		updatedOrder.setCustomerName(req.getParameter("customerName"));
		updatedOrder.setCustomerPhoneNumber(Integer.parseInt(req.getParameter("customerPhoneNumber")));
		updatedOrder.setCustomerAddress(req.getParameter("customerAddress"));
		int state = Integer.parseInt(req.getParameter("orderState"));
		updatedOrder.setOrderState(state);
		updatedOrder.setRiderName(req.getParameter("riderName"));
		if (state == 2) {
			String orderCompletedDate = LocalDate.now().toString();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			String orderCompletedTime = LocalDateTime.now().format(formatter);
			updatedOrder.setOrderCompletedDate(orderCompletedDate);
			updatedOrder.setOrderCompletedTime(orderCompletedTime);
			logger.trace("Order is marked completed.");
		}
		orderDAO.updateOrder(updatedOrder);
		logger.trace("Order is updated into DB.");
		
		req.setAttribute("order", updatedOrder);
		req.getRequestDispatcher("./WEB-INF/UpdateOrderSuccess.jsp").forward(req, resp);
	}
}
