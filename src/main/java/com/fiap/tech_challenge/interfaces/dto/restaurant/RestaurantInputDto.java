package com.fiap.tech_challenge.interfaces.dto.restaurant;

import com.fiap.tech_challenge.interfaces.dto.AddressDto;
import com.fiap.tech_challenge.interfaces.dto.owner.OwnerInputDto;

public record RestaurantInputDto(
    String name,
    AddressDto address,
    String cuisineType,
    String openingHours,
    Long ownerId
) {}
