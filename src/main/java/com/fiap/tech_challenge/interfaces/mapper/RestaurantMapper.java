package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.domain.restaurant.Restaurant;
import com.fiap.tech_challenge.infrastructure.entity.RestaurantJpa;
import com.fiap.tech_challenge.interfaces.dto.restaurant.RestaurantInputDto;
import com.fiap.tech_challenge.interfaces.dto.restaurant.RestaurantOutputDto;
import com.fiap.tech_challenge.interfaces.dto.restaurant.RestaurantOwnerOutputDto;

public class RestaurantMapper {
    public static RestaurantJpa convertEntityToJpa(Restaurant restaurant) {
        if (restaurant == null) return null;
        return new RestaurantJpa(
            restaurant.getId(),
            restaurant.getName(),
            AddressMapper.convertEntityToJpa(restaurant.getAddress()),
            restaurant.getCuisineType(),
            restaurant.getOpeningHours(),
            OwnerMapper.convertEntityToJpa(restaurant.getOwner())
        );
    }

    public static Restaurant convertJpaToEntity(RestaurantJpa restaurantJpa) {
        if (restaurantJpa == null) return null;
        return new Restaurant(
                restaurantJpa.getId(),
                restaurantJpa.getName(),
                AddressMapper.convertJpaToEntity(restaurantJpa.getAddressJpa()),
                restaurantJpa.getCuisineType(),
                restaurantJpa.getOpeningHours(),
                OwnerMapper.convertJpaToEntity(restaurantJpa.getOwner())
        );
    }

    public static Restaurant convertInputDtoToDomain(RestaurantInputDto dto) {
        if (dto == null) return null;
        return new Restaurant(
            dto.name(),
            AddressMapper.convertDtoToEntity(dto.address()),
            dto.cuisineType(),
            dto.openingHours(),
            OwnerMapper.convertInputDtoToDomain(dto.owner())
        );
    }

    public static RestaurantOutputDto convertEntitytoOutputDto(Restaurant restaurant) {
        if (restaurant == null) return null;
        return new RestaurantOutputDto(
            restaurant.getId(),
            restaurant.getName(),
            AddressMapper.convertEntityToDto(restaurant.getAddress()),
            restaurant.getCuisineType(),
            restaurant.getOpeningHours(),
            new RestaurantOwnerOutputDto(restaurant.getOwner().getId(), restaurant.getName(), restaurant.getOwner().getDocument())
        );
    }
}
