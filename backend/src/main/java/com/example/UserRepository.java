package com.example;

public interface UserRepository {
    boolean emailExists(String email);
    void save(User user);
}
