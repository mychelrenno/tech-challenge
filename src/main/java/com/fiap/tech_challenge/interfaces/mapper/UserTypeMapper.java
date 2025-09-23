package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.infrastructure.entity.UserTypeJpa;
import com.fiap.tech_challenge.interfaces.dto.UserTypeDto;

import java.util.ArrayList;
import java.util.List;

public class UserTypeMapper {

    public static UserType convertDtoToEntity(UserTypeDto dto) {
        if (dto == null) {
            return null;
        }
        return new UserType(dto.id(), dto.name());
    }

    public static UserTypeJpa convertEntityToJpa(UserType userType) {
        if (userType == null) {
            return null;
        }
        return new UserTypeJpa(userType.getId(), userType.getName());
    }

    public static UserTypeDto convertJpaToDto(UserTypeJpa userTypeJpa){
        if (userTypeJpa == null) {
            return null;
        }
        return new UserTypeDto(userTypeJpa.getId(), userTypeJpa.getName());
    }

    public static UserTypeDto convertEntityToDto(UserType userType) {
        if (userType == null) {
            return null;
        }
        return new UserTypeDto(userType.getId(), userType.getName());
    }

    public static UserType convertJpaToEntity(UserTypeJpa userTypeJpa) {
        if (userTypeJpa == null) {
            return null;
        }
        return new UserType(userTypeJpa.getId(), userTypeJpa.getName());
    }

    public static List<UserType> convertJpaToEntityList(List<UserTypeJpa> userTypeJpaList) {
        var userTypeList = new ArrayList<UserType>();
        userTypeJpaList.forEach( u -> {
            var userType = new UserType(u.getId(), u.getName());
            userTypeList.add(userType);
        });
        return userTypeList;
    }

    public static List<UserTypeDto> convertEntityToDtoList(List<UserType> userType) {
        var userTypeDtoList = new ArrayList<UserTypeDto>();
        userType.forEach( u -> {
            var userTypeDto = new UserTypeDto(u.getId(), u.getName());
            userTypeDtoList.add(userTypeDto);
        });
        return userTypeDtoList;
    }
}
