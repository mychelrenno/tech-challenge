package com.fiap.tech_challenge.core.usecase.usertype;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.exception.ResourceAlreadyExistsException;
import com.fiap.tech_challenge.core.repository.UserTypeRepository;

public class UpdateUserTypeUseCase {
    private final UserTypeRepository userTypeRepository;

    public UpdateUserTypeUseCase(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    public UserType execute(UserType userType) {
        var _userType = userTypeRepository.findByName(userType);
        if (_userType != null) {
            throw new ResourceAlreadyExistsException("UserType already exists");
        }
        return userTypeRepository.update(userType);
    }
}
