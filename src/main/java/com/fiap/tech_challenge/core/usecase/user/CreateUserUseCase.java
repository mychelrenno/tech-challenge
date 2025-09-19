package com.fiap.tech_challenge.core.usecase.user;

import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.core.repository.UserRepository;

public class CreateUserUseCase {
    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(User user) {
        validateUser(user);
        return userRepository.save(user);
    }

    public void validateUser(User user){
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (user.getEmail().isEmpty() || !user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email provided.");
        }
        if (user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        if(userRepository.findByEmail(user.getEmail())!=null){
            throw new IllegalArgumentException("E-mail already registered.");
        }
        if(userRepository.findByUsername(user.getUsername())!=null){
            throw new IllegalArgumentException("Username already registered.");
        }
    }
}
