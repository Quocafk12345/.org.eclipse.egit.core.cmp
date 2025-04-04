package com.todoApp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.todoApp.Connect.DBConnection;

public class ColleaguesDAO {
	
	public boolean isUserExists(String userName) {
		String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)){
			 stmt.setString(1, userName);
			 ResultSet rs = stmt.executeQuery();
			 if(rs.next()) {
				 return rs.getInt(1)>0;
			 }
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error when check username!");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isColleague(String ownerUsername, String colleagueUsername) {
		String sql = "SELECT COUNT(*)FROM colleagues WHERE username = ?";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, colleagueUsername);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)>0;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error when check colleague");
			e.printStackTrace();
		}
		return false;
	}
	
	   private boolean isColleagueExists(String ownerUsername, String colleagueUsername) {
	        String sql = "SELECT COUNT(*) FROM colleagues WHERE owner_username = ? AND colleague_username = ?";
	        
	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	             
	            stmt.setString(1, ownerUsername);
	            stmt.setString(2, colleagueUsername);
	            ResultSet rs = stmt.executeQuery();
	            
	            if (rs.next()) {
	                return rs.getInt(1) > 0; // Nếu số lượng > 0, tức là đã tồn tại
	            }
	        } catch (SQLException e) {
	            System.err.println("❌ Lỗi khi kiểm tra đồng nghiệp!");
	            e.printStackTrace();
	        }
	        return false;
	    }
	   
	public boolean addColleague(String loggedInUser,String colleagueName) throws Exception{
		
		
		if(colleagueName.equals(loggedInUser)) {
			System.out.println("Cannot add yourself into list colleagues!");
			return false;
		}
		
		if(!isUserExists(colleagueName)) {
			System.out.println("Don't have this username");
			return false;
		}
		String sql = "INSERT INTO colleagues(userName) VALUES(?)";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, colleagueName);
			stmt.executeUpdate();
			System.out.println("Add colleague success:" + colleagueName);
		} catch (Exception e) {
			System.out.println("Error when add colleague!");
			e.printStackTrace();
			// TODO: handle exception
		}
		return false;
	}

	public List<String> getAllColleagues(){
		List<String> colleagues = new ArrayList<>();
		String sql = "SELECT * FROM Colleagues";
		try (Connection conn = DBConnection.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)){
			while(rs.next()) {
				colleagues.add(rs.getString("userName"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error when get list colleagues!");
		}
		
		return colleagues;
	}
	
	public void deleteColleage(String userName) {
		String sql = "DELETE FROM colleagues WHERE username = ?";
		
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)){
			 
			 stmt.setString(1, userName);
			 int rowsAffected = stmt.executeUpdate();
			 
			 if(rowsAffected > 0) {
				 System.out.println("Deleted colleague have id:"+userName);
			 }else {
				 System.out.println("Dont found colleague with id:"+userName);
			 }
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error when delete colleague!");
			e.printStackTrace();
		}
	}
}
