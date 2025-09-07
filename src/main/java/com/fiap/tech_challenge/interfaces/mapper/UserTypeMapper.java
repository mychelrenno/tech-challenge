package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.entity.UserType;
import com.fiap.tech_challenge.infrastructure.entity.UserTypeJpa;
import com.fiap.tech_challenge.interfaces.dto.UserTypeDto;
import org.apache.catalina.User;

public class UserTypeMapper {

    public static UserType convertDtoToEntity(UserTypeDto dto) {
        return new UserType(dto.name());
    }

    public static UserTypeJpa convertEntityToJpa(UserType userType) {
        return new UserTypeJpa(userType.getName());
    }

    public static UserType convertJpaToEntity(UserTypeJpa userTypeJpa) {
        return new UserType(userTypeJpa.getName());
    }
}
