package com.fiap.tech_challenge.core.usecase.usertype;

import com.fiap.tech_challenge.core.repository.UserTypeRepository;

public class DeleteUserTypeUseCase {
    private final UserTypeRepository userTypeRepository;

    public DeleteUserTypeUseCase(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    public void execute(Long id) {
        userTypeRepository.delete(id);
    }
}
