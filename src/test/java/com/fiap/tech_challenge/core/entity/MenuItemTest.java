package com.fiap.tech_challenge.core.entity;

import com.fiap.tech_challenge.core.domain.restaurant.MenuItem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MenuItemTest {

    @Test
    void shouldCreateMenuItemWithAllFields() {
        // Given
        String name = "Pizza Margherita";
        String description = "Pizza clássica com molho de tomate, mussarela e manjericão fresco";
        Double price = 45.90;
        Boolean restaurantOnly = false;
        String imagePath = "/images/pizza-margherita.jpg";

        // When
        MenuItem menuItem = new MenuItem(name, description, price, restaurantOnly, imagePath);

        // Then
        assertNotNull(menuItem);
        assertEquals(name, menuItem.getName());
        assertEquals(description, menuItem.getDescription());
        assertEquals(price, menuItem.getPrice());
        assertEquals(restaurantOnly, menuItem.getRestaurantOnly());
        assertEquals(imagePath, menuItem.getImagePath());
    }

    @Test
    void shouldAllowModificationOfFields() {
        // Given
        MenuItem menuItem = new MenuItem("Original Name", "Original Description", 10.0, false, "/original.jpg");

        // When
        menuItem.setName("New Name");
        menuItem.setDescription("New Description");
        menuItem.setPrice(15.50);
        menuItem.setRestaurantOnly(true);
        menuItem.setImagePath("/new.jpg");

        // Then
        assertEquals("New Name", menuItem.getName());
        assertEquals("New Description", menuItem.getDescription());
        assertEquals(15.50, menuItem.getPrice());
        assertTrue(menuItem.getRestaurantOnly());
        assertEquals("/new.jpg", menuItem.getImagePath());
    }

    @Test
    void shouldCreateMenuItemForRestaurantOnly() {
        // Given
        String name = "Café Expresso";
        String description = "Café expresso tradicional, servido apenas no local";
        Double price = 4.50;
        Boolean restaurantOnly = true;
        String imagePath = "/images/cafe-expresso.jpg";

        // When
        MenuItem menuItem = new MenuItem(name, description, price, restaurantOnly, imagePath);

        // Then
        assertNotNull(menuItem);
        assertEquals(name, menuItem.getName());
        assertTrue(menuItem.getRestaurantOnly());
        assertEquals(price, menuItem.getPrice());
    }

    @Test
    void shouldCreateMenuItemForDelivery() {
        // Given
        String name = "Hambúrguer Artesanal";
        String description = "Hambúrguer com carne artesanal, disponível para delivery";
        Double price = 32.90;
        Boolean restaurantOnly = false;
        String imagePath = "/images/hamburger.jpg";

        // When
        MenuItem menuItem = new MenuItem(name, description, price, restaurantOnly, imagePath);

        // Then
        assertNotNull(menuItem);
        assertEquals(name, menuItem.getName());
        assertFalse(menuItem.getRestaurantOnly());
        assertEquals(price, menuItem.getPrice());
    }
}

