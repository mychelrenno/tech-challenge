package com.fiap.tech_challenge.interfaces.dto;

public record AddressDto(
        String postalCode,
        String street,
        String additionalDetails,
        String city,
        String country
) {
}
