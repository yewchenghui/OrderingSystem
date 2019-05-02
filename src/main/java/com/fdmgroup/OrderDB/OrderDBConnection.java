package com.fdmgroup.OrderDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderDBConnection {
	private static String db_url;
	private static String db_user;
	private static String db_password;
	private static Connection conn;
	private final static Logger orderDBConnectionlog = LogManager.getLogger(OrderDBConnection.class);
	
	private OrderDBConnection() {
		db_url = "jdbc:oracle:thin:@localhost:1521:XE";
		db_user = "Trainee1";
		db_password = "!QAZSE4";
		setupConnection();
	}
	
	private static class OrderDBConnectionManager {
		private final static OrderDBConnection instance = new OrderDBConnection();
	}
	
	public static OrderDBConnection getInstance() {
		try {
			return OrderDBConnectionManager.instance;
		} catch (ExceptionInInitializerError ex) {
			orderDBConnectionlog.error("Unable to get Instance", ex);
			return null;
		}
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	/* Private method charge to set the connection statement*/
	private static void setupConnection() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			String url = "" + db_url;
			conn = DriverManager.getConnection(url, db_user, db_password);
		} catch (SQLException ex) {
			orderDBConnectionlog.error("Unable to set connection", ex);
		}
	}
	
	public Statement getStatement() {
		try {
			return conn.createStatement();
		} catch (SQLException ex) {
			orderDBConnectionlog.error("Unable to create statement", ex);
			return null;
		}
	}
}
