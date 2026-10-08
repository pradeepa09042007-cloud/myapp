package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserRepository implements UserRepository {
    @Override
    public boolean emailExists(String email) {
        String sql = "SELECT id FROM users WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (name, phone_number, email, password_hash) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.name);
            stmt.setString(2, user.phone);
            stmt.setString(3, user.email);
            stmt.setString(4, user.password); // This is already securely hashed
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error saving user");
        }
    }
}
