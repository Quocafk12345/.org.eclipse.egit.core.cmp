package com.todoApp.config;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final Logger logger = LoggerFactory.getLogger(DBConnection.class);
    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=toDoApp;encrypt=true;trustServerCertificate=true";
    private static final String user = "sa";
    private static final String pass = "Quocvjp12345@";
    private static DBConnection instance;
    private Connection connection;
    private DBConnection(){
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url,user,pass);
            logger.info("Connected to database");
        }catch (ClassNotFoundException e){
            logger.error("SQL Server JDBC Driver not found.", e);
        }catch (SQLException e){
            logger.error("SQL Server JDBC Driver not found.", e);
        }
    }
    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }
    public Connection getConnection() {
        return connection;
    }

}