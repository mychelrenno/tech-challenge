package com.fiap.tech_challenge.infrastructure.repository;

import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.core.exception.ResourceNotFoundException;
import com.fiap.tech_challenge.core.repository.UserRepository;
import com.fiap.tech_challenge.infrastructure.entity.UserJpa;
import com.fiap.tech_challenge.infrastructure.entity.UserTypeJpa;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaUser;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaUserType;
import com.fiap.tech_challenge.interfaces.dto.user.UserOutputDto;
import com.fiap.tech_challenge.interfaces.mapper.AddressMapper;
import com.fiap.tech_challenge.interfaces.mapper.UserMapper;
import com.fiap.tech_challenge.interfaces.mapper.UserTypeMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        UserTypeJpa userTypeJpa = UserTypeMapper.convertDomainToJpa(user.getUserType());
        userTypeJpa = springDataJpaUserType.findByName(userTypeJpa.getName());
        if(userTypeJpa!=null){
            userJpa.setUserTypeJpa(userTypeJpa);
        }
        var savedUserJpa = springDataJpaUser.save(userJpa);
        return UserMapper.convertJpaToEntity(savedUserJpa);
    }

    public Boolean changePassword(Long userId, String oldPassword, String newPassword) {
        Optional<UserJpa> user = springDataJpaUser.findById(userId);
        if(user.isPresent()){
            UserJpa foundUser = user.get();
            if(!foundUser.getActive()){
                throw new ResourceNotFoundException("User is not active.");
            }
            if (!oldPassword.matches(foundUser.getPassword())) {
                throw new IllegalArgumentException("Old password is incorrect.");
            }
            foundUser.setPassword(newPassword);
            foundUser.setLastUpdateDate(new Date());
            springDataJpaUser.save(foundUser);
            return true;
        }
        throw new ResourceNotFoundException("User not found.");
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

    @Override
    public User update(Long id, User user) {
        var userJpa = springDataJpaUser.findById(id);
        if(userJpa.isPresent()){
            UserJpa foundUserJpa = userJpa.get();
            // check if user type already registered
            UserTypeJpa userTypeJpa = foundUserJpa.getUserTypeJpa();
            userTypeJpa = springDataJpaUserType.findByName(user.getUserType().getName());
            if(userTypeJpa!=null){
                foundUserJpa.setUserTypeJpa(userTypeJpa);
            }
            // set user's new data
            foundUserJpa.setName(user.getName());
            foundUserJpa.setEmail(user.getEmail());
            foundUserJpa.setUsername(user.getUsername());
            foundUserJpa.setAddressJpa(AddressMapper.convertEntityToJpa(user.getAddress()));
            foundUserJpa.setLastUpdateDate(new Date());
            foundUserJpa.setActive(true);
            var savedUserJpa = springDataJpaUser.save(foundUserJpa);
            return UserMapper.convertJpaToEntity(savedUserJpa);
        }else{
            return null;
        }
    }

    @Override
    public Boolean delete(Long id) {
        var userJpa = springDataJpaUser.findById(id);
        if(userJpa.isPresent()){
            UserJpa foundUserJpa = userJpa.get();
            foundUserJpa.setActive(false);

            springDataJpaUser.save(foundUserJpa);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<User> findByActiveTrue() {
        List<UserOutputDto> listUsersActive = springDataJpaUser
                .findByActiveTrue()
                .stream()
                .map(user -> new UserOutputDto(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getUsername(),
                        UserTypeMapper.convertJpaToDto(user.getUserTypeJpa()),
                        AddressMapper.convertJpaToDto(user.getAddressJpa()),
                        user.getLastUpdateDate(),
                        user.getActive()
                ))
                .toList();

        return listUsersActive
                .stream()
                .map(UserMapper::convertDtoToEntity)
                .toList();
    }
}
