package com.fiap.tech_challenge.core.usecase.restaurant;

import com.fiap.tech_challenge.core.domain.Owner;
import com.fiap.tech_challenge.core.domain.restaurant.Restaurant;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.core.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetRestaurantUseCaseTest {
    @Mock
    private RestaurantRepository restaurantRepository;
    private GetRestaurantUseCase getRestaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getRestaurantUseCase = new GetRestaurantUseCase(restaurantRepository);
    }

    @Test
    void testFindByIdShouldReturnRestaurant() {
        Address address = new Address("01234-567", "Rua A", "Apto 101", "São Paulo", "Brasil");
        Owner owner = new Owner(1L, "12345678900", null, null);
        Restaurant restaurant = new Restaurant(1L, "Restaurante Teste", address, "Italiana", "08:00-18:00", owner);
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));

        Optional<Restaurant> result = getRestaurantUseCase.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Restaurante Teste", result.get().getName());
        verify(restaurantRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdShouldReturnEmptyWhenNotFound() {
        when(restaurantRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Restaurant> result = getRestaurantUseCase.findById(2L);

        assertFalse(result.isPresent());
        verify(restaurantRepository, times(1)).findById(2L);
    }

    @Test
    void testFindAllShouldReturnListOfRestaurants() {
        Address address1 = new Address("11111-111", "Rua B", "Sala 1", "Porto Alegre", "Brasil");
        Address address2 = new Address("22222-222", "Rua C", "Sala 2", "Recife", "Brasil");
        Owner owner1 = new Owner(4L, "12345678900", null, null);
        Owner owner2 = new Owner(5L, "12345678900", null, null);
        Restaurant r1 = new Restaurant(3L, "Restaurante 1", address1, "Francesa", "11:00-23:00", owner1);
        Restaurant r2 = new Restaurant(4L, "Restaurante 2", address2, "Chinesa", "12:00-00:00", owner2);
        when(restaurantRepository.findAll()).thenReturn(Arrays.asList(r1, r2));

        List<Restaurant> result = getRestaurantUseCase.findAll();

        assertEquals(2, result.size());
        assertEquals("Restaurante 1", result.get(0).getName());
        assertEquals("Restaurante 2", result.get(1).getName());
        verify(restaurantRepository, times(1)).findAll();
    }

    @Test
    void testFindAllShouldReturnEmptyList() {
        when(restaurantRepository.findAll()).thenReturn(Collections.emptyList());

        List<Restaurant> result = getRestaurantUseCase.findAll();

        assertTrue(result.isEmpty());
        verify(restaurantRepository, times(1)).findAll();
    }
}

