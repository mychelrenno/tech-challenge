package com.fiap.tech_challenge.interfaces.dto;

public record RestaurantOutputDto(
    Long id,
    String name,
    AddressDto address,
    String cuisineType,
    String openingHours,
    Long ownerId
) {}
