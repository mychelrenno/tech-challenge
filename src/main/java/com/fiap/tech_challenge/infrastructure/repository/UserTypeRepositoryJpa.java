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
        var userTypeJpa = UserTypeMapper.convertEntityToJpa(userType);
        var _userTypeJpa = springDataJpaUserType.save(userTypeJpa);
        return UserTypeMapper.convertJpaToEntity(_userTypeJpa);
    }

    @Override
    public List<UserType> listAll() {
        var userTypeJpaList = springDataJpaUserType.findAll();
        var userTypeList = UserTypeMapper.convertJpaToEntityList(userTypeJpaList);
        return userTypeList;
    }

    @Override
    public UserType update(UserType userType) {
        var userTypeJpa = UserTypeMapper.convertEntityToJpa(userType);
        userTypeJpa = springDataJpaUserType.save(userTypeJpa);
        userType = UserTypeMapper.convertJpaToEntity(userTypeJpa);
        return userType;
    }

    @Override
    public void delete(Long id) {
        springDataJpaUserType.deleteById(id);
    }

}
