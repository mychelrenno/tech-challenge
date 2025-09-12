package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.domain.Restaurant;
import com.fiap.tech_challenge.infrastructure.entity.RestaurantJpa;
import com.fiap.tech_challenge.interfaces.dto.RestaurantInputDto;
import com.fiap.tech_challenge.interfaces.dto.RestaurantOutputDto;

public class RestaurantMapper {
    public static RestaurantJpa toEntity(Restaurant restaurant) {
        if (restaurant == null) return null;
        return new RestaurantJpa(
            restaurant.getId(),
            restaurant.getName(),
            AddressMapper.convertEntityToJpa(restaurant.getAddress()),
            restaurant.getCuisineType(),
            restaurant.getOpeningHours(),
            restaurant.getOwnerId()
        );
    }

    public static Restaurant toDomain(RestaurantJpa entity) {
        if (entity == null) return null;
        return new Restaurant(
            entity.getId(),
            entity.getName(),
            AddressMapper.convertJpaToEntity(entity.getAddressJpa()),
            entity.getCuisineType(),
            entity.getOpeningHours(),
            entity.getOwnerId()
        );
    }

    public static Restaurant fromInputDto(RestaurantInputDto dto) {
        if (dto == null) return null;
        return new Restaurant(
            dto.name(),
            AddressMapper.convertDtoToEntity(dto.address()),
            dto.cuisineType(),
            dto.openingHours(),
            dto.ownerId()
        );
    }

    public static RestaurantOutputDto toOutputDto(Restaurant restaurant) {
        if (restaurant == null) return null;
        return new RestaurantOutputDto(
            restaurant.getId(),
            restaurant.getName(),
            AddressMapper.convertEntityToDto(restaurant.getAddress()),
            restaurant.getCuisineType(),
            restaurant.getOpeningHours(),
            restaurant.getOwnerId()
        );
    }
}
