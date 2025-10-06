package com.fiap.tech_challenge.core.usecase.user;

import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.core.repository.UserRepository;

public class UpdateUserUseCase {
    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(Long userId, User updatedUserData) {
        // Check data
        validateName(updatedUserData.getName());
        validateUsername(updatedUserData.getUsername(), userId);
        validateEmail(updatedUserData.getEmail(), userId);

        // Update data
        User existingUser = userRepository.findById(userId);
        if(existingUser!=null){
            return userRepository.update(userId, updatedUserData);
        }else{
            throw new IllegalArgumentException("Invalid user provided.");
        }
    }

    private void validateName(String name){
        if (name.isEmpty() || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be empty.");
        }
    }

    private void validateEmail(String email, Long currentUserId) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email provided.");
        }
        User user = userRepository.findByEmail(email);
        if (user != null && !user.getId().equals(currentUserId)) {
            throw new IllegalArgumentException("E-mail already registered.");
        }
    }

    private void validateUsername(String username, Long currentUserId) {
        User user = userRepository.findByUsername(username);
        if (user != null && !user.getId().equals(currentUserId)) {
            throw new IllegalArgumentException("Username already registered.");
        }
    }
}
