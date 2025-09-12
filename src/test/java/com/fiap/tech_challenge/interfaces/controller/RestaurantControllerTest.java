package com.fiap.tech_challenge.interfaces.controller;

import com.fiap.tech_challenge.core.domain.Restaurant;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.core.usecase.restaurant.CreateRestaurantUseCase;
import com.fiap.tech_challenge.core.usecase.restaurant.UpdateRestaurantUseCase;
import com.fiap.tech_challenge.core.usecase.restaurant.DeleteRestaurantUseCase;
import com.fiap.tech_challenge.core.usecase.restaurant.GetRestaurantUseCase;
import com.fiap.tech_challenge.interfaces.dto.AddressDto;
import com.fiap.tech_challenge.interfaces.dto.RestaurantInputDto;
import com.fiap.tech_challenge.interfaces.dto.RestaurantOutputDto;
import com.fiap.tech_challenge.interfaces.mapper.RestaurantMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantControllerTest {
    @Mock
    private CreateRestaurantUseCase createUseCase;
    @Mock
    private UpdateRestaurantUseCase updateUseCase;
    @Mock
    private DeleteRestaurantUseCase deleteUseCase;
    @Mock
    private GetRestaurantUseCase getUseCase;

    @InjectMocks
    private RestaurantController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new RestaurantController(createUseCase, updateUseCase, deleteUseCase, getUseCase);
    }

    @Test
    void testCreate() {
        AddressDto addressDto = new AddressDto("01234-567", "Endereço A", "Apto 101", "São Paulo", "Brasil");
        RestaurantInputDto inputDto = new RestaurantInputDto("Restaurante A", addressDto, "Italiana", "08:00-18:00", 1L);
        Address address = new Address(addressDto.postalCode(), addressDto.street(), addressDto.additionalDetails(), addressDto.city(), addressDto.country());
        Restaurant created = new Restaurant(1L, inputDto.name(), address, inputDto.cuisineType(), inputDto.openingHours(), inputDto.ownerId());
        when(createUseCase.execute(any(Restaurant.class))).thenReturn(created);

        ResponseEntity<RestaurantOutputDto> response = controller.create(inputDto);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    void testUpdate() {
        AddressDto addressDto = new AddressDto("98765-432", "Endereço B", "Casa", "Rio de Janeiro", "Brasil");
        RestaurantInputDto inputDto = new RestaurantInputDto("Restaurante B", addressDto, "Japonesa", "09:00-22:00", 2L);
        Address address = new Address(addressDto.postalCode(), addressDto.street(), addressDto.additionalDetails(), addressDto.city(), addressDto.country());
        Restaurant updated = new Restaurant(2L, inputDto.name(), address, inputDto.cuisineType(), inputDto.openingHours(), inputDto.ownerId());
        when(updateUseCase.execute(any(Restaurant.class))).thenReturn(updated);

        ResponseEntity<RestaurantOutputDto> response = controller.update(2L, inputDto);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Restaurante B", response.getBody().name());
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    void testDelete() {
        doNothing().when(deleteUseCase).execute(3L);
        ResponseEntity<Void> response = controller.delete(3L);
        assertEquals(204, response.getStatusCodeValue());
        assertEquals(204, response.getStatusCode().value());
    }

    @Test
    void testGetByIdFound() {
        Address address = new Address("12345-678", "Endereço C", "Apto 202", "Curitiba", "Brasil");
        Restaurant restaurant = new Restaurant(4L, "Restaurante C", address, "Brasileira", "10:00-20:00", 3L);
        when(getUseCase.findById(4L)).thenReturn(Optional.of(restaurant));
        ResponseEntity<RestaurantOutputDto> response = controller.getById(4L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Restaurante C", response.getBody().name());
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetByIdNotFound() {
        when(getUseCase.findById(5L)).thenReturn(Optional.empty());
        ResponseEntity<RestaurantOutputDto> response = controller.getById(5L);
        assertEquals(404, response.getStatusCodeValue());
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void testGetAll() {
        Address address1 = new Address("11111-111", "Endereço D", "Sala 1", "Porto Alegre", "Brasil");
        Address address2 = new Address("22222-222", "Endereço E", "Sala 2", "Recife", "Brasil");
        Restaurant r1 = new Restaurant(6L, "Restaurante D", address1, "Francesa", "11:00-23:00", 4L);
        Restaurant r2 = new Restaurant(7L, "Restaurante E", address2, "Chinesa", "12:00-00:00", 5L);
        when(getUseCase.findAll()).thenReturn(Arrays.asList(r1, r2));
        ResponseEntity<List<RestaurantOutputDto>> response = controller.getAll();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }
}
