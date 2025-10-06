package com.fiap.tech_challenge.core.usecase.usertype;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.exception.ResourceNotFoundException;
import com.fiap.tech_challenge.core.repository.UserTypeRepository;

public class FindByIdUseCase {
    private final UserTypeRepository userTypeRepository;

    public FindByIdUseCase(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    public UserType execute(UserType userType) {
        var _userType = userTypeRepository.findById(userType);
        if (_userType == null) {
            throw new ResourceNotFoundException("UserType not found");
        }
        return _userType;
    }
}
