package com.fiap.tech_challenge.infrastructure.repository;

import com.fiap.tech_challenge.core.domain.Owner;
import com.fiap.tech_challenge.core.domain.restaurant.Restaurant;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.infrastructure.entity.OwnerJpa;
import com.fiap.tech_challenge.infrastructure.entity.RestaurantJpa;
import com.fiap.tech_challenge.infrastructure.entity.AddressJpa;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaRestaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantRepositoryJpaTest {
    @Mock
    private SpringDataJpaRestaurant springDataJpaRestaurant;
    private RestaurantRepositoryJpa repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        repository = new RestaurantRepositoryJpa(springDataJpaRestaurant);
    }

    @Test
    void testSave() {
        Address address = new Address("01234-567", "Rua A", "Apto 101", "São Paulo", "Brasil");
        Owner owner = new Owner(1L, "12345678900", null, null);
        Restaurant restaurant = new Restaurant(1L, "Restaurante Teste", address, "Italiana", "08:00-18:00", owner);
        AddressJpa addressJpa = new AddressJpa(address.getPostalCode(), address.getStreet(), address.getAdditionalDetails(), address.getCity(), address.getCountry());
        OwnerJpa ownerJpa = new OwnerJpa();
        ownerJpa.setId(1L);
        RestaurantJpa entity = new RestaurantJpa(1L, restaurant.getName(), addressJpa, restaurant.getCuisineType(), restaurant.getOpeningHours(), ownerJpa);
        when(springDataJpaRestaurant.save(any(RestaurantJpa.class))).thenReturn(entity);

        Restaurant result = repository.save(restaurant);
        assertNotNull(result);
        assertEquals(restaurant.getName(), result.getName());
        verify(springDataJpaRestaurant, times(1)).save(any(RestaurantJpa.class));
    }

    @Test
    void testFindByIdFound() {
        AddressJpa addressJpa = new AddressJpa("01234-567", "Rua A", "Apto 101", "São Paulo", "Brasil");
        OwnerJpa ownerJpa = new OwnerJpa();
        ownerJpa.setId(2L);
        RestaurantJpa entity = new RestaurantJpa(2L, "Restaurante JPA", addressJpa, "Japonesa", "09:00-22:00", ownerJpa);
        when(springDataJpaRestaurant.findById(2L)).thenReturn(Optional.of(entity));

        Optional<Restaurant> result = repository.findById(2L);
        assertTrue(result.isPresent());
        assertEquals("Restaurante JPA", result.get().getName());
        verify(springDataJpaRestaurant, times(1)).findById(2L);
    }

    @Test
    void testFindByIdNotFound() {
        when(springDataJpaRestaurant.findById(3L)).thenReturn(Optional.empty());
        Optional<Restaurant> result = repository.findById(3L);
        assertFalse(result.isPresent());
        verify(springDataJpaRestaurant, times(1)).findById(3L);
    }

    @Test
    void testFindAll() {
        AddressJpa addressJpa1 = new AddressJpa("11111-111", "Rua B", "Sala 1", "Porto Alegre", "Brasil");
        AddressJpa addressJpa2 = new AddressJpa("22222-222", "Rua C", "Sala 2", "Recife", "Brasil");
        OwnerJpa ownerJpa1 = new OwnerJpa();
        ownerJpa1.setId(4L);
        OwnerJpa ownerJpa2 = new OwnerJpa();
        ownerJpa1.setId(5L);
        RestaurantJpa r1 = new RestaurantJpa(4L, "Restaurante 1", addressJpa1, "Francesa", "11:00-23:00", ownerJpa1);
        RestaurantJpa r2 = new RestaurantJpa(5L, "Restaurante 2", addressJpa2, "Chinesa", "12:00-00:00", ownerJpa2);
        when(springDataJpaRestaurant.findAll()).thenReturn(Arrays.asList(r1, r2));

        List<Restaurant> result = repository.findAll();
        assertEquals(2, result.size());
        assertEquals("Restaurante 1", result.get(0).getName());
        assertEquals("Restaurante 2", result.get(1).getName());
        verify(springDataJpaRestaurant, times(1)).findAll();
    }

    @Test
    void testFindAllEmpty() {
        when(springDataJpaRestaurant.findAll()).thenReturn(Arrays.asList());
        List<Restaurant> result = repository.findAll();
        assertTrue(result.isEmpty());
        verify(springDataJpaRestaurant, times(1)).findAll();
    }

    @Test
    void testDeleteById() {
        doNothing().when(springDataJpaRestaurant).deleteById(6L);
        repository.deleteById(6L);
        verify(springDataJpaRestaurant, times(1)).deleteById(6L);
    }

    @Test
    void testUpdate() {
        Address address = new Address("33333-333", "Rua D", "Sala 3", "Salvador", "Brasil");
        Owner owner = new Owner(7L, "12345678900", null, null);
        Restaurant restaurant = new Restaurant(7L, "Restaurante Atualizado", address, "Mexicana", "13:00-23:00", owner);
        AddressJpa addressJpa = new AddressJpa(address.getPostalCode(), address.getStreet(), address.getAdditionalDetails(), address.getCity(), address.getCountry());
        OwnerJpa ownerJpa = new OwnerJpa();
        ownerJpa.setId(7L);
        RestaurantJpa entity = new RestaurantJpa(7L, restaurant.getName(), addressJpa, restaurant.getCuisineType(), restaurant.getOpeningHours(), ownerJpa);
        when(springDataJpaRestaurant.save(any(RestaurantJpa.class))).thenReturn(entity);

        Restaurant result = repository.update(restaurant);
        assertNotNull(result);
        assertEquals("Restaurante Atualizado", result.getName());
        verify(springDataJpaRestaurant, times(1)).save(any(RestaurantJpa.class));
    }
}

