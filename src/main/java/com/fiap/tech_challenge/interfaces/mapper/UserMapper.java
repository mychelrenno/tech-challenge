package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.domain.User;
import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.infrastructure.entity.AddressJpa;
import com.fiap.tech_challenge.infrastructure.entity.UserJpa;
import com.fiap.tech_challenge.infrastructure.entity.UserTypeJpa;
import com.fiap.tech_challenge.interfaces.dto.UserInputDto;

import java.util.Date;

public class UserMapper {

    public static User convertDtoToEntity(UserInputDto userInputDto) {
        return new User(
                userInputDto.name(),
                userInputDto.email(),
                userInputDto.username(),
                userInputDto.password(),
                UserTypeMapper.convertDtoToEntity(userInputDto.userType()),
                AddressMapper.convertDtoToEntity(userInputDto.address())
        );
    }

    public static UserJpa convertEntityToJpa(User user) {
        return new UserJpa(
                user.getName(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                UserTypeMapper.convertEntityToJpa(user.getUserType()),
                AddressMapper.convertEntityToJpa(user.getAddress()),
                new Date(),
                true
        );
    }

    public static User convertJpaToEntity(UserJpa userJpa) {
        return new User(
                userJpa.getName(),
                userJpa.getEmail(),
                userJpa.getUsername(),
                userJpa.getPassword(),
                UserTypeMapper.convertJpaToEntity(userJpa.getUserTypeJpa()),
                AddressMapper.convertJpaToEntity(userJpa.getAddressJpa())
        );
    }
}
