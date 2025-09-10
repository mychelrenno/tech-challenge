package com.fiap.tech_challenge.infrastructure.repository;

import com.fiap.tech_challenge.core.domain.User;
import com.fiap.tech_challenge.core.repository.UserRepository;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaUser;
import com.fiap.tech_challenge.interfaces.mapper.UserMapper;
import com.fiap.tech_challenge.interfaces.mapper.UserTypeMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryJpa implements UserRepository {

    private final SpringDataJpaUser springDataJpaUser;

    public UserRepositoryJpa(SpringDataJpaUser springDataJpaUser) {
        this.springDataJpaUser = springDataJpaUser;
    }

    @Override
    public User save(User user) {
        var userJpa = UserMapper.convertEntityToJpa(user);
        var savedUserJpa = springDataJpaUser.save(userJpa);
        User savedUser = UserMapper.convertJpaToEntity(savedUserJpa);
        return savedUser;
    }
}
