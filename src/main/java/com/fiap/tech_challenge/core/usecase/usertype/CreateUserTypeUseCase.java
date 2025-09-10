package com.fiap.tech_challenge.core.usecase.usertype;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.repository.UserTypeRepository;

public class CreateUserTypeUseCase {
    private final UserTypeRepository userTypeRepository;

    public CreateUserTypeUseCase(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    public UserType execute(UserType userType) {
        return userTypeRepository.save(userType);
    }
}
