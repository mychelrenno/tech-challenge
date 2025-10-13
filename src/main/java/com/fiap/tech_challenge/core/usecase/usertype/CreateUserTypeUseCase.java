package com.fiap.tech_challenge.core.usecase.usertype;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.exception.ResourceAlreadyExistsException;
import com.fiap.tech_challenge.core.repository.UserTypeRepository;

public class CreateUserTypeUseCase {
    private final UserTypeRepository userTypeRepository;

    public CreateUserTypeUseCase(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    public UserType execute(UserType userType) throws Exception {
        var _userType = userTypeRepository.findByName(userType);
        if (_userType != null) {
            throw new ResourceAlreadyExistsException("UserType already exists");
        }
        return userTypeRepository.save(userType);
    }
}
