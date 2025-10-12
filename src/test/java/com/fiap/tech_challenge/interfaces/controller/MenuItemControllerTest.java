package com.fiap.tech_challenge.interfaces.controller;

import com.fiap.tech_challenge.core.entity.MenuItem;
import com.fiap.tech_challenge.core.usecase.CreateMenuItemUseCase;
import com.fiap.tech_challenge.core.usecase.DeleteMenuItemUseCase;
import com.fiap.tech_challenge.core.usecase.FindAllMenuItemsUseCase;
import com.fiap.tech_challenge.core.usecase.FindMenuItemByIdUseCase;
import com.fiap.tech_challenge.core.usecase.UpdateMenuItemUseCase;
import com.fiap.tech_challenge.interfaces.dto.MenuItemDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MenuItemController.class)
class MenuItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateMenuItemUseCase createMenuItemUseCase;

    @MockitoBean
    private FindAllMenuItemsUseCase findAllMenuItemsUseCase;

    @MockitoBean
    private FindMenuItemByIdUseCase findMenuItemByIdUseCase;

    @MockitoBean
    private UpdateMenuItemUseCase updateMenuItemUseCase;

    @MockitoBean
    private DeleteMenuItemUseCase deleteMenuItemUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateMenuItemSuccessfully() throws Exception {
        // Given
        MenuItemDto menuItemDto = new MenuItemDto(
                null,
                "Pizza Margherita",
                "Pizza clássica com molho de tomate, mussarela e manjericão fresco",
                45.90,
                false,
                "/images/pizza-margherita.jpg"
        );

        MenuItem savedMenuItem = new MenuItem(
                1L,
                "Pizza Margherita",
                "Pizza clássica com molho de tomate, mussarela e manjericão fresco",
                45.90,
                false,
                "/images/pizza-margherita.jpg"
        );

        when(createMenuItemUseCase.execute(any(MenuItem.class))).thenReturn(savedMenuItem);

        // When & Then
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuItemDto)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateMenuItemForRestaurantOnly() throws Exception {
        // Given
        MenuItemDto menuItemDto = new MenuItemDto(
                null,
                "Café Expresso",
                "Café expresso tradicional, servido apenas no local",
                4.50,
                true,
                "/images/cafe-expresso.jpg"
        );

        MenuItem savedMenuItem = new MenuItem(
                2L,
                "Café Expresso",
                "Café expresso tradicional, servido apenas no local",
                4.50,
                true,
                "/images/cafe-expresso.jpg"
        );

        when(createMenuItemUseCase.execute(any(MenuItem.class))).thenReturn(savedMenuItem);

        // When & Then
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuItemDto)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateMenuItemForDelivery() throws Exception {
        // Given
        MenuItemDto menuItemDto = new MenuItemDto(
                null,
                "Hambúrguer Artesanal",
                "Hambúrguer com carne artesanal, disponível para delivery",
                32.90,
                false,
                "/images/hamburger.jpg"
        );

        MenuItem savedMenuItem = new MenuItem(
                3L,
                "Hambúrguer Artesanal",
                "Hambúrguer com carne artesanal, disponível para delivery",
                32.90,
                false,
                "/images/hamburger.jpg"
        );

        when(createMenuItemUseCase.execute(any(MenuItem.class))).thenReturn(savedMenuItem);

        // When & Then
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuItemDto)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldHandleEmptyRequestBody() throws Exception {
        // Given
        MenuItem savedMenuItem = new MenuItem(
                99L,
                "Item vazio",
                "Descrição vazia",
                0.0,
                false,
                null
        );
        when(createMenuItemUseCase.execute(any(MenuItem.class))).thenReturn(savedMenuItem);
        // When & Then
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldHandleNullValues() throws Exception {
        // Given
        MenuItemDto menuItemDto = new MenuItemDto(null, null, null, null, null, null);

        MenuItem savedMenuItem = new MenuItem(null, null, null, null, null, null);

        when(createMenuItemUseCase.execute(any(MenuItem.class))).thenReturn(savedMenuItem);

        // When & Then
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuItemDto)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldHandleInvalidJson() throws Exception {
        // When & Then
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("invalid json"))
                .andExpect(status().isBadRequest());
    }
}
