package com.fiap.tech_challenge.interfaces.dto.user;

import com.fiap.tech_challenge.interfaces.dto.AddressDto;
import com.fiap.tech_challenge.interfaces.dto.UserTypeDto;

public record UserOutputDto(
        Long id,
        String name,
        String email,
        String username,
        UserTypeDto userType,
        AddressDto address,
        Boolean active
) {
}
