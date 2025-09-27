package com.fiap.tech_challenge.core.repository;

import com.fiap.tech_challenge.core.domain.UserType;

import java.util.List;

public interface UserTypeRepository {
    UserType save(UserType userType);
    List<UserType> listAll();
    UserType update(UserType userType);
    void delete(Long id);
    UserType findByName(UserType userType);
}
