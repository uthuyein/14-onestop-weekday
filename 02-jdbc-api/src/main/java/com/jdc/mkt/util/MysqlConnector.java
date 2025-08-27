package com.jdc.mkt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@SuppressWarnings("unused")
public final class MysqlConnector {
	
	private static final String URL = "jdbc:mysql://localhost:3306/testDb";
	private static final String USER = "root";
	private static final String PASSWORD = "admin";
	private static final String  member = "?user=root&password=admin";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);

//      return DriverManager.getConnection(URL + member);
		
//		Properties p = new Properties();
//		p.put("user", "root");
//		p.put("password", "admin");
//		return DriverManager.getConnection(URL, p);
	}
	

	
}
