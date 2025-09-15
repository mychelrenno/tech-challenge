package com.fiap.tech_challenge.infrastructure.repository;

import com.fiap.tech_challenge.core.domain.User;
import com.fiap.tech_challenge.core.repository.UserRepository;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaUser;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaUserType;
import com.fiap.tech_challenge.interfaces.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryJpa implements UserRepository {

    private final SpringDataJpaUser springDataJpaUser;
    private final SpringDataJpaUserType springDataJpaUserType;

    public UserRepositoryJpa(SpringDataJpaUser springDataJpaUser, SpringDataJpaUserType springDataJpaUserType) {
        this.springDataJpaUser = springDataJpaUser;
        this.springDataJpaUserType = springDataJpaUserType;
    }

    @Override
    public User save(User user) {
        var userJpa = UserMapper.convertEntityToJpa(user);
        // check if user type already registered
        var userTypeJpa = springDataJpaUserType.findByName(user.getUserType().getName());
        if(userTypeJpa!=null){
            userJpa.setUserTypeJpa(userTypeJpa);
        }
        var savedUserJpa = springDataJpaUser.save(userJpa);
        return UserMapper.convertJpaToEntity(savedUserJpa);
    }

    @Override
    public User findByEmail(String email) {
        var userJpa = springDataJpaUser.findByEmail(email);
        return userJpa.map(UserMapper::convertJpaToEntity).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        var userJpa = springDataJpaUser.findByUsername(username);
        return userJpa.map(UserMapper::convertJpaToEntity).orElse(null);
    }

    @Override
    public User findById(Long id) {
        var userJpa = springDataJpaUser.findById(id);
        return userJpa.map(UserMapper::convertJpaToEntity).orElse(null);
    }
}
