package com.fiap.tech_challenge.interfaces.dto;

import java.util.Date;

public record UserOutputDto(
        Long id,
        String name,
        String email,
        String username,
        UserTypeDto userType,
        AddressDto address,
        Date lastUpdateDate,
        Boolean activate
) {
}
