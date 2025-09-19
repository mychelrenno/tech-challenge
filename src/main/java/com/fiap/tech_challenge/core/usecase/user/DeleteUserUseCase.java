package com.fiap.tech_challenge.core.usecase.user;

import com.fiap.tech_challenge.core.repository.UserRepository;

public class DeleteUserUseCase {
    private final UserRepository userRepository;

    public DeleteUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method: Logic delete
    public boolean delete(Long userId){
        return userRepository.delete(userId);
    }
}
