package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.infrastructure.entity.AddressJpa;
import com.fiap.tech_challenge.interfaces.dto.AddressDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressMapperTest {
    @Test
    void shouldConvertDtoToEntitySuccessfully() {
        AddressDto dto = new AddressDto("12345", "Main Street", "Apt 10", "New York", "USA");

        Address address = AddressMapper.convertDtoToEntity(dto);

        assertNotNull(address);
        assertEquals("12345", address.getPostalCode());
        assertEquals("Main Street", address.getStreet());
        assertEquals("Apt 10", address.getAdditionalDetails());
        assertEquals("New York", address.getCity());
        assertEquals("USA", address.getCountry());
    }

    @Test
    void shouldConvertEntityToJpaSuccessfully() {
        Address address = new Address("54321", "Broadway", "Suite 100", "Los Angeles", "USA");

        AddressJpa jpa = AddressMapper.convertEntityToJpa(address);

        assertNotNull(jpa);
        assertEquals("54321", jpa.getPostalCode());
        assertEquals("Broadway", jpa.getStreet());
        assertEquals("Suite 100", jpa.getAdditionalDetails());
        assertEquals("Los Angeles", jpa.getCity());
        assertEquals("USA", jpa.getCountry());
    }

    @Test
    void shouldConvertJpaToEntitySuccessfully() {
        AddressJpa jpa = new AddressJpa("99999", "Fifth Ave", "Penthouse", "Chicago", "USA");

        Address address = AddressMapper.convertJpaToEntity(jpa);

        assertNotNull(address);
        assertEquals("99999", address.getPostalCode());
        assertEquals("Fifth Ave", address.getStreet());
        assertEquals("Penthouse", address.getAdditionalDetails());
        assertEquals("Chicago", address.getCity());
        assertEquals("USA", address.getCountry());
    }

    @Test
    void shouldConvertEntityToDtoSuccessfully() {
        Address address = new Address("11223", "Elm Street", "House 5", "Miami", "USA");

        AddressDto dto = AddressMapper.convertEntityToDto(address);

        assertNotNull(dto);
        assertEquals("11223", dto.postalCode());
        assertEquals("Elm Street", dto.street());
        assertEquals("House 5", dto.additionalDetails());
        assertEquals("Miami", dto.city());
        assertEquals("USA", dto.country());
    }

    @Test
    void shouldConvertJpaToDtoSuccessfully() {
        AddressJpa jpa = new AddressJpa("77777", "Ocean Drive", "Floor 2", "San Diego", "USA");

        AddressDto dto = AddressMapper.convertJpaToDto(jpa);

        assertNotNull(dto);
        assertEquals("77777", dto.postalCode());
        assertEquals("Ocean Drive", dto.street());
        assertEquals("Floor 2", dto.additionalDetails());
        assertEquals("San Diego", dto.city());
        assertEquals("USA", dto.country());
    }

    @Test
    void shouldReturnNullWhenEntityIsNullInEntityToDto() {
        AddressDto dto = AddressMapper.convertEntityToDto(null);
        assertNull(dto);
    }

    @Test
    void shouldReturnNullWhenJpaIsNullInJpaToDto() {
        AddressDto dto = AddressMapper.convertJpaToDto(null);
        assertNull(dto);
    }

    @Test
    void shouldMaintainDataIntegrityAcrossConversions() {
        AddressDto originalDto = new AddressDto("55555", "Wall Street", "Office 8", "New York", "USA");

        Address entity = AddressMapper.convertDtoToEntity(originalDto);
        AddressJpa jpa = AddressMapper.convertEntityToJpa(entity);
        Address entityAgain = AddressMapper.convertJpaToEntity(jpa);
        AddressDto dtoAgain = AddressMapper.convertEntityToDto(entityAgain);

        assertEquals(originalDto.postalCode(), dtoAgain.postalCode());
        assertEquals(originalDto.street(), dtoAgain.street());
        assertEquals(originalDto.additionalDetails(), dtoAgain.additionalDetails());
        assertEquals(originalDto.city(), dtoAgain.city());
        assertEquals(originalDto.country(), dtoAgain.country());
    }
}
