package com.fiap.tech_challenge.core.usecase.usertype;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.repository.UserTypeRepository;

import java.util.List;

public class ListAllUserTypeUseCase {
    private final UserTypeRepository userTypeRepository;

    public ListAllUserTypeUseCase(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    public List<UserType> execute() {
        return userTypeRepository.listAll();
    }
}
