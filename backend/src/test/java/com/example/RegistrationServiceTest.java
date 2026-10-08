package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationServiceTest {

    private RegistrationService service;

    @BeforeEach
    public void setup() {
        // We will pass a simple FakeUserRepository so we don't need MySQL to run these tests
        service = new RegistrationService(new FakeUserRepository());
    }

    @Test
    public void testValidRegistration() {
        User user = new User("Alice", "1234567890", "alice@example.com", "secret123");
        String response = service.register(user);
        assertEquals("Success", response);
    }

    @Test
    public void testMissingNameRejected() {
        User user = new User("", "1234567890", "bob@example.com", "secret123");
        assertEquals("Name is required", service.register(user));
    }

    @Test
    public void testMissingEmailRejected() {
        User user = new User("Bob", "1234567890", "", "secret123");
        assertEquals("Email is required", service.register(user));
    }

    @Test
    public void testInvalidEmailRejected() {
        User user = new User("Bob", "1234567890", "not-an-email", "secret123");
        assertEquals("Invalid email format", service.register(user));
    }

    @Test
    public void testDuplicateEmailRejected() {
        User user1 = new User("Charlie", "123", "charlie@example.com", "pass");
        service.register(user1); // First time works

        User user2 = new User("Charlie Two", "456", "charlie@example.com", "pass2");
        assertEquals("Email already exists", service.register(user2)); // Second time fails
    }
}
