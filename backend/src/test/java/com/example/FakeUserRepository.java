package com.example;

import java.util.ArrayList;
import java.util.List;

public class FakeUserRepository implements UserRepository {
    private List<User> users = new ArrayList<>();

    @Override
    public boolean emailExists(String email) {
        return users.stream().anyMatch(u -> u.email.equals(email));
    }

    @Override
    public void save(User user) {
        users.add(user);
    }
}
