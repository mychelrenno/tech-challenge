package com.fiap.tech_challenge.core.usecase.user;

import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.core.repository.UserRepository;

import java.util.List;

public class ListAllActiveUsersUseCase {
    private final UserRepository userRepository;

    public ListAllActiveUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> listAllUsersActive(){
        return userRepository.findByActiveTrue();
    }
}
