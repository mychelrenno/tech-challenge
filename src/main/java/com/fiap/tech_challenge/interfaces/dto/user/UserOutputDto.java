package com.fiap.tech_challenge.interfaces.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.tech_challenge.interfaces.dto.AddressDto;
import com.fiap.tech_challenge.interfaces.dto.UserTypeDto;
import java.util.Date;

public record UserOutputDto(
        Long id,
        String name,
        String email,
        String username,
        UserTypeDto userType,
        @JsonProperty("address")AddressDto address,
        Date lastUpdateDate,
        Boolean active
) {
}
