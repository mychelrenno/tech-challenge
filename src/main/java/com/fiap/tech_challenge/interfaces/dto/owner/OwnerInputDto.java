package com.fiap.tech_challenge.interfaces.dto.owner;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.tech_challenge.interfaces.dto.restaurant.RestaurantInputDto;
import com.fiap.tech_challenge.interfaces.dto.user.UserInputDto;

import java.util.List;

public record OwnerInputDto(
        String document,
        @JsonProperty("restaurants")List<RestaurantInputDto> restaurants,
        @JsonProperty("user")UserInputDto user) {
}
