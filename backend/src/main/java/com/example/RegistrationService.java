package com.example;

public class RegistrationService {
    private UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String register(User user) {
        if (user.name == null || user.name.trim().isEmpty()) {
            return "Name is required";
        }
        if (user.phone == null || user.phone.trim().isEmpty()) {
            return "Phone is required";
        }
        if (user.email == null || user.email.trim().isEmpty()) {
            return "Email is required";
        }
        if (user.password == null || user.password.trim().isEmpty()) {
            return "Password is required";
        }
        if (!user.email.contains("@")) {
            return "Invalid email format";
        }
        if (userRepository.emailExists(user.email)) {
            return "Email already exists";
        }

        // Hash the password securely before saving!
        user.password = PasswordUtil.hash(user.password);
        userRepository.save(user);

        return "Success";
    }
}
