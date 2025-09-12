package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.domain.Restaurant;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.infrastructure.entity.AddressJpa;
import com.fiap.tech_challenge.infrastructure.entity.RestaurantJpa;
import com.fiap.tech_challenge.interfaces.dto.AddressDto;
import com.fiap.tech_challenge.interfaces.dto.RestaurantInputDto;
import com.fiap.tech_challenge.interfaces.dto.RestaurantOutputDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantMapperTest {
    @Test
    void testToEntity() {
        Address address = new Address("01234-567", "Rua A", "Apto 101", "São Paulo", "Brasil");
        Restaurant restaurant = new Restaurant(1L, "Restaurante Teste", address, "Italiana", "08:00-18:00", 1L);
        RestaurantJpa entity = RestaurantMapper.toEntity(restaurant);
        assertNotNull(entity);
        assertEquals(restaurant.getId(), entity.getId());
        assertEquals(restaurant.getName(), entity.getName());
        assertEquals(restaurant.getCuisineType(), entity.getCuisineType());
        assertEquals(restaurant.getOpeningHours(), entity.getOpeningHours());
        assertEquals(restaurant.getOwnerId(), entity.getOwnerId());
        assertNotNull(entity.getAddressJpa());
        assertEquals(address.getPostalCode(), entity.getAddressJpa().getPostalCode());
    }

    @Test
    void testToEntityWithNull() {
        assertNull(RestaurantMapper.toEntity(null));
    }

    @Test
    void testToDomain() {
        AddressJpa addressJpa = new AddressJpa("01234-567", "Rua A", "Apto 101", "São Paulo", "Brasil");
        RestaurantJpa entity = new RestaurantJpa(2L, "Restaurante JPA", addressJpa, "Japonesa", "09:00-22:00", 2L);
        Restaurant restaurant = RestaurantMapper.toDomain(entity);
        assertNotNull(restaurant);
        assertEquals(entity.getId(), restaurant.getId());
        assertEquals(entity.getName(), restaurant.getName());
        assertEquals(entity.getCuisineType(), restaurant.getCuisineType());
        assertEquals(entity.getOpeningHours(), restaurant.getOpeningHours());
        assertEquals(entity.getOwnerId(), restaurant.getOwnerId());
        assertNotNull(restaurant.getAddress());
        assertEquals(addressJpa.getPostalCode(), restaurant.getAddress().getPostalCode());
    }

    @Test
    void testToDomainWithNull() {
        assertNull(RestaurantMapper.toDomain(null));
    }

    @Test
    void testFromInputDto() {
        AddressDto addressDto = new AddressDto("01234-567", "Rua B", "Casa", "Rio de Janeiro", "Brasil");
        RestaurantInputDto inputDto = new RestaurantInputDto("Restaurante DTO", addressDto, "Brasileira", "10:00-20:00", 3L);
        Restaurant restaurant = RestaurantMapper.fromInputDto(inputDto);
        assertNotNull(restaurant);
        assertEquals(inputDto.name(), restaurant.getName());
        assertEquals(inputDto.cuisineType(), restaurant.getCuisineType());
        assertEquals(inputDto.openingHours(), restaurant.getOpeningHours());
        assertEquals(inputDto.ownerId(), restaurant.getOwnerId());
        assertNotNull(restaurant.getAddress());
        assertEquals(addressDto.postalCode(), restaurant.getAddress().getPostalCode());
    }

    @Test
    void testFromInputDtoWithNull() {
        assertNull(RestaurantMapper.fromInputDto(null));
    }

    @Test
    void testToOutputDto() {
        Address address = new Address("22222-222", "Rua C", "Sala 2", "Recife", "Brasil");
        Restaurant restaurant = new Restaurant(4L, "Restaurante Output", address, "Francesa", "11:00-23:00", 4L);
        RestaurantOutputDto outputDto = RestaurantMapper.toOutputDto(restaurant);
        assertNotNull(outputDto);
        assertEquals(restaurant.getId(), outputDto.id());
        assertEquals(restaurant.getName(), outputDto.name());
        assertEquals(restaurant.getCuisineType(), outputDto.cuisineType());
        assertEquals(restaurant.getOpeningHours(), outputDto.openingHours());
        assertEquals(restaurant.getOwnerId(), outputDto.ownerId());
        assertNotNull(outputDto.address());
        assertEquals(address.getPostalCode(), outputDto.address().postalCode());
    }

    @Test
    void testToOutputDtoWithNull() {
        assertNull(RestaurantMapper.toOutputDto(null));
    }
}

