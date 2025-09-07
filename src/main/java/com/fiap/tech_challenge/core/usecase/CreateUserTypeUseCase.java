package com.fiap.tech_challenge.core.usecase;

import com.fiap.tech_challenge.core.entity.UserType;
import com.fiap.tech_challenge.core.repository.UserTypeRepository;

public class CreateUserTypeUseCase {

    private final UserTypeRepository userTypeRepository;

    public CreateUserTypeUseCase(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    public UserType execute(UserType userType) {
        var _userType = userTypeRepository.save(userType);
        return _userType;
    }
}
