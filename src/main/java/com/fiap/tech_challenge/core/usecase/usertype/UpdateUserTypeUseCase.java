package com.fiap.tech_challenge.core.usecase.usertype;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.repository.UserTypeRepository;

import java.util.List;

public class UpdateUserTypeUseCase {
    private final UserTypeRepository userTypeRepository;

    public UpdateUserTypeUseCase(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    public UserType execute(UserType userType) {
        return userTypeRepository.update(userType);
    }
}
