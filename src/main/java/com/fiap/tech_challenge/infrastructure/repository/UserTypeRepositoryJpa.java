package com.fiap.tech_challenge.infrastructure.repository;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.repository.UserTypeRepository;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaUserType;
import com.fiap.tech_challenge.interfaces.mapper.UserTypeMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserTypeRepositoryJpa implements UserTypeRepository {

    private final SpringDataJpaUserType springDataJpaUserType;

    public UserTypeRepositoryJpa(SpringDataJpaUserType springDataJpaUserType) {
        this.springDataJpaUserType = springDataJpaUserType;
    }

    @Override
    public UserType save(UserType userType) {
        var userTypeJpa = UserTypeMapper.convertDomainToJpa(userType);
        var _userTypeJpa = springDataJpaUserType.save(userTypeJpa);
        return UserTypeMapper.convertJpaToDomain(_userTypeJpa);
    }

    @Override
    public List<UserType> listAll() {
        var userTypeJpaList = springDataJpaUserType.findAll();
        return UserTypeMapper.convertJpaToDomain(userTypeJpaList);
    }

    @Override
    public UserType update(UserType userType) {
        var userTypeJpa = UserTypeMapper.convertDomainToJpa(userType);
        userTypeJpa = springDataJpaUserType.save(userTypeJpa);
        userType = UserTypeMapper.convertJpaToDomain(userTypeJpa);
        return userType;
    }

    @Override
    public void delete(Long id) {
        springDataJpaUserType.deleteById(id);
    }

    @Override
    public UserType findByName(UserType userType) {
        var userTypeJpa = springDataJpaUserType.findByName(userType.getName());
        return UserTypeMapper.convertJpaToDomain(userTypeJpa);
    }

    @Override
    public UserType findById(UserType userType) {
        var userTypeJpa = springDataJpaUserType.findById(userType.getId());
        if (userTypeJpa.isPresent()) {
            var _userType = UserTypeMapper.convertJpaToDomain(userTypeJpa.get());
            return _userType;
        }
        return null;
    }

}
