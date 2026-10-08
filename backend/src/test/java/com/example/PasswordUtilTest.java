package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordUtilTest {

    @Test
    public void testPasswordIsHashed() {
        String plainPassword = "mySecretPassword123";
        String hash = PasswordUtil.hash(plainPassword);
        
        assertNotNull(hash);
        assertNotEquals(plainPassword, hash, "Hash should not be plain text!");
        assertTrue(hash.startsWith("$2a$"), "Should be a valid bcrypt hash");
    }

    @Test
    public void testPasswordVerification() {
        String plainPassword = "mySecretPassword123";
        String hash = PasswordUtil.hash(plainPassword);
        
        assertTrue(PasswordUtil.verify(plainPassword, hash));
        assertFalse(PasswordUtil.verify("wrongPassword", hash));
    }
}
