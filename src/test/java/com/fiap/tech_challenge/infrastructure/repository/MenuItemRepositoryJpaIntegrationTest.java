package com.fiap.tech_challenge.infrastructure.repository;

import com.fiap.tech_challenge.core.domain.MenuItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class MenuItemRepositoryJpaIntegrationTest {

    @Autowired
    private MenuItemRepositoryJpa menuItemRepository;

    @Test
    void shouldSaveMenuItemSuccessfully() {
        // Given
        MenuItem menuItem = new MenuItem(
                "Pizza Margherita",
                "Pizza clássica com molho de tomate, mussarela e manjericão fresco",
                45.90,
                false,
                "/images/pizza-margherita.jpg"
        );

        // When
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);

        // Then
        assertNotNull(savedMenuItem);
        assertEquals("Pizza Margherita", savedMenuItem.getName());
        assertEquals("Pizza clássica com molho de tomate, mussarela e manjericão fresco", savedMenuItem.getDescription());
        assertEquals(45.90, savedMenuItem.getPrice());
        assertFalse(savedMenuItem.getRestaurantOnly());
        assertEquals("/images/pizza-margherita.jpg", savedMenuItem.getImagePath());

        // Additional verification that the entity was persisted
        assertNotNull(savedMenuItem);
    }

    @Test
    void shouldSaveMenuItemForRestaurantOnly() {
        // Given
        MenuItem menuItem = new MenuItem(
                "Café Expresso",
                "Café expresso tradicional, servido apenas no local",
                4.50,
                true,
                "/images/cafe-expresso.jpg"
        );

        // When
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);

        // Then
        assertNotNull(savedMenuItem);
        assertEquals("Café Expresso", savedMenuItem.getName());
        assertTrue(savedMenuItem.getRestaurantOnly());
        assertEquals(4.50, savedMenuItem.getPrice());
    }

    @Test
    void shouldSaveMenuItemWithNullValues() {
        // Given
        MenuItem menuItem = new MenuItem(null, null, null, null, null);

        // When
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);

        // Then
        assertNotNull(savedMenuItem);
        assertNull(savedMenuItem.getName());
        assertNull(savedMenuItem.getDescription());
        assertNull(savedMenuItem.getPrice());
        assertNull(savedMenuItem.getRestaurantOnly());
        assertNull(savedMenuItem.getImagePath());
    }

    @Test
    void shouldSaveMultipleMenuItems() {
        // Given
        MenuItem pizza = new MenuItem(
                "Pizza Margherita",
                "Pizza clássica",
                45.90,
                false,
                "/images/pizza.jpg"
        );

        MenuItem cafe = new MenuItem(
                "Café Expresso",
                "Café tradicional",
                4.50,
                true,
                "/images/cafe.jpg"
        );

        // When
        MenuItem savedPizza = menuItemRepository.save(pizza);
        MenuItem savedCafe = menuItemRepository.save(cafe);

        // Then
        assertNotNull(savedPizza);
        assertNotNull(savedCafe);
        assertEquals("Pizza Margherita", savedPizza.getName());
        assertEquals("Café Expresso", savedCafe.getName());
        assertNotEquals(savedPizza.getName(), savedCafe.getName());
    }
}
