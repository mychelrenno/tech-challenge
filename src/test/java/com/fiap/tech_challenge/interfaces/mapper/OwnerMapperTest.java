package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.domain.*;
import com.fiap.tech_challenge.core.domain.restaurant.Restaurant;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.infrastructure.entity.*;
import com.fiap.tech_challenge.interfaces.dto.AddressDto;
import com.fiap.tech_challenge.interfaces.dto.UserTypeDto;
import com.fiap.tech_challenge.interfaces.dto.owner.OwnerInputDto;
import com.fiap.tech_challenge.interfaces.dto.restaurant.RestaurantInputDto;
import com.fiap.tech_challenge.interfaces.dto.user.UserInputDto;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OwnerMapperTest {
    private Address createAddress() {
        return new Address("12345", "Main Street", "Suite 1", "New York", "USA");
    }

    private AddressDto createAddressDto() {
        return new AddressDto("12345", "Main Street", "Apt 10", "New York", "USA");
    }

    private AddressJpa createAddressJpa() {
        return new AddressJpa("12345", "Main Street", "Apt 10", "New York", "USA");
    }

    private UserTypeJpa createUserTypeJpa() {
        UserTypeJpa typeJpa = new UserTypeJpa();
        typeJpa.setId(1L);
        typeJpa.setName("OWNER");
        return typeJpa;
    }

    private UserType createDomainUserType() {
        return new UserType(1L, "CUSTOMER");
    }

    private User createDomainUser() {
        return new User(1L, "John Doe", "john@example.com", "johndoe", "password123", createDomainUserType(), createAddress(), new Date(), true);
    }

    private UserJpa createUserJpa() {
        UserJpa userJpa = new UserJpa();
        userJpa.setId(1L);
        userJpa.setName("Jane Smith");
        userJpa.setEmail("jane@example.com");
        userJpa.setUsername("securepass");
        userJpa.setPassword("password123");
        userJpa.setUserTypeJpa(createUserTypeJpa());
        userJpa.setAddressJpa(createAddressJpa());
        return userJpa;
    }

    private OwnerInputDto createOwnerInputDto(){
        return new OwnerInputDto(
                "valid document",
                List.of(),
                null
        );
    }

    private RestaurantInputDto createRestaurantInputDto() {
        return new RestaurantInputDto(
                "Bistro 88",
                createAddressDto(),
                "Italian",
                "09:00-22:00",
                1L
        );
    }

    private Restaurant createDomainRestaurant() {
        return new Restaurant(1L, "Bistro 88", createAddress(), "Italian", "09:00-22:00", new Owner());
    }

    private RestaurantJpa createRestaurantJpa() {
        RestaurantJpa restaurantJpa = new RestaurantJpa();
        restaurantJpa.setId(1L);
        restaurantJpa.setName("Bistro 88");
        restaurantJpa.setCuisineType("Italian");
        restaurantJpa.setOpeningHours("09:00-22:00");
        restaurantJpa.setAddressJpa(createAddressJpa());
        restaurantJpa.setOwner(new OwnerJpa());
        return restaurantJpa;
    }

    @Test
    void shouldConvertInputDtoToDomainSuccessfully() {
        OwnerInputDto inputDto = new OwnerInputDto(
                "11122233344",
                List.of(createRestaurantInputDto()),
                new UserInputDto("Jane Smith",
                        "jane@example.com",
                        "securepass",
                        "password123",
                        new UserTypeDto(1L, "CLIENT"),
                        createAddressDto())
        );

        Owner owner = OwnerMapper.convertInputDtoToDomain(inputDto);

        assertNotNull(owner);
        assertNull(owner.getId());
        assertEquals("11122233344", owner.getDocument());
        assertNotNull(owner.getUser());
        assertEquals("Jane Smith", owner.getUser().getName());
        assertEquals(1, owner.getRestaurants().size());

        Restaurant restaurant = owner.getRestaurants().getFirst();
        assertEquals("Bistro 88", restaurant.getName());
        assertEquals("Italian", restaurant.getCuisineType());
        assertEquals("New York", restaurant.getAddress().getCity());

        assertSame(owner, restaurant.getOwner());
    }

    @Test
    void shouldConvertEntityToJpaSuccessfully() {
        Owner owner = new Owner(1L, "55566677788", List.of(createDomainRestaurant()), createDomainUser());

        OwnerJpa jpa = OwnerMapper.convertEntityToJpa(owner);

        assertNotNull(jpa);
        assertEquals(1L, jpa.getId());
        assertEquals("55566677788", jpa.getDocument());
        assertNotNull(jpa.getUser());
        assertEquals("John Doe", jpa.getUser().getName());
        assertNotNull(jpa.getRestaurants());
        assertEquals("Bistro 88", jpa.getRestaurants().getFirst().getName());
    }

    @Test
    void shouldReturnNullWhenConvertingNullEntityToJpa() {
        assertNull(OwnerMapper.convertEntityToJpa(null));
    }

    @Test
    void shouldConvertJpaToEntitySuccessfully() {
        OwnerJpa jpa = new OwnerJpa();
        jpa.setId(10L);
        jpa.setDocument("00011122233");
        jpa.setUser(createUserJpa());
        jpa.setRestaurants(List.of(createRestaurantJpa()));
        Owner owner = OwnerMapper.convertJpaToEntity(jpa);

        assertNotNull(owner);
        assertEquals(10L, owner.getId());
        assertEquals("00011122233", owner.getDocument());
        assertNotNull(owner.getUser());
        assertEquals("Jane Smith", owner.getUser().getName());
        assertNotNull(owner.getRestaurants());
        assertEquals(1, owner.getRestaurants().size());
        assertEquals("Bistro 88", owner.getRestaurants().getFirst().getName());
    }

    @Test
    void shouldReturnNullWhenConvertingNullJpaToEntity() {
        assertNull(OwnerMapper.convertJpaToEntity(null));
    }

    @Test
    void shouldMaintainDataIntegrityAcrossConversions() {
        Owner original = new Owner(3L, "99988877766", List.of(createDomainRestaurant()), createDomainUser());

        OwnerJpa jpa = OwnerMapper.convertEntityToJpa(original);
        Owner backToEntity = OwnerMapper.convertJpaToEntity(jpa);

        assertNotNull(backToEntity);
        assertEquals(original.getId(), backToEntity.getId());
        assertEquals(original.getDocument(), backToEntity.getDocument());
        assertEquals(original.getUser().getName(), backToEntity.getUser().getName());
        assertEquals(original.getRestaurants().getFirst().getName(), backToEntity.getRestaurants().getFirst().getName());
    }
}
