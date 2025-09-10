package com.fiap.tech_challenge.infrastructure.repository;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.repository.UserTypeRepository;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaUserType;
import com.fiap.tech_challenge.interfaces.mapper.UserTypeMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserTypeRepositoryJpa implements UserTypeRepository {

    private final SpringDataJpaUserType springDataJpaUserType;

    public UserTypeRepositoryJpa(SpringDataJpaUserType springDataJpaUserType) {
        this.springDataJpaUserType = springDataJpaUserType;
    }

    @Override
    public UserType save(UserType userType) {
        var userTypeJpa = UserTypeMapper.convertEntityToJpa(userType);
        var _userTypeJpa =springDataJpaUserType.save(userTypeJpa);
        var _userType = UserTypeMapper.convertJpaToEntity(_userTypeJpa);
        return _userType;
    }

}
