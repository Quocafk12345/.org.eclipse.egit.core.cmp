package com.todoApp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.todoApp.Connect.DBConnection;

public class TaskDAO {
	public void addTask(String task, String note) {
		String sql = "INSERT INTO tasks(task, completed,note) VALUES(?,0,?)";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, task);
			stmt.setString(2, note);
			stmt.executeUpdate();
			System.out.println("Add Work success " + task);
		} catch (SQLException e) {
			System.out.println("Error when add: " + task);
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	public boolean shareTask(String ownerUsername, int taskID, String colleagueUsername) {

		String sql = "INSERT INTO shared_tasks(task_id, owner_username, colleague_username) VALUES(?,?,?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, taskID);
			stmt.setString(2, ownerUsername);
			stmt.setString(3, colleagueUsername);
			stmt.executeUpdate();
			System.out.println("Task have been shared # " + taskID + "with" + colleagueUsername);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error when share task!");
			e.printStackTrace();
		}
		return false;
	}

	public List<String> getSharedTasks(String loggedInUser) {
	    List<String> sharedTasks = new ArrayList<>();
	    String sql = "SELECT task_id, owner_userName FROM shared_tasks WHERE colleague_userName = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, loggedInUser);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            int taskId = rs.getInt("task_id");
	            String owner = rs.getString("owner_userName");
	            sharedTasks.add("- Task #" + taskId + " (Shared by: " + owner + ")");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("❌ Error retrieving shared tasks.");
	    }
	    return sharedTasks;
	}


	public String getAllTasks() {
		StringBuilder result = new StringBuilder();
		String sql = "SELECT * FROM tasks";

		try {
			Connection conn = DBConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				result.append("ID:").append(rs.getLong("id")).append(" | Task: ").append(rs.getString("task"))
						.append(" | Completed: ").append(rs.getBoolean("completed") ? "✅" : "❌")
						.append(" | Created at: ").append(rs.getTimestamp("created_at")).append("\n");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println("Error when get task");
			e.printStackTrace();
		}
		return result.isEmpty() ? "Don't have any task" : result.toString();
	}

	public void markTaskAsCompleted(long id) {
		String sql = "UPDATE tasks SET completed = 1 WHERE id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, id);
			int rowsUpdated = stmt.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("Marked completed task have id:" + id);
			} else {
				System.out.println("Don't found task with id: " + id);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error when update task!");
			e.printStackTrace();
		}
	}

	public void deleteTask(long id) {

		System.out.println(getAllTasks());

		String sql = "DELETE FROM tasks WHERE id = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, id);
			int rowsDeleted = stmt.executeUpdate();

			if (rowsDeleted > 0) {
				System.out.println("Deleted task have id: " + id);
			} else {
				System.out.println("Don't found task with id: " + id);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error when delete task!");
			e.printStackTrace();
		}
	}
}
