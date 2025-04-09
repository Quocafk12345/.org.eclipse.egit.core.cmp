package com.todoApp.config;


public class SessionContext {

    private static String username;

    public static void setUsername(String username) {
        SessionContext.username = username;
    }

    public static String getUsername() {
        return username;
    }

    public static void clear() {
        SessionContext.username = null;
    }
}
