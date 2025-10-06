package com.fiap.tech_challenge.interfaces.dto.user;

import com.fiap.tech_challenge.interfaces.dto.AddressDto;
import com.fiap.tech_challenge.interfaces.dto.UserTypeDto;

public record UserInputDto(
        String name,
        String email,
        String username,
        String password,
        UserTypeDto userType,
        AddressDto address
) {
}
