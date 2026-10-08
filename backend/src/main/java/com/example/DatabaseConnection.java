package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String url = System.getenv("DB_HOST") != null ? 
            "jdbc:mysql://" + System.getenv("DB_HOST") + ":" + System.getenv("DB_PORT") + "/" + System.getenv("DB_NAME") :
            "jdbc:mysql://localhost:3306/myapp_db";
        String user = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "myapp_user";
        String password = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "myapp_password";

        
        return DriverManager.getConnection(url, user, password);
    }
}
