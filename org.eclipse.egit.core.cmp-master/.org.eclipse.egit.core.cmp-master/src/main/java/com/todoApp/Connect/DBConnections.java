package com.todoApp.Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnections {
    private static final String url = "jdbc:sqlserver://localhost:1433; databaseName = ToDoApp";
    private static final String user = "sa";
    private static final String password = "Quocvjp12345@";
    private static Connection connection;

    private DBConnections(){}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.sqlserverdriver");
                connection = DriverManager.getConnection(url, user, password);
            }catch (ClassNotFoundException e) {
                System.err.println("couldn't find SQL Driver");
                e.printStackTrace();
            }catch (SQLException e){
                System.err.println("couldn't connect to DB");
            }
        }
        return connection;
    }
}
