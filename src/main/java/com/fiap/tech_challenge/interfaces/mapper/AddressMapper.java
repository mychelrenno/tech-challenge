package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.infrastructure.entity.AddressJpa;
import com.fiap.tech_challenge.interfaces.dto.AddressDto;
import com.fiap.tech_challenge.interfaces.dto.UserTypeDto;

public class AddressMapper {

    public static Address convertDtoToEntity(AddressDto addressDto) {
        return new Address(
                addressDto.postalCode(),
                addressDto.street(),
                addressDto.additionalDetails(),
                addressDto.city(),
                addressDto.country()
        );
    }

    public static AddressJpa convertEntityToJpa(Address address) {
        return new AddressJpa(
                address.getPostalCode(),
                address.getStreet(),
                address.getAdditionalDetails(),
                address.getCity(),
                address.getCountry()
        );
    }

    public static Address convertJpaToEntity(AddressJpa addressJpa) {
        return new Address(
                addressJpa.getPostalCode(),
                addressJpa.getStreet(),
                addressJpa.getAdditionalDetails(),
                addressJpa.getCity(),
                addressJpa.getCountry()
        );
    }
}
