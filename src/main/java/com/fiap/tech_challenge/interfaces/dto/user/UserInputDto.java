package com.fiap.tech_challenge.interfaces.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.tech_challenge.interfaces.dto.AddressDto;
import com.fiap.tech_challenge.interfaces.dto.UserTypeDto;

public record UserInputDto(
        String name,
        String email,
        String username,
        String password,
        UserTypeDto userType,
        @JsonProperty("address")AddressDto address
) {
}
