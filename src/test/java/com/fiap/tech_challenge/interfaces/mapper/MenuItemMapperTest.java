package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.entity.MenuItem;
import com.fiap.tech_challenge.infrastructure.entity.MenuItemJpa;
import com.fiap.tech_challenge.interfaces.dto.MenuItemDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemMapperTest {

    @Test
    void shouldConvertDtoToEntity() {
        // Given
        MenuItemDto dto = new MenuItemDto(
                null,
                "Pizza Margherita",
                "Pizza clássica com molho de tomate, mussarela e manjericão fresco",
                45.90,
                false,
                "/images/pizza-margherita.jpg"
        );

        // When
        MenuItem entity = MenuItemMapper.convertDtoToEntity(dto);

        // Then
        assertNotNull(entity);
        assertEquals(dto.name(), entity.getName());
        assertEquals(dto.description(), entity.getDescription());
        assertEquals(dto.price(), entity.getPrice());
        assertEquals(dto.restaurantOnly(), entity.getRestaurantOnly());
        assertEquals(dto.imagePath(), entity.getImagePath());
    }

    @Test
    void shouldConvertEntityToJpa() {
        // Given
        MenuItem entity = new MenuItem(
                "Pizza Margherita",
                "Pizza clássica com molho de tomate, mussarela e manjericão fresco",
                45.90,
                false,
                "/images/pizza-margherita.jpg"
        );

        // When
        MenuItemJpa jpa = MenuItemMapper.convertEntityToJpa(entity);

        // Then
        assertNotNull(jpa);
        assertEquals(entity.getName(), jpa.getName());
        assertEquals(entity.getDescription(), jpa.getDescription());
        assertEquals(entity.getPrice(), jpa.getPrice());
        assertEquals(entity.getRestaurantOnly(), jpa.getRestaurantOnly());
        assertEquals(entity.getImagePath(), jpa.getImagePath());
    }

    @Test
    void shouldConvertJpaToEntity() {
        // Given
        MenuItemJpa jpa = new MenuItemJpa(
                "Pizza Margherita",
                "Pizza clássica com molho de tomate, mussarela e manjericão fresco",
                45.90,
                false,
                "/images/pizza-margherita.jpg"
        );

        // When
        MenuItem entity = MenuItemMapper.convertJpaToEntity(jpa);

        // Then
        assertNotNull(entity);
        assertEquals(jpa.getName(), entity.getName());
        assertEquals(jpa.getDescription(), entity.getDescription());
        assertEquals(jpa.getPrice(), entity.getPrice());
        assertEquals(jpa.getRestaurantOnly(), entity.getRestaurantOnly());
        assertEquals(jpa.getImagePath(), entity.getImagePath());
    }

    @Test
    void shouldConvertDtoToEntityWithRestaurantOnly() {
        // Given
        MenuItemDto dto = new MenuItemDto(
                null,
                "Café Expresso",
                "Café expresso tradicional, servido apenas no local",
                4.50,
                true,
                "/images/cafe-expresso.jpg"
        );

        // When
        MenuItem entity = MenuItemMapper.convertDtoToEntity(dto);

        // Then
        assertNotNull(entity);
        assertEquals(dto.name(), entity.getName());
        assertTrue(entity.getRestaurantOnly());
        assertEquals(dto.price(), entity.getPrice());
    }

    @Test
    void shouldConvertEntityToJpaWithNullValues() {
        // Given
        MenuItem entity = new MenuItem(null, null, null, null, null);

        // When
        MenuItemJpa jpa = MenuItemMapper.convertEntityToJpa(entity);

        // Then
        assertNotNull(jpa);
        assertNull(jpa.getName());
        assertNull(jpa.getDescription());
        assertNull(jpa.getPrice());
        assertNull(jpa.getRestaurantOnly());
        assertNull(jpa.getImagePath());
    }

    @Test
    void shouldConvertJpaToEntityWithNullValues() {
        // Given
        MenuItemJpa jpa = new MenuItemJpa(null, null, null, null, null);

        // When
        MenuItem entity = MenuItemMapper.convertJpaToEntity(jpa);

        // Then
        assertNotNull(entity);
        assertNull(entity.getName());
        assertNull(entity.getDescription());
        assertNull(entity.getPrice());
        assertNull(entity.getRestaurantOnly());
        assertNull(entity.getImagePath());
    }

    @Test
    void shouldConvertDtoToEntityWithNullValues() {
        // Given
        MenuItemDto dto = new MenuItemDto(null, null, null, null, null, null);

        // When
        MenuItem entity = MenuItemMapper.convertDtoToEntity(dto);

        // Then
        assertNotNull(entity);
        assertNull(entity.getName());
        assertNull(entity.getDescription());
        assertNull(entity.getPrice());
        assertNull(entity.getRestaurantOnly());
        assertNull(entity.getImagePath());
    }
}

