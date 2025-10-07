package com.fiap.tech_challenge.interfaces.dto;

public record MenuItemDto(
        Long id,
        String name,
        String description,
        Double price,
        Boolean restaurantOnly,
        String imagePath
) {
}
