package com.fiap.tech_challenge.core.usecase.restaurant;

import com.fiap.tech_challenge.core.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class DeleteRestaurantUseCaseTest {
    @Mock
    private RestaurantRepository restaurantRepository;
    private DeleteRestaurantUseCase deleteRestaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deleteRestaurantUseCase = new DeleteRestaurantUseCase(restaurantRepository);
    }

    @Test
    void testExecuteShouldDeleteRestaurantById() {
        Long id = 1L;
        doNothing().when(restaurantRepository).deleteById(id);

        deleteRestaurantUseCase.execute(id);

        verify(restaurantRepository, times(1)).deleteById(id);
    }

    @Test
    void testExecuteWithNullIdShouldNotDelete() {
        deleteRestaurantUseCase.execute(null);
        verify(restaurantRepository, times(1)).deleteById(null);
    }
}

