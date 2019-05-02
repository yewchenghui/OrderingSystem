package com.fdmgroup.Order.Servlets;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.DAO.UsersDAOJpa;
import com.fdmgroup.StockOrder.Users;

public class DoLogin extends HttpServlet {
	Logger logger = LogManager.getLogger(DoLogin.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("./").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManagerFactory eMF = (EntityManagerFactory) this.getServletContext().getAttribute("emf");
		UsersDAOJpa userDAO = new UsersDAOJpa(eMF);

		String webUserName = req.getParameter("defaultForm-email");
		String webPassWord = req.getParameter("defaultForm-pass");
		Users temp = new Users();
		temp.setUserName(webUserName);
		temp.setPassWord(webPassWord);

		if (userDAO.checkUserExist(temp)) {
			Users act = userDAO.getUserByUserName(webUserName);

			if (!act.getPassWord().equals(temp.getPassWord())) {
				logger.trace("Invalid password attempt for: " + temp.getUserName());
				req.getRequestDispatcher("./").forward(req, resp);
			} else {
				HttpSession session = req.getSession(true);
				logger.trace("Sucessful login: " + temp.getUserName());
				session.setAttribute("loggedin", webUserName);
				req.getRequestDispatcher("./").forward(req, resp);
			}
		} else {
			logger.trace("Invalid username.");
			req.getRequestDispatcher("./").forward(req, resp);
		}

	}

}
