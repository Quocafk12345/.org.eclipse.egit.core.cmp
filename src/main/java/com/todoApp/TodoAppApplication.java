package com.todoApp;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.todoApp.Dao.ColleaguesDAO;
import com.todoApp.Dao.TaskDAO;
import com.todoApp.Dao.UserDAO;

@SpringBootApplication
public class TodoAppApplication {

	public static void main(String[] args) throws Exception {
//		Connection conn = DBConnection.getConnection();
//		if(conn != null) {
//			System.out.println("Connect db success!");
//		}else {
//			System.out.println("Cannot connect to db!");
//		}
		TaskDAO taskDAO = new TaskDAO();
		UserDAO userDAO = new UserDAO();
		ColleaguesDAO colleaguesDAO = new ColleaguesDAO();
		Scanner scan = new Scanner(System.in);

		System.out.println("Please login to continuous");
		String loggedInUser = null;
		while (loggedInUser == null) {
			System.out.println("User name:");
			String userName = scan.nextLine();
			System.out.println("Password:");
			String password = scan.nextLine();

			if (userDAO.login(userName, password)) {
				System.out.println("Login success! welcome " + userName);
				loggedInUser = userName;
			} else {
				System.out.println("Wrong username or password. Please try again!");
			}
		}
		while (true) {
			System.out.println("\n=== TO-DO LIST MENU ===");
			System.out.println("1. Add new task");
			System.out.println("2. Display list task");
			System.out.println("3. Mark complete task");
			System.out.println("4. Delete task");
			System.out.println("5. Share task");// Add get users function to view list users
			System.out.println("6. Display task of you and colleague");
			System.out.println("7. Display all users");
			System.out.println("8. Exit");
			System.out.println("Choose:");

			int choice = scan.nextInt();
			scan.nextLine();

			switch (choice) {
			case 1:
				System.out.println("Enter task:");
				String taskTitle = scan.nextLine();
				System.out.println("Enter note:\n");
				String note = scan.nextLine();
				taskDAO.addTask(taskTitle, note);

			case 2:
				System.out.println(taskDAO.getAllTasks());
				break;
			case 3:
				System.out.println("Enter ID task need to complete:");
				long completedID = scan.nextLong();
				taskDAO.markTaskAsCompleted(completedID);
				break;

			case 4:
				System.out.println(taskDAO.getAllTasks());
				;
				System.out.println("Enter ID task need to delete:");
				long deleteId = scan.nextLong();
				taskDAO.deleteTask(deleteId);
				System.out.println(taskDAO.getAllTasks());
				;
				break;

			case 5:
				System.out.print("Enter ID task need to share: ");
				int taskId = scan.nextInt();
				scan.nextLine();
				System.out.print("Enter colleague name:");
				String colleagueName = scan.nextLine();

				taskDAO.shareTask(loggedInUser, taskId, colleagueName);
				break;

			case 6:
				List<String> sharedTasks = taskDAO.getSharedTasks(loggedInUser);
				if (sharedTasks.isEmpty()) {
					System.out.println("Don't have any shared tasks!");
				} else {
					System.out.println("List of shared tasks:");
					for (String taskInfo : sharedTasks) {
						System.out.println(taskInfo);
					}
				}
				break;
				
			case 7:
				List<String> allUsers = userDAO.getAllUser();
				if (allUsers.isEmpty()) {
					System.out.println("List users is empty!");
				} else {
					System.out.println("-- List users --");
					allUsers.forEach(System.out::println);
				}
				break;
			case 8:
				System.out.println("Exited.");
				System.exit(choice);
				scan.close();
			default:
				throw new IllegalArgumentException("Unexpected value. Let try again " + choice);
			}
		}
	}
}
