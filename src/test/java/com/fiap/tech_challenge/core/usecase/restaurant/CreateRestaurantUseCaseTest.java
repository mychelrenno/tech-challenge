package com.fiap.tech_challenge.core.usecase.restaurant;

import com.fiap.tech_challenge.core.domain.Owner;
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
        Owner owner = new Owner(1L, "12345678900", null, null);
        Restaurant restaurant = new Restaurant(1L, "Restaurante Teste", address, "Italiana", "08:00-18:00", owner);
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);

        Restaurant result = createRestaurantUseCase.execute(restaurant);

        assertNotNull(result);
        assertEquals("Restaurante Teste", result.getName());
        verify(restaurantRepository, times(1)).save(restaurant);
    }

    @Test
    void testExecuteShouldReturnNullWhenRepositoryReturnsNull() {
        Address address = new Address("01234-567", "Rua B", "Casa", "Rio de Janeiro", "Brasil");
        Owner owner = new Owner(2L, "12345678900", null, null);
        Restaurant restaurant = new Restaurant(2L, "Restaurante Null", address, "Japonesa", "09:00-22:00", owner);
        when(restaurantRepository.save(restaurant)).thenReturn(null);

        Restaurant result = createRestaurantUseCase.execute(restaurant);

        assertNull(result);
        verify(restaurantRepository, times(1)).save(restaurant);
    }

    @Test
    void testConstructorShouldThrowExceptionWhenNameIsNullOrBlank() {
        Address address = new Address("01234-567", "Rua A", "Apto 101", "São Paulo", "Brasil");
        Owner owner = new Owner(1L, "12345678900", null, null);
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(1L, null, address, "Italiana", "08:00-18:00", owner));
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(1L, "   ", address, "Italiana", "08:00-18:00", owner));
    }

    @Test
    void testConstructorShouldThrowExceptionWhenAddressIsNull() {
        Owner owner = new Owner(1L, "12345678900", null, null);
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(1L, "Restaurante Teste", null, "Italiana", "08:00-18:00", owner));
    }

    @Test
    void testConstructorShouldThrowExceptionWhenCuisineTypeIsNullOrBlank() {
        Address address = new Address("01234-567", "Rua A", "Apto 101", "São Paulo", "Brasil");
        Owner owner = new Owner(1L, "12345678900", null, null);
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(1L, "Restaurante Teste", address, null, "08:00-18:00", owner));
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(1L, "Restaurante Teste", address, "   ", "08:00-18:00", owner));
    }

    @Test
    void testConstructorShouldThrowExceptionWhenOpeningHoursIsNullOrBlank() {
        Address address = new Address("01234-567", "Rua A", "Apto 101", "São Paulo", "Brasil");
        Owner owner = new Owner(1L, "12345678900", null, null);
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(1L, "Restaurante Teste", address, "Italiana", null, owner));
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(1L, "Restaurante Teste", address, "Italiana", "   ", owner));
    }

    @Test
    void testConstructorShouldThrowExceptionWhenOwnerIsNull() {
        Address address = new Address("01234-567", "Rua A", "Apto 101", "São Paulo", "Brasil");
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(1L, "Restaurante Teste", address, "Italiana", "08:00-18:00", null));
    }
}
