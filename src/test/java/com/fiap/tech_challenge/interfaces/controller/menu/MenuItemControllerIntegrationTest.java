package com.fiap.tech_challenge.interfaces.controller.menu;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class MenuItemControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldCreateMenuItemEndToEnd() throws Exception {
        String menuItemJson = """
                {
                    "name": "Pizza Margherita",
                    "description": "Pizza clássica com molho de tomate, mussarela e manjericão fresco",
                    "price": 45.90,
                    "restaurantOnly": false,
                    "imagePath": "/images/pizza-margherita.jpg"
                }
                """;
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(menuItemJson))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateMenuItemForRestaurantOnlyEndToEnd() throws Exception {
        String menuItemJson = """
                {
                    "name": "Café Expresso",
                    "description": "Café expresso tradicional, servido apenas no local",
                    "price": 4.50,
                    "restaurantOnly": true,
                    "imagePath": "/images/cafe-expresso.jpg"
                }
                """;
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(menuItemJson))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateMenuItemForDeliveryEndToEnd() throws Exception {
        String menuItemJson = """
                {
                    "name": "Hambúrguer Artesanal",
                    "description": "Hambúrguer com carne artesanal, disponível para delivery",
                    "price": 32.90,
                    "restaurantOnly": false,
                    "imagePath": "/images/hamburger.jpg"
                }
                """;
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(menuItemJson))
                .andExpect(status().isOk());
    }

    @Test
    void shouldHandleEmptyRequestBodyEndToEnd() throws Exception {
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldHandleInvalidJsonEndToEnd() throws Exception {
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("invalid json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldHandleMissingContentType() throws Exception {
        String menuItemJson = """
                {
                    "name": "Pizza Margherita",
                    "description": "Pizza clássica com molho de tomate, mussarela e manjericão fresco",
                    "price": 45.90,
                    "restaurantOnly": false,
                    "imagePath": "/images/pizza-margherita.jpg"
                }
                """;
        mockMvc.perform(post("/api/menu-item")
                        .content(menuItemJson))
                .andExpect(status().isUnsupportedMediaType());
    }
    @Test
    void shouldFindAllMenuItemsEndToEnd() throws Exception {
        String pizza = """
                {
                    "name": "Pizza Quattro Formaggi",
                    "description": "Pizza com quatro queijos",
                    "price": 52.90,
                    "restaurantOnly": false,
                    "imagePath": "/images/quattro-formaggi.jpg"
                }
                """;

        String cafe = """
                {
                    "name": "Café Latte",
                    "description": "Café com leite",
                    "price": 6.50,
                    "restaurantOnly": true,
                    "imagePath": "/images/latte.jpg"
                }
                """;

        mockMvc.perform(post("/api/menu-item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pizza));

        mockMvc.perform(post("/api/menu-item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cafe));
        mockMvc.perform(get("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(java.util.List.class)))
                .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(2)));
    }

    @Test
    void shouldReturnEmptyListWhenNoItemsEndToEnd() throws Exception {
        mockMvc.perform(get("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(java.util.List.class)));
    }
    @Test
    void shouldFindMenuItemByIdEndToEnd() throws Exception {
        String menuItemJson = """
                {
                    "name": "Lasanha Bolonhesa",
                    "description": "Lasanha com molho bolonhesa",
                    "price": 38.90,
                    "restaurantOnly": false,
                    "imagePath": "/images/lasanha.jpg"
                }
                """;

        MvcResult createResult = mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(menuItemJson))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = createResult.getResponse().getContentAsString();
        Long createdId = new ObjectMapper().readTree(responseBody).get("id").asLong();
        mockMvc.perform(get("/api/menu-item/{id}", createdId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(createdId.intValue())))
                .andExpect(jsonPath("$.name", is("Lasanha Bolonhesa")))
                .andExpect(jsonPath("$.description", is("Lasanha com molho bolonhesa")))
                .andExpect(jsonPath("$.price", is(38.90)))
                .andExpect(jsonPath("$.restaurantOnly", is(false)))
                .andExpect(jsonPath("$.imagePath", is("/images/lasanha.jpg")));
    }

    @Test
    void shouldReturn404WhenItemNotFoundEndToEnd() throws Exception {
        Long nonExistentId = 999999L;
        mockMvc.perform(get("/api/menu-item/{id}", nonExistentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    void shouldUpdateMenuItemEndToEnd() throws Exception {
        String originalMenuItem = """
                {
                    "name": "Risoto Original",
                    "description": "Risoto de funghi",
                    "price": 42.00,
                    "restaurantOnly": false,
                    "imagePath": "/images/risoto.jpg"
                }
                """;

        MvcResult createResult = mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(originalMenuItem))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = createResult.getResponse().getContentAsString();
        Long createdId = new ObjectMapper().readTree(responseBody).get("id").asLong();

        String updatedMenuItem = """
                {
                    "name": "Risoto Atualizado",
                    "description": "Risoto de camarão",
                    "price": 58.00,
                    "restaurantOnly": true,
                    "imagePath": "/images/risoto-camarao.jpg"
                }
                """;
        mockMvc.perform(put("/api/menu-item/{id}", createdId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedMenuItem))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(createdId.intValue())))
                .andExpect(jsonPath("$.name", is("Risoto Atualizado")))
                .andExpect(jsonPath("$.description", is("Risoto de camarão")))
                .andExpect(jsonPath("$.price", is(58.00)))
                .andExpect(jsonPath("$.restaurantOnly", is(true)))
                .andExpect(jsonPath("$.imagePath", is("/images/risoto-camarao.jpg")));

        mockMvc.perform(get("/api/menu-item/{id}", createdId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Risoto Atualizado")))
                .andExpect(jsonPath("$.price", is(58.00)));
    }

    @Test
    void shouldUpdateMenuItemPriceEndToEnd() throws Exception {
        String menuItemJson = """
                {
                    "name": "Suco Natural",
                    "description": "Suco de laranja",
                    "price": 8.00,
                    "restaurantOnly": false,
                    "imagePath": "/images/suco.jpg"
                }
                """;

        MvcResult createResult = mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(menuItemJson))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = createResult.getResponse().getContentAsString();
        Long createdId = new ObjectMapper().readTree(responseBody).get("id").asLong();

        String updatedMenuItem = """
                {
                    "name": "Suco Natural",
                    "description": "Suco de laranja",
                    "price": 12.00,
                    "restaurantOnly": false,
                    "imagePath": "/images/suco.jpg"
                }
                """;
        mockMvc.perform(put("/api/menu-item/{id}", createdId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedMenuItem))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(12.00)));
    }

    @Test
    void shouldUpdateRestaurantOnlyFlagEndToEnd() throws Exception {
        String deliveryItem = """
                {
                    "name": "Sobremesa Especial",
                    "description": "Torta de limão",
                    "price": 15.00,
                    "restaurantOnly": false,
                    "imagePath": "/images/torta.jpg"
                }
                """;

        MvcResult createResult = mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deliveryItem))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = createResult.getResponse().getContentAsString();
        Long createdId = new ObjectMapper().readTree(responseBody).get("id").asLong();
        String restaurantOnlyItem = """
                {
                    "name": "Sobremesa Especial",
                    "description": "Torta de limão",
                    "price": 15.00,
                    "restaurantOnly": true,
                    "imagePath": "/images/torta.jpg"
                }
                """;
        mockMvc.perform(put("/api/menu-item/{id}", createdId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(restaurantOnlyItem))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.restaurantOnly", is(true)));
    }
    @Test
    void shouldDeleteMenuItemEndToEnd() throws Exception {
        String menuItemJson = """
                {
                    "name": "Item a Deletar",
                    "description": "Este item será deletado",
                    "price": 25.00,
                    "restaurantOnly": false,
                    "imagePath": "/images/deletar.jpg"
                }
                """;

        MvcResult createResult = mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(menuItemJson))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = createResult.getResponse().getContentAsString();
        Long createdId = new ObjectMapper().readTree(responseBody).get("id").asLong();

        mockMvc.perform(get("/api/menu-item/{id}", createdId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/api/menu-item/{id}", createdId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        mockMvc.perform(get("/api/menu-item/{id}", createdId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteAndVerifyItemIsGoneEndToEnd() throws Exception {
        String menuItemJson = """
                {
                    "name": "Pizza a Deletar",
                    "description": "Pizza que será removida",
                    "price": 45.00,
                    "restaurantOnly": false,
                    "imagePath": "/images/pizza-deletar.jpg"
                }
                """;

        MvcResult createResult = mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(menuItemJson))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = createResult.getResponse().getContentAsString();
        Long createdId = new ObjectMapper().readTree(responseBody).get("id").asLong();
        mockMvc.perform(delete("/api/menu-item/{id}", createdId))
                .andExpect(status().isNoContent());
        mockMvc.perform(get("/api/menu-item/{id}", createdId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCompleteFullCrudCycleEndToEnd() throws Exception {
        String createJson = """
                {
                    "name": "Item CRUD Completo",
                    "description": "Teste de CRUD completo",
                    "price": 30.00,
                    "restaurantOnly": false,
                    "imagePath": "/images/crud.jpg"
                }
                """;

        MvcResult createResult = mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson))
                .andExpect(status().isOk())
                .andReturn();

        Long itemId = new ObjectMapper().readTree(createResult.getResponse().getContentAsString()).get("id").asLong();
        mockMvc.perform(get("/api/menu-item/{id}", itemId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Item CRUD Completo")));
        String updateJson = """
                {
                    "name": "Item CRUD Atualizado",
                    "description": "Teste de CRUD completo - atualizado",
                    "price": 40.00,
                    "restaurantOnly": true,
                    "imagePath": "/images/crud-updated.jpg"
                }
                """;

        mockMvc.perform(put("/api/menu-item/{id}", itemId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Item CRUD Atualizado")))
                .andExpect(jsonPath("$.price", is(40.00)));
        mockMvc.perform(delete("/api/menu-item/{id}", itemId))
                .andExpect(status().isNoContent());
        mockMvc.perform(get("/api/menu-item/{id}", itemId))
                .andExpect(status().isNotFound());
    }
}
