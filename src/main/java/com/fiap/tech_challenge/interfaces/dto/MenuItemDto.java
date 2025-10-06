package com.fiap.tech_challenge.interfaces.dto;

public record MenuItemDto(
        String name,
        String description,
        Double price,
        Boolean restaurantOnly,
        String imagePath
) {
}
