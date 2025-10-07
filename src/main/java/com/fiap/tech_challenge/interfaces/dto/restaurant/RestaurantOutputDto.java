package com.fiap.tech_challenge.interfaces.dto;

import com.fiap.tech_challenge.interfaces.dto.owner.OwnerOutputDto;

public record RestaurantOutputDto(
    Long id,
    String name,
    AddressDto address,
    String cuisineType,
    String openingHours,
    OwnerOutputDto ownerId
) {}
