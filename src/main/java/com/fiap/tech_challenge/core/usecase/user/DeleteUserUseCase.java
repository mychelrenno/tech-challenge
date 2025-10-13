package com.fiap.tech_challenge.core.usecase.user;

import com.fiap.tech_challenge.core.repository.UserRepository;

public class DeleteUserUseCase {
    private final UserRepository userRepository;

    public DeleteUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method: Logic delete
    public boolean delete(Long userId){
        if (userId == null) {
            throw new IllegalArgumentException("User's ID cannot be null.");
        }
        return userRepository.delete(userId);
    }
}
