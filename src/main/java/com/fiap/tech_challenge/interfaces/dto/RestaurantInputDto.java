package com.fiap.tech_challenge.interfaces.dto;

public record RestaurantInputDto(
    String name,
    AddressDto address,
    String cuisineType,
    String openingHours,
    Long ownerId
) {}
