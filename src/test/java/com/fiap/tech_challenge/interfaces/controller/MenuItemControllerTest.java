package com.fiap.tech_challenge.interfaces.controller;

import com.fiap.tech_challenge.core.domain.restaurant.MenuItem;
import com.fiap.tech_challenge.core.usecase.menu_item.CreateMenuItemUseCase;
import com.fiap.tech_challenge.core.usecase.menu_item.DeleteMenuItemUseCase;
import com.fiap.tech_challenge.core.usecase.menu_item.FindAllMenuItemsUseCase;
import com.fiap.tech_challenge.core.usecase.menu_item.FindMenuItemByIdUseCase;
import com.fiap.tech_challenge.core.usecase.menu_item.FindMenuItemByRestaurantIdUseCase;
import com.fiap.tech_challenge.core.usecase.menu_item.UpdateMenuItemUseCase;
import com.fiap.tech_challenge.interfaces.dto.MenuItemDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

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

@MockitoBean
    private FindMenuItemByRestaurantIdUseCase findMenuItemByRestaurantIdUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateMenuItemSuccessfully() throws Exception {
        MenuItemDto menuItemDto = new MenuItemDto(
                null,
                "Pizza Margherita",
                "Pizza clássica com molho de tomate, mussarela e manjericão fresco",
                45.90,
                false,
                "/images/pizza-margherita.jpg",
                1L
        );

        MenuItem savedMenuItem = new MenuItem(
                1L,
                "Pizza Margherita",
                "Pizza clássica com molho de tomate, mussarela e manjericão fresco",
                45.90,
                false,
                "/images/pizza-margherita.jpg",
                1L
        );

        when(createMenuItemUseCase.execute(any(MenuItem.class))).thenReturn(savedMenuItem);
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuItemDto)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateMenuItemForRestaurantOnly() throws Exception {
        MenuItemDto menuItemDto = new MenuItemDto(
                null,
                "Café Expresso",
                "Café expresso tradicional, servido apenas no local",
                4.50,
                true,
                "/images/cafe-expresso.jpg",
                1L
        );

        MenuItem savedMenuItem = new MenuItem(
                2L,
                "Café Expresso",
                "Café expresso tradicional, servido apenas no local",
                4.50,
                true,
                "/images/cafe-expresso.jpg",
                1L
        );

        when(createMenuItemUseCase.execute(any(MenuItem.class))).thenReturn(savedMenuItem);
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuItemDto)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateMenuItemForDelivery() throws Exception {
        MenuItemDto menuItemDto = new MenuItemDto(
                null,
                "Hambúrguer Artesanal",
                "Hambúrguer com carne artesanal, disponível para delivery",
                32.90,
                false,
                "/images/hamburger.jpg",
                2L
        );

        MenuItem savedMenuItem = new MenuItem(
                3L,
                "Hambúrguer Artesanal",
                "Hambúrguer com carne artesanal, disponível para delivery",
                32.90,
                false,
                "/images/hamburger.jpg",
                2L
        );

        when(createMenuItemUseCase.execute(any(MenuItem.class))).thenReturn(savedMenuItem);
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuItemDto)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldHandleEmptyRequestBody() throws Exception {
MenuItem emptyMenuItem = new MenuItem(null, null, null, null, null, null, null);
        
        when(createMenuItemUseCase.execute(any(MenuItem.class))).thenReturn(emptyMenuItem);
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldHandleNullValues() throws Exception {
MenuItemDto menuItemDto = new MenuItemDto(null, null, null, null, null, null, null);

        MenuItem savedMenuItem = new MenuItem(null, null, null, null, null, null, null);

        when(createMenuItemUseCase.execute(any(MenuItem.class))).thenReturn(savedMenuItem);
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuItemDto)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldHandleInvalidJson() throws Exception {
        mockMvc.perform(post("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{invalid}"))
                .andExpect(status().isBadRequest());
    }
    @Test
    void shouldFindAllMenuItems() throws Exception {
        List<MenuItem> menuItems = Arrays.asList(
                new MenuItem(1L, "Pizza Margherita", "Pizza clássica", 45.90, false, "/images/pizza.jpg", 1L),
                new MenuItem(2L, "Café Expresso", "Café tradicional", 4.50, true, "/images/cafe.jpg", 1L),
                new MenuItem(3L, "Hambúrguer", "Hambúrguer artesanal", 32.90, false, "/images/hamburger.jpg", 1L)
        );

        when(findAllMenuItemsUseCase.execute()).thenReturn(menuItems);
        mockMvc.perform(get("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is("Pizza Margherita")))
                .andExpect(jsonPath("$[1].name", is("Café Expresso")))
                .andExpect(jsonPath("$[2].name", is("Hambúrguer")));

        verify(findAllMenuItemsUseCase, times(1)).execute();
    }

    @Test
    void shouldReturnEmptyListWhenNoItems() throws Exception {
        when(findAllMenuItemsUseCase.execute()).thenReturn(Arrays.asList());
        mockMvc.perform(get("/api/menu-item")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        verify(findAllMenuItemsUseCase, times(1)).execute();
    }
    @Test
    void shouldFindMenuItemById() throws Exception {
        Long menuItemId = 1L;
        MenuItem menuItem = new MenuItem(menuItemId, "Pizza Margherita", "Pizza clássica com molho de tomate, mussarela e manjericão fresco", 45.90, false, "/images/pizza-margherita.jpg", 1L);

        when(findMenuItemByIdUseCase.execute(menuItemId)).thenReturn(Optional.of(menuItem));
        mockMvc.perform(get("/api/menu-item/{id}", menuItemId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Pizza Margherita")))
                .andExpect(jsonPath("$.description", is("Pizza clássica com molho de tomate, mussarela e manjericão fresco")))
                .andExpect(jsonPath("$.price", is(45.90)))
                .andExpect(jsonPath("$.restaurantOnly", is(false)))
                .andExpect(jsonPath("$.imagePath", is("/images/pizza-margherita.jpg")));

        verify(findMenuItemByIdUseCase, times(1)).execute(menuItemId);
    }

    @Test
    void shouldReturn404WhenItemNotFound() throws Exception {
        Long menuItemId = 999L;

        when(findMenuItemByIdUseCase.execute(menuItemId)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/menu-item/{id}", menuItemId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(findMenuItemByIdUseCase, times(1)).execute(menuItemId);
    }

    @Test
    void shouldFindRestaurantOnlyItemById() throws Exception {
        Long menuItemId = 2L;
        MenuItem menuItem = new MenuItem(menuItemId, "Café Expresso", "Café tradicional", 4.50, true, "/images/cafe.jpg", 1L);

        when(findMenuItemByIdUseCase.execute(menuItemId)).thenReturn(Optional.of(menuItem));
        mockMvc.perform(get("/api/menu-item/{id}", menuItemId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.restaurantOnly", is(true)));

        verify(findMenuItemByIdUseCase, times(1)).execute(menuItemId);
    }
    @Test
    void shouldUpdateMenuItemSuccessfully() throws Exception {
        Long menuItemId = 1L;
        MenuItemDto menuItemDto = new MenuItemDto(null, "Pizza Margherita Atualizada", "Nova descrição", 55.90, false, "/images/pizza-nova.jpg", 1L);

        MenuItem updatedMenuItem = new MenuItem(menuItemId, "Pizza Margherita Atualizada", "Nova descrição", 55.90, false, "/images/pizza-nova.jpg", 1L);

        when(updateMenuItemUseCase.execute(any(MenuItem.class))).thenReturn(updatedMenuItem);
        mockMvc.perform(put("/api/menu-item/{id}", menuItemId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuItemDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Pizza Margherita Atualizada")))
                .andExpect(jsonPath("$.description", is("Nova descrição")))
                .andExpect(jsonPath("$.price", is(55.90)));

        verify(updateMenuItemUseCase, times(1)).execute(any(MenuItem.class));
    }

    @Test
    void shouldUpdateMenuItemPrice() throws Exception {
        Long menuItemId = 1L;
        MenuItemDto menuItemDto = new MenuItemDto(null, "Pizza Margherita", "Pizza clássica", 65.90, false, "/images/pizza.jpg", 1L);

        MenuItem updatedMenuItem = new MenuItem(menuItemId, "Pizza Margherita", "Pizza clássica", 65.90, false, "/images/pizza.jpg", 1L);

        when(updateMenuItemUseCase.execute(any(MenuItem.class))).thenReturn(updatedMenuItem);
        mockMvc.perform(put("/api/menu-item/{id}", menuItemId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuItemDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(65.90)));

        verify(updateMenuItemUseCase, times(1)).execute(any(MenuItem.class));
    }

    @Test
    void shouldUpdateMenuItemRestaurantOnlyFlag() throws Exception {
        Long menuItemId = 1L;
        MenuItemDto menuItemDto = new MenuItemDto(null, "Café Expresso", "Agora disponível apenas no restaurante", 4.50, true, "/images/cafe.jpg", 1L);

        MenuItem updatedMenuItem = new MenuItem(menuItemId, "Café Expresso", "Agora disponível apenas no restaurante", 4.50, true, "/images/cafe.jpg", 1L);

        when(updateMenuItemUseCase.execute(any(MenuItem.class))).thenReturn(updatedMenuItem);
        mockMvc.perform(put("/api/menu-item/{id}", menuItemId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuItemDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.restaurantOnly", is(true)));

        verify(updateMenuItemUseCase, times(1)).execute(any(MenuItem.class));
    }
    @Test
    void shouldDeleteMenuItemSuccessfully() throws Exception {
        Long menuItemId = 1L;

        doNothing().when(deleteMenuItemUseCase).execute(menuItemId);
        mockMvc.perform(delete("/api/menu-item/{id}", menuItemId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(deleteMenuItemUseCase, times(1)).execute(menuItemId);
    }

    @Test
    void shouldDeleteMultipleMenuItems() throws Exception {
        Long firstId = 1L;
        Long secondId = 2L;

        doNothing().when(deleteMenuItemUseCase).execute(anyLong());
        mockMvc.perform(delete("/api/menu-item/{id}", firstId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mockMvc.perform(delete("/api/menu-item/{id}", secondId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(deleteMenuItemUseCase, times(1)).execute(firstId);
        verify(deleteMenuItemUseCase, times(1)).execute(secondId);
    }

    @Test
    void shouldReturnNoContentWhenDeletingNonExistentItem() throws Exception {
        Long nonExistentId = 999L;

        doNothing().when(deleteMenuItemUseCase).execute(nonExistentId);
        mockMvc.perform(delete("/api/menu-item/{id}", nonExistentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(deleteMenuItemUseCase, times(1)).execute(nonExistentId);
    }
}
