package com.fiap.tech_challenge.interfaces.dto.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.tech_challenge.interfaces.dto.user.UserOutputDto;

public record CustomerOutputDto(
        Long id,
        String document,
        @JsonProperty("user")UserOutputDto userOutputDto
        ) {
}
