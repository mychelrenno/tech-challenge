package com.fiap.tech_challenge.interfaces.dto.restaurant;

import com.fiap.tech_challenge.interfaces.dto.AddressDto;

public record RestaurantOutputDto(
    Long id,
    String name,
    AddressDto address,
    String cuisineType,
    String openingHours,
    RestaurantOwnerOutputDto restaurantOwner
) {}
