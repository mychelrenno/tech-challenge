package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.domain.Owner;
import com.fiap.tech_challenge.core.domain.restaurant.Restaurant;
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
                        dto.ownerId()
                ))
                .toList();

        return new Owner(
             null,
                ownerInputDto.document(),
                restaurantList,
                UserMapper.convertDtoToEntity(ownerInputDto.user())
        );
    }
}
