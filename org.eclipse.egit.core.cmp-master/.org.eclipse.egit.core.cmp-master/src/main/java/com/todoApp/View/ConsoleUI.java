package com.todoApp.View;

import com.todoApp.Dao.TaskDAO;
import com.todoApp.Dao.UserDAO;
import com.todoApp.Entities.SharedTask;
import com.todoApp.Entities.Tasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleUI.class);
    private final UserDAO userDAO = new UserDAO();
    private final TaskDAO taskDAO = new TaskDAO();
    private final Scanner scanner = new Scanner(System.in);
    private String loggedInUsername;

    public void start(){
        showLogin();
        showMenu();
    }

    private void showLogin() {
        System.out.println("Please login to continue");
        while(loggedInUsername == null){
            System.out.println("Username: ");
            String username = scanner.nextLine();
            System.out.println("Password: ");
            String password = scanner.nextLine();

            if(userDAO.login(username, password)){
                loggedInUsername = username;
                System.out.println("Logged in user: " + loggedInUsername);
            }
            else{
                System.out.println("Wrong password. Please try again.");
            }
        }
    }


    private void showMenu() {
        while (true) {
            System.out.println("\n=== TO-DO LIST MENU ===");
            System.out.println("1. Add new task");
            System.out.println("2. View all tasks");
            System.out.println("3. Mark task as completed");
            System.out.println("4. Delete task");
            System.out.println("5. Share task");
            System.out.println("6. View shared tasks");
            System.out.println("7. View all users");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            try {
                switch (choice) {
                    case 1 -> addTask();
                    case 2 -> viewTasks();
                    case 3 -> markTaskCompleted();
                    case 4 -> deleteTask();
                    case 5 -> shareTask();
                    case 6 -> viewSharedTasks();
                    case 7 -> viewAllUsers();
                    case 8 -> exitProgram();
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                logger.error("Error during menu processing", e);
                System.out.println("Something went wrong. Check logs for details.");
            }
        }
    }

    private void addTask() {
        System.out.println("Please enter a task name: ");
        String taskName = scanner.nextLine();
        System.out.println("Please enter a task description: ");
        String taskDescription = scanner.nextLine();
        taskDAO.addTask(taskName, taskDescription);
        System.out.println("Task added successfully.");
    }
    public void viewTasks() {
        TaskDAO taskDAO = new TaskDAO();
        List<Tasks> tasks = taskDAO.getAllTasks();

        if (tasks == null || tasks.isEmpty()) {
            System.out.println("❌ Không có công việc nào được tìm thấy.");
        } else {
            System.out.println("📋 Danh sách công việc:");
            for (Tasks task : tasks) {
                System.out.println("-----------------------------------");
                System.out.println("🆔 ID         : " + task.getId());
                System.out.println("📝 Công việc  : " + task.getTask());
                System.out.println("🗒️ Ghi chú    : " + task.getNote());
                System.out.println("✅ Hoàn thành : " + (task.isCompleted() ? "Đã hoàn thành" : "Chưa hoàn thành"));
                System.out.println("📅 Ngày tạo   : " + task.getCreated_at());
            }
            System.out.println("-----------------------------------");
        }
    }


    private void markTaskCompleted() {
        System.out.println("Please enter a task ID: ");
        long taskID = Long.parseLong(scanner.nextLine());
        taskDAO.markTaskAsCompleted(taskID);
        System.out.println("Task marked successfully.");
    }

    private void deleteTask() {
        System.out.println("Please enter a task ID: ");
        long taskID = Long.parseLong(scanner.nextLine());
        taskDAO.deleteTask(taskID);
        System.out.println("Task deleted successfully.");
    }

    private void shareTask() {
        System.out.println("Please enter a task ID: ");
        long taskID = Long.parseLong(scanner.nextLine());
        System.out.println("Please enter colleague name: ");
        String colleagueName = scanner.nextLine();
        taskDAO.shareTask(loggedInUsername, taskID, colleagueName);
        System.out.println("Task share successfully.");
    }
    private void viewSharedTasks() {
        // Giả sử bạn có lưu username người dùng đã login
        String currentUsername = this.loggedInUsername; // hoặc lấy từ Session, biến toàn cục, v.v.

        List<SharedTask> sharedTasks = taskDAO.getSharedTasks(currentUsername);

        if (sharedTasks.isEmpty()) {
            System.out.println("No shared tasks found.");
        } else {
            System.out.println("== Shared Tasks ==");
            for (SharedTask task : sharedTasks) {
                System.out.println("ID: " + task.getId());
                System.out.println("Task ID: " + task.getTaskId());
                System.out.println("Owner: " + task.getOwnerUsername());
                System.out.println("Colleague: " + task.getColleagueUsername());
                System.out.println("Shared At: " + task.getCreatedAt());
                System.out.println("--------------------");
            }
        }
    }




    private void viewAllUsers() {
        List<String> users = userDAO.getAllUser();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        }
        else {
            users.forEach(System.out::println);
        }
    }

    private void exitProgram() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
