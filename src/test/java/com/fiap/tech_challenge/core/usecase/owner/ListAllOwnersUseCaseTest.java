package com.fiap.tech_challenge.core.usecase.owner;

import com.fiap.tech_challenge.core.domain.Owner;
import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.domain.restaurant.Restaurant;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.core.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ListAllOwnersUseCaseTest {
    private OwnerRepository ownerRepository;
    private ListAllOwnersUseCase listAllOwnersUseCase;

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

    @BeforeEach
    void setUp() {
        ownerRepository = mock(OwnerRepository.class);
        listAllOwnersUseCase = new ListAllOwnersUseCase(ownerRepository);
    }

    @Test
    void shouldReturnListOfActiveOwners() {
        // given
        Owner owner1 = new Owner(
                1L,
                "Document 1",
                List.of(createDomainRestaurant()),
                createDomainUser()
        );
        owner1.getRestaurants().getFirst().addOwner(owner1);

        Owner owner2 = new Owner(
                2L,
                "Document 2",
                List.of(createDomainRestaurant()),
                createDomainUser()
        );
        owner2.getRestaurants().getFirst().addOwner(owner2);

        List<Owner> activeOwners = List.of(owner1, owner2);

        when(ownerRepository.findByActiveTrue()).thenReturn(activeOwners);

        // when
        List<Owner> result = listAllOwnersUseCase.listAllOwnersActive();

        // then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
        verify(ownerRepository, times(1)).findByActiveTrue();
    }

    @Test
    void shouldReturnEmptyListWhenNoActiveOwnersFound() {
        // given
        when(ownerRepository.findByActiveTrue()).thenReturn(Collections.emptyList());

        // when
        List<Owner> result = listAllOwnersUseCase.listAllOwnersActive();

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(ownerRepository, times(1)).findByActiveTrue();
    }
}
