package com.fiap.tech_challenge.interfaces.dto.owner;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.tech_challenge.interfaces.dto.RestaurantOutputDto;
import com.fiap.tech_challenge.interfaces.dto.user.UserOutputDto;

import java.util.List;

public record OwnerOutputDto(
        Long id,
        String document,
        @JsonProperty("restaurants")List<RestaurantOutputDto> restaurants,
        @JsonProperty("user")UserOutputDto user
) {
}
