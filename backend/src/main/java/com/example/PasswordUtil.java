package com.example;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Hashes a plain text password using bcrypt
    public static String hash(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Verifies a plain text password against a hashed one
    public static boolean verify(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
