package com.todoApp.Dao;


import com.todoApp.Connect.DBConnections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDAO {
    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;

    public BaseDAO() {
        connection = DBConnections.getConnection();
    }

    protected PreparedStatement preparedStatement(String sql,Object... params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }
        return preparedStatement;
    }

    protected ResultSet executeQuery(String sql, Object... params) {
        try {
            PreparedStatement ps = preparedStatement(sql,params);
            return ps.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    protected int executeUpdate(String sql, Object... params) {
        try (PreparedStatement ps = preparedStatement(sql,  params)){
            return ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
    }
    protected void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
