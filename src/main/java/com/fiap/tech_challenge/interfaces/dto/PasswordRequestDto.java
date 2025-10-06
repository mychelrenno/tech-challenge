package com.fiap.tech_challenge.interfaces.dto;

public record PasswordRequestDto(Long id,
                                 String oldPassword,
                                 String newPassword) {
}
