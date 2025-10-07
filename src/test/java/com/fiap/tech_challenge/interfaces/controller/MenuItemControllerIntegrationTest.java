package com.fiap.tech_challenge.interfaces.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class MenuItemControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldCreateMenuItemEndToEnd() throws Exception {
        // Given
        String menuItemJson = """
                {
                    "name": "Pizza Margherita",
                    "description": "Pizza clássica com molho de tomate, mussarela e manjericão fresco",
                    "price": 45.90,
                    "restaurantOnly": false,
                    "imagePath": "/images/pizza-margherita.jpg"
                }
                """;

        // When & Then
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(menuItemJson))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateMenuItemForRestaurantOnlyEndToEnd() throws Exception {
        // Given
        String menuItemJson = """
                {
                    "name": "Café Expresso",
                    "description": "Café expresso tradicional, servido apenas no local",
                    "price": 4.50,
                    "restaurantOnly": true,
                    "imagePath": "/images/cafe-expresso.jpg"
                }
                """;

        // When & Then
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(menuItemJson))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateMenuItemForDeliveryEndToEnd() throws Exception {
        // Given
        String menuItemJson = """
                {
                    "name": "Hambúrguer Artesanal",
                    "description": "Hambúrguer com carne artesanal, disponível para delivery",
                    "price": 32.90,
                    "restaurantOnly": false,
                    "imagePath": "/images/hamburger.jpg"
                }
                """;

        // When & Then
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(menuItemJson))
                .andExpect(status().isOk());
    }

    @Test
    void shouldHandleEmptyRequestBodyEndToEnd() throws Exception {
        // When & Then
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldHandleInvalidJsonEndToEnd() throws Exception {
        // When & Then
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("invalid json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldHandleMissingContentType() throws Exception {
        // Given
        String menuItemJson = """
                {
                    "name": "Pizza Margherita",
                    "description": "Pizza clássica com molho de tomate, mussarela e manjericão fresco",
                    "price": 45.90,
                    "restaurantOnly": false,
                    "imagePath": "/images/pizza-margherita.jpg"
                }
                """;

        // When & Then
        mockMvc.perform(post("/api/menu-item")
                        .content(menuItemJson))
                .andExpect(status().isUnsupportedMediaType());
    }
}
