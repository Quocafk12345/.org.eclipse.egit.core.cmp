package com.todoApp.Connect;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
	private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=toDoApp;encrypt=true;trustServerCertificate=true";
	private static final String user = "sa";
	private static final String pass = "123";
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url,user,pass);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("Not found JDBC Driver!");
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error connect to DB");
		}
		return conn;
	}
}
