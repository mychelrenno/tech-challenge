package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.domain.Owner;
import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.domain.restaurant.Restaurant;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.infrastructure.entity.*;
import com.fiap.tech_challenge.interfaces.dto.AddressDto;
import com.fiap.tech_challenge.interfaces.dto.UserTypeDto;
import com.fiap.tech_challenge.interfaces.dto.owner.OwnerInputDto;
import com.fiap.tech_challenge.interfaces.dto.restaurant.RestaurantInputDto;
import com.fiap.tech_challenge.interfaces.dto.restaurant.RestaurantOutputDto;
import com.fiap.tech_challenge.interfaces.dto.user.UserInputDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantMapperTest {
    @Test
    void testToEntity() {
        Address address = new Address("01234-567", "Rua A", "Apto 101", "São Paulo", "Brasil");
        Owner owner = new Owner(2L, "12345678900", null, null);
        Restaurant restaurant = new Restaurant(1L, "Restaurante Teste", address, "Italiana", "08:00-18:00", owner);
        RestaurantJpa restaurantJpa = RestaurantMapper.convertEntityToJpa(restaurant);
        assertNotNull(restaurantJpa);
        assertEquals(restaurant.getId(), restaurantJpa.getId());
        assertEquals(restaurant.getName(), restaurantJpa.getName());
        assertEquals(restaurant.getCuisineType(), restaurantJpa.getCuisineType());
        assertEquals(restaurant.getOpeningHours(), restaurantJpa.getOpeningHours());
        assertEquals(restaurant.getOwner().getId(), restaurantJpa.getOwner().getId());
        assertNotNull(restaurantJpa.getAddressJpa());
        assertEquals(address.getPostalCode(), restaurantJpa.getAddressJpa().getPostalCode());
    }

    @Test
    void testToEntityWithNull() {
        assertNull(RestaurantMapper.convertEntityToJpa(null));
    }

    @Test
    void testToDomain() {
        AddressJpa addressJpa = new AddressJpa("01234-567", "Rua A", "Apto 101", "São Paulo", "Brasil");
        UserJpa userJpa = new UserJpa(1L, "ownerUser", "", "", "password123", new UserTypeJpa(1L, "OWNER"), addressJpa, new Date(), Boolean.TRUE);
        OwnerJpa ownerJpa = new OwnerJpa(1L, "12345678900", Collections.emptyList(), null);
        ownerJpa.setId(2L);
        RestaurantJpa entity = new RestaurantJpa(2L, "Restaurante JPA", addressJpa, "Japonesa", "09:00-22:00", ownerJpa);
        Restaurant restaurant = RestaurantMapper.convertJpaToEntity(entity);
        assertNotNull(restaurant);
        assertEquals(entity.getId(), restaurant.getId());
        assertEquals(entity.getName(), restaurant.getName());
        assertEquals(entity.getCuisineType(), restaurant.getCuisineType());
        assertEquals(entity.getOpeningHours(), restaurant.getOpeningHours());
        assertEquals(entity.getOwner().getId(), restaurant.getOwner().getId());
        assertNotNull(restaurant.getAddress());
        assertEquals(addressJpa.getPostalCode(), restaurant.getAddress().getPostalCode());
    }

    @Test
    void testToDomainWithNull() {
        assertNull(RestaurantMapper.convertJpaToEntity(null));
    }

    @Test
    void testFromInputDto() {
        AddressDto addressDto = new AddressDto("01234-567", "Rua B", "Casa", "Rio de Janeiro", "Brasil");
        UserTypeDto userTypeDto = new UserTypeDto(1L, "OWNER");
        UserInputDto userDto = new UserInputDto("ownerUser", "", "", "password123", userTypeDto , addressDto);
        Long ownerId = 1L;
        RestaurantInputDto inputDto = new RestaurantInputDto("Restaurante DTO", addressDto, "Brasileira", "10:00-20:00", ownerId);
        Restaurant restaurant = RestaurantMapper.convertInputDtoToDomain(inputDto);
        assertNotNull(restaurant);
        assertEquals(inputDto.name(), restaurant.getName());
        assertEquals(inputDto.cuisineType(), restaurant.getCuisineType());
        assertEquals(inputDto.openingHours(), restaurant.getOpeningHours());
        assertEquals(inputDto.ownerId(), restaurant.getOwner().getId());
        assertNotNull(restaurant.getAddress());
        assertEquals(addressDto.postalCode(), restaurant.getAddress().getPostalCode());
    }

    @Test
    void testFromInputDtoWithNull() {
        assertNull(RestaurantMapper.convertInputDtoToDomain(null));
    }

    @Test
    void testToOutputDto() {
        Address address = new Address("22222-222", "Rua C", "Sala 2", "Recife", "Brasil");
        Owner owner = new Owner(4L, "12345678900", null, null);
        Restaurant restaurant = new Restaurant(4L, "Restaurante Output", address, "Francesa", "11:00-23:00", owner);
        RestaurantOutputDto outputDto = RestaurantMapper.convertEntitytoOutputDto(restaurant);
        assertNotNull(outputDto);
        assertEquals(restaurant.getId(), outputDto.id());
        assertEquals(restaurant.getName(), outputDto.name());
        assertEquals(restaurant.getCuisineType(), outputDto.cuisineType());
        assertEquals(restaurant.getOpeningHours(), outputDto.openingHours());
        assertEquals(restaurant.getOwner().getId(), outputDto.restaurantOwner().id());
        assertNotNull(outputDto.address());
        assertEquals(address.getPostalCode(), outputDto.address().postalCode());
    }

    @Test
    void testToOutputDtoWithNull() {
        assertNull(RestaurantMapper.convertEntitytoOutputDto(null));
    }
}

