package com.fiap.tech_challenge.core.usecase.owner;

import com.fiap.tech_challenge.core.domain.Owner;
import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.domain.restaurant.Restaurant;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.core.exception.InvalidAttributeException;
import com.fiap.tech_challenge.core.exception.ResourceNotFoundException;
import com.fiap.tech_challenge.core.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UpdateOwnerUseCaseTest {
    private OwnerRepository ownerRepository;
    private UpdateOwnerUseCase updateOwnerUseCase;

    @BeforeEach
    void setUp() {
        ownerRepository = mock(OwnerRepository.class);
        updateOwnerUseCase = new UpdateOwnerUseCase(ownerRepository);
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

    private Owner createValidOwner() {
        return new Owner(
                null,
                "Valid document",
                List.of(createDomainRestaurant()),
                createDomainUser()
        );
    }

    @Test
    void shouldUpdateOwnerSuccessfully() {
        // given
        Long ownerId = 1L;
        Owner existingOwner = createValidOwner();
        Owner updatedData = createValidOwner();

        when(ownerRepository.findById(ownerId)).thenReturn(existingOwner);
        when(ownerRepository.update(ownerId, updatedData)).thenReturn(true);

        // when
        boolean result = updateOwnerUseCase.execute(ownerId, updatedData);

        // then
        assertTrue(result);
        verify(ownerRepository, times(1)).findById(ownerId);
        verify(ownerRepository, times(1)).update(ownerId, updatedData);
    }

    @Test
    void shouldThrowExceptionWhenOwnerNotFound() {
        // given
        Long ownerId = 999L;
        Owner updatedData = createValidOwner();

        when(ownerRepository.findById(ownerId)).thenReturn(null);

        // when / then
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> updateOwnerUseCase.execute(ownerId, updatedData)
        );

        assertEquals("Owner not found.", exception.getMessage());
        verify(ownerRepository, times(1)).findById(ownerId);
        verify(ownerRepository, never()).update(anyLong(), any());
    }

    @Test
    void shouldThrowExceptionWhenUpdatedOwnerIsNull() {
        Long ownerId = 1L;
        when(ownerRepository.findById(ownerId)).thenReturn(createValidOwner());

        InvalidAttributeException exception = assertThrows(
                InvalidAttributeException.class,
                () -> updateOwnerUseCase.execute(ownerId, null)
        );

        assertEquals("Owner cannot be empty.", exception.getMessage());
        verify(ownerRepository, times(1)).findById(ownerId);
        verify(ownerRepository, never()).update(anyLong(), any());
    }

    @Test
    void shouldThrowExceptionWhenDocumentIsEmpty() {
        Long ownerId = 1L;
        Owner validOwner = createValidOwner();
        Owner invalidOwner = new Owner(
                validOwner.getId(),
                "",
                validOwner.getRestaurants(),
                validOwner.getUser()
        );

        when(ownerRepository.findById(ownerId)).thenReturn(createValidOwner());

        InvalidAttributeException exception = assertThrows(
                InvalidAttributeException.class,
                () -> updateOwnerUseCase.execute(ownerId, invalidOwner)
        );

        assertEquals("Owner's document cannot be empty.", exception.getMessage());
        verify(ownerRepository, never()).update(anyLong(), any());
    }

    @Test
    void shouldThrowExceptionWhenRestaurantsIsEmpty() {
        Long ownerId = 1L;
        Owner validOwner = createValidOwner();
        Owner invalidOwner = new Owner(
                validOwner.getId(),
                validOwner.getDocument(),
                List.of(),
                validOwner.getUser()
        );

        when(ownerRepository.findById(ownerId)).thenReturn(createValidOwner());

        InvalidAttributeException exception = assertThrows(
                InvalidAttributeException.class,
                () -> updateOwnerUseCase.execute(ownerId, invalidOwner)
        );

        assertEquals("Owner must have a least one restaurant.", exception.getMessage());
        verify(ownerRepository, never()).update(anyLong(), any());
    }

    @Test
    void shouldThrowExceptionWhenUserIsNull() {
        Long ownerId = 1L;
        Owner validOwner = createValidOwner();
        Owner invalidOwner = new Owner(
                validOwner.getId(),
                validOwner.getDocument(),
                validOwner.getRestaurants(),
                null
        );

        when(ownerRepository.findById(ownerId)).thenReturn(createValidOwner());

        InvalidAttributeException exception = assertThrows(
                InvalidAttributeException.class,
                () -> updateOwnerUseCase.execute(ownerId, invalidOwner)
        );

        assertEquals("User's info cannot be empty.", exception.getMessage());
        verify(ownerRepository, never()).update(anyLong(), any());
    }
}
