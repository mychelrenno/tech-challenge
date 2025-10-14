package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.domain.Owner;
import com.fiap.tech_challenge.core.domain.restaurant.Restaurant;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.infrastructure.entity.OwnerJpa;
import com.fiap.tech_challenge.infrastructure.entity.RestaurantJpa;
import com.fiap.tech_challenge.interfaces.dto.owner.OwnerInputDto;

import java.util.ArrayList;
import java.util.List;

public class OwnerMapper {
    public static Owner convertInputDtoToDomain(OwnerInputDto ownerInputDto){

        List<Restaurant> restaurants = new ArrayList<>();

        ownerInputDto.restaurants().forEach(restaurant -> {
            restaurants.add(RestaurantMapper.convertInputDtoToDomainWithoutOwner(restaurant));
        });

        Owner owner = new Owner(
             null,
                ownerInputDto.document(),
                restaurants,
                UserMapper.convertDtoToEntity(ownerInputDto.user())
        );

        restaurants.forEach(restaurant -> restaurant.addOwner(owner));
        return owner;
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

        Owner owner1 = new Owner(
                ownerJpa.getId(),
                ownerJpa.getDocument(),
                null,
                UserMapper.convertJpaToEntity(ownerJpa.getUser())
        );

        List<Restaurant> restaurantList = new ArrayList<>();
        ownerJpa.getRestaurants().forEach(restaurantJpa ->
        {
            restaurantJpa.setOwner(ownerJpa);
            Restaurant restaurant = new Restaurant(
                    restaurantJpa.getId(),
                    restaurantJpa.getName(),
                    AddressMapper.convertJpaToEntity(restaurantJpa.getAddressJpa()),
                    restaurantJpa.getCuisineType(),
                    restaurantJpa.getOpeningHours(),
                    owner1
            );
            restaurantList.add(restaurant);
        });

        return new Owner(
            ownerJpa.getId(),
            ownerJpa.getDocument(), restaurantList,
            UserMapper.convertJpaToEntity(ownerJpa.getUser())
        );
    }
}
