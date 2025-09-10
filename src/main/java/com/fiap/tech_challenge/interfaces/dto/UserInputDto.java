package com.fiap.tech_challenge.interfaces.dto;

public record UserInputDto(
        String name,
        String email,
        String username,
        String password,
        UserTypeDto userType,
        AddressDto address
) {
}
