package com.fiap.tech_challenge.core.usecase.owner;

import com.fiap.tech_challenge.core.domain.Owner;
import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.domain.restaurant.Restaurant;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.core.exception.InvalidAttributeException;
import com.fiap.tech_challenge.core.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateOwnerUseCaseTest {
    private OwnerRepository ownerRepository;
    private CreateOwnerUseCase createOwnerUseCase;

    @BeforeEach
    void setUp() {
        ownerRepository = Mockito.mock(OwnerRepository.class);
        createOwnerUseCase = new CreateOwnerUseCase(ownerRepository);
    }

    private Address createDomainAddress() {
        return new Address("12345", "Main Street", "Suite 1", "New York", "USA");
    }

    private Restaurant createDomainRestaurant() {
        return new Restaurant(1L, "Bistro 88", createDomainAddress(), "Italian", "09:00-22:00", new Owner());
    }

    private UserType createDomainUserType() {
        return new UserType(1L, "CUSTOMER");
    }

    private User createDomainUser(){
        return new User(
                5L,
                "Alice",
                "alice@example.com",
                "alice123",
                "secret",
                createDomainUserType(),
                createDomainAddress(),
                new Date(),
                true
        );
    }

    @Test
    void shouldCreateOwnerSuccessfully() {
        // given
        Owner owner = new Owner(
                null,
                "123456789",
                List.of(createDomainRestaurant()),
                createDomainUser()
        );

        when(ownerRepository.save(owner)).thenReturn(true);

        // when
        Boolean result = createOwnerUseCase.execute(owner);

        // then
        assertTrue(result);
        verify(ownerRepository, times(1)).save(owner);
    }

    @Test
    void shouldThrowExceptionWhenOwnerIsNull() {
        InvalidAttributeException exception = assertThrows(
                InvalidAttributeException.class,
                () -> createOwnerUseCase.execute(null)
        );
        assertEquals("Owner cannot be empty.", exception.getMessage());
        verifyNoInteractions(ownerRepository);
    }

    @Test
    void shouldThrowExceptionWhenDocumentIsNull() {
        Owner owner = new Owner(
                null,
                null,
                List.of(createDomainRestaurant()),
                createDomainUser()
        );
        owner.getRestaurants().getFirst().addOwner(owner);
        InvalidAttributeException exception = assertThrows(
                InvalidAttributeException.class,
                () -> createOwnerUseCase.execute(owner)
        );
        assertEquals("Owner's document cannot be empty.", exception.getMessage());
        verifyNoInteractions(ownerRepository);
    }

    @Test
    void shouldThrowExceptionWhenRestaurantsIsEmpty() {
        Owner owner = new Owner(
                null,
                "123456789",
                List.of(),
                createDomainUser()
        );

        InvalidAttributeException exception = assertThrows(
                InvalidAttributeException.class,
                () -> createOwnerUseCase.execute(owner)
        );
        assertEquals("Owner must have a least one restaurant active.", exception.getMessage());
        verifyNoInteractions(ownerRepository);
    }

    @Test
    void shouldThrowExceptionWhenUserIsNull() {
        Owner owner = new Owner(
                null,
                "123456789",
                List.of(createDomainRestaurant()),
                null
        );

        InvalidAttributeException exception = assertThrows(
                InvalidAttributeException.class,
                () -> createOwnerUseCase.execute(owner)
        );
        assertEquals("User's info cannot be empty.", exception.getMessage());
        verifyNoInteractions(ownerRepository);
    }
}
