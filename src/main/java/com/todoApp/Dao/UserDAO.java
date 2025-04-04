package com.todoApp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.todoApp.Connect.DBConnection;

public class UserDAO {
	public boolean login(String userName, String password) {
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
		
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)){
			 stmt.setString(1, userName);
			 stmt.setString(2, password);
			 ResultSet rs = stmt.executeQuery();
			 
			 return rs.next();
		} catch (Exception e) {
			System.out.println("Error when login!");
			e.printStackTrace();
			// TODO: handle exception
		}
		return false;
	}
	
	public List<String> getAllUser(){
		List<String> users = new ArrayList<>();
		String sql = "Select * from users";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()){
			
			 while(rs.next()) {
				 users.add(rs.getString("username"));
			 }
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error retrieving users!");
			e.printStackTrace();
		}
		return users;
	}
}
