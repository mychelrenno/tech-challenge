package com.fiap.tech_challenge.core.usecase.user;

import com.fiap.tech_challenge.core.domain.User;
import com.fiap.tech_challenge.core.repository.UserRepository;

public class CreateUserUseCase {
    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(User user) {
        return userRepository.save(user);
    }
}
