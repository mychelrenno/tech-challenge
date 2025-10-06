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

class CreateRestaurantUseCaseTest {
    @Mock
    private RestaurantRepository restaurantRepository;
    private CreateRestaurantUseCase createRestaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createRestaurantUseCase = new CreateRestaurantUseCase(restaurantRepository);
    }

    @Test
    void testExecuteShouldSaveRestaurant() {
        Address address = new Address("01234-567", "Rua A", "Apto 101", "São Paulo", "Brasil");
        Restaurant restaurant = new Restaurant(1L, "Restaurante Teste", address, "Italiana", "08:00-18:00", 1L);
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);

        Restaurant result = createRestaurantUseCase.execute(restaurant);

        assertNotNull(result);
        assertEquals("Restaurante Teste", result.getName());
        verify(restaurantRepository, times(1)).save(restaurant);
    }

    @Test
    void testExecuteShouldReturnNullWhenRepositoryReturnsNull() {
        Address address = new Address("01234-567", "Rua B", "Casa", "Rio de Janeiro", "Brasil");
        Restaurant restaurant = new Restaurant(2L, "Restaurante Null", address, "Japonesa", "09:00-22:00", 2L);
        when(restaurantRepository.save(restaurant)).thenReturn(null);

        Restaurant result = createRestaurantUseCase.execute(restaurant);

        assertNull(result);
        verify(restaurantRepository, times(1)).save(restaurant);
    }
}

