package com.dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/helpdesk?useSSL=false";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "password";
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Failed to load.");
		}
	}
	
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
