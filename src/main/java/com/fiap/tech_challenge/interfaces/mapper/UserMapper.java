package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.infrastructure.entity.UserJpa;
import com.fiap.tech_challenge.interfaces.dto.user.UserInputDto;
import com.fiap.tech_challenge.interfaces.dto.user.UserOutputDto;

import java.util.Date;

public class UserMapper {

    public static User convertDtoToEntity(UserInputDto userInputDto) {
        if (userInputDto == null) return null;
        return new User(
                null,
                userInputDto.name(),
                userInputDto.email(),
                userInputDto.username(),
                userInputDto.password(),
                UserTypeMapper.convertDtoToDomain(userInputDto.userType()),
                AddressMapper.convertDtoToEntity(userInputDto.address()),
                new Date(),
                true
        );
    }

    public static User convertDtoToEntity(UserOutputDto userOutputDto) {
        if (userOutputDto == null) return null;
        return new User(
                userOutputDto.id(),
                userOutputDto.name(),
                userOutputDto.email(),
                userOutputDto.username(),
                null,
                UserTypeMapper.convertDtoToDomain(userOutputDto.userType()),
                AddressMapper.convertDtoToEntity(userOutputDto.address()),
                userOutputDto.lastUpdateDate(),
                userOutputDto.active()
        );
    }

    public static UserJpa convertEntityToJpa(User user) {
        if (user == null) return null;
        return new UserJpa(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                UserTypeMapper.convertDomainToJpa(user.getUserType()),
                AddressMapper.convertEntityToJpa(user.getAddress()),
                new Date(),
                true
        );
    }

    public static User convertJpaToEntity(UserJpa userJpa) {
        if (userJpa == null) return null;
        return new User(
                userJpa.getId(),
                userJpa.getName(),
                userJpa.getEmail(),
                userJpa.getUsername(),
                userJpa.getPassword(),
                UserTypeMapper.convertJpaToDomain(userJpa.getUserTypeJpa()),
                AddressMapper.convertJpaToEntity(userJpa.getAddressJpa()),
                userJpa.getLastUpdateDate(),
                userJpa.getActive()
        );
    }
}
