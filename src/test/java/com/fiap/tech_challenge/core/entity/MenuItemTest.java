package com.fiap.tech_challenge.core.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MenuItemTest {

    @Test
    void shouldCreateMenuItemWithAllFields() {
        String name = "Pizza Margherita";
        String description = "Pizza clássica com molho de tomate, mussarela e manjericão fresco";
        Double price = 45.90;
        Boolean restaurantOnly = false;
        String imagePath = "/images/pizza-margherita.jpg";
        MenuItem menuItem = new MenuItem(name, description, price, restaurantOnly, imagePath);
        assertNotNull(menuItem);
        assertEquals(name, menuItem.getName());
        assertEquals(description, menuItem.getDescription());
        assertEquals(price, menuItem.getPrice());
        assertEquals(restaurantOnly, menuItem.getRestaurantOnly());
        assertEquals(imagePath, menuItem.getImagePath());
    }

    @Test
    void shouldAllowModificationOfFields() {
        MenuItem menuItem = new MenuItem("Original Name", "Original Description", 10.0, false, "/original.jpg");
        menuItem.setName("New Name");
        menuItem.setDescription("New Description");
        menuItem.setPrice(15.50);
        menuItem.setRestaurantOnly(true);
        menuItem.setImagePath("/new.jpg");
        assertEquals("New Name", menuItem.getName());
        assertEquals("New Description", menuItem.getDescription());
        assertEquals(15.50, menuItem.getPrice());
        assertTrue(menuItem.getRestaurantOnly());
        assertEquals("/new.jpg", menuItem.getImagePath());
    }

    @Test
    void shouldCreateMenuItemForRestaurantOnly() {
        String name = "Café Expresso";
        String description = "Café expresso tradicional, servido apenas no local";
        Double price = 4.50;
        Boolean restaurantOnly = true;
        String imagePath = "/images/cafe-expresso.jpg";
        MenuItem menuItem = new MenuItem(name, description, price, restaurantOnly, imagePath);
        assertNotNull(menuItem);
        assertEquals(name, menuItem.getName());
        assertTrue(menuItem.getRestaurantOnly());
        assertEquals(price, menuItem.getPrice());
    }

    @Test
    void shouldCreateMenuItemForDelivery() {
        String name = "Hambúrguer Artesanal";
        String description = "Hambúrguer com carne artesanal, disponível para delivery";
        Double price = 32.90;
        Boolean restaurantOnly = false;
        String imagePath = "/images/hamburger.jpg";
        MenuItem menuItem = new MenuItem(name, description, price, restaurantOnly, imagePath);
        assertNotNull(menuItem);
        assertEquals(name, menuItem.getName());
        assertFalse(menuItem.getRestaurantOnly());
        assertEquals(price, menuItem.getPrice());
    }
}

