package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.domain.Owner;
import com.fiap.tech_challenge.core.domain.restaurant.Restaurant;
import com.fiap.tech_challenge.infrastructure.entity.OwnerJpa;
import com.fiap.tech_challenge.interfaces.dto.owner.OwnerInputDto;

import java.util.List;

public class OwnerMapper {
    public static Owner convertInputDtoToDomain(OwnerInputDto ownerInputDto){
        List<Restaurant> restaurantList = ownerInputDto.restaurants()
                .stream()
                .map(dto -> new Restaurant(
                        dto.name(),
                        AddressMapper.convertDtoToEntity(dto.address()),
                        dto.cuisineType(),
                        dto.openingHours(),
                        convertInputDtoToDomain(dto.owner())
                ))
                .toList();

        return new Owner(
             null,
                ownerInputDto.document(),
                restaurantList,
                UserMapper.convertDtoToEntity(ownerInputDto.user())
        );
    }

    public static OwnerJpa convertEntityToJpa(Owner owner) {
        if (owner == null) return null;

        OwnerJpa ownerJpa = new OwnerJpa();
        ownerJpa.setId(owner.getId());
        ownerJpa.setDocument(owner.getDocument());
        ownerJpa.setUser(UserMapper.convertEntityToJpa(owner.getUser()));
        ownerJpa.setRestaurants(
                owner.getRestaurants() != null ?
                        owner.getRestaurants().stream()
                                .map(RestaurantMapper::convertEntityToJpa)
                                .toList()
                        : null
        );
        return ownerJpa;
    }

    public static Owner convertJpaToEntity(OwnerJpa ownerJpa) {
        if (ownerJpa == null) return null;

        return new Owner(
            ownerJpa.getId(),
            ownerJpa.getDocument(),
            ownerJpa.getRestaurants() != null ?
                ownerJpa.getRestaurants().stream()
                    .map(RestaurantMapper::convertJpaToEntity)
                    .toList()
                : null,
            UserMapper.convertJpaToEntity(ownerJpa.getUser())
        );
    }
}
