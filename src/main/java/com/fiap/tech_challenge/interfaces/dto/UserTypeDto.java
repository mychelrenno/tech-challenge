package com.fiap.tech_challenge.interfaces.dto;

import com.fiap.tech_challenge.interfaces.dto.validation.group.Create;
import com.fiap.tech_challenge.interfaces.dto.validation.group.Update;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserTypeDto(
        @NotNull(message = "the identifier is required", groups = Update.class)
        Long id,
        @NotBlank(message = "the name is required", groups = {Create.class, Update.class})
        String name
) {}
