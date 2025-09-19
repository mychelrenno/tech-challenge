package com.fiap.tech_challenge.core.usecase.restaurant;

import com.fiap.tech_challenge.core.domain.restaurant.Restaurant;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.core.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateRestaurantUseCaseTest {
    @Mock
    private RestaurantRepository restaurantRepository;
    private UpdateRestaurantUseCase updateRestaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        updateRestaurantUseCase = new UpdateRestaurantUseCase(restaurantRepository);
    }

    @Test
    void testExecuteShouldUpdateRestaurant() {
        Address address = new Address("01234-567", "Rua A", "Apto 101", "São Paulo", "Brasil");
        Restaurant restaurant = new Restaurant(1L, "Restaurante Atualizado", address, "Italiana", "08:00-18:00", 1L);
        when(restaurantRepository.update(restaurant)).thenReturn(restaurant);

        Restaurant result = updateRestaurantUseCase.execute(restaurant);

        assertNotNull(result);
        assertEquals("Restaurante Atualizado", result.getName());
        verify(restaurantRepository, times(1)).update(restaurant);
    }

    @Test
    void testExecuteShouldReturnNullWhenNotFound() {
        Address address = new Address("98765-432", "Rua B", "Casa", "Rio de Janeiro", "Brasil");
        Restaurant restaurant = new Restaurant(2L, "Restaurante Inexistente", address, "Japonesa", "09:00-22:00", 2L);
        when(restaurantRepository.update(restaurant)).thenReturn(null);

        Restaurant result = updateRestaurantUseCase.execute(restaurant);

        assertNull(result);
        verify(restaurantRepository, times(1)).update(restaurant);
    }
}

