package com.fiap.tech_challenge.interfaces.dto.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.tech_challenge.interfaces.dto.user.UserInputDto;

public record CustomerInputDto(
        String document,
        @JsonProperty("user")UserInputDto  userInputDto
) {
}
