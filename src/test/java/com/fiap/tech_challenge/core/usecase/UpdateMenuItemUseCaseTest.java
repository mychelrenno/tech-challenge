package com.fiap.tech_challenge.core.usecase;

import com.fiap.tech_challenge.core.domain.restaurant.MenuItem;
import com.fiap.tech_challenge.core.repository.MenuItemRepository;
import com.fiap.tech_challenge.core.usecase.menu_item.UpdateMenuItemUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateMenuItemUseCaseTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    private UpdateMenuItemUseCase updateMenuItemUseCase;

    @BeforeEach
    void setUp() {
        updateMenuItemUseCase = new UpdateMenuItemUseCase(menuItemRepository);
    }

    @Test
    void shouldUpdateMenuItemSuccessfully() {
        MenuItem inputMenuItem = new MenuItem(1L, "Pizza Margherita Atualizada", "Nova descrição da pizza", 55.90, false, "/images/pizza-nova.jpg", 1L);

        MenuItem updatedMenuItem = new MenuItem(1L, "Pizza Margherita Atualizada", "Nova descrição da pizza", 55.90, false, "/images/pizza-nova.jpg", 1L);

        when(menuItemRepository.update(any(MenuItem.class))).thenReturn(updatedMenuItem);
        MenuItem result = updateMenuItemUseCase.execute(inputMenuItem);
        assertNotNull(result);
        assertEquals(inputMenuItem.getId(), result.getId());
        assertEquals(inputMenuItem.getName(), result.getName());
        assertEquals(inputMenuItem.getDescription(), result.getDescription());
        assertEquals(inputMenuItem.getPrice(), result.getPrice());
        assertEquals(inputMenuItem.getRestaurantOnly(), result.getRestaurantOnly());
        assertEquals(inputMenuItem.getImagePath(), result.getImagePath());

        verify(menuItemRepository, times(1)).update(inputMenuItem);
    }

    @Test
    void shouldUpdateMenuItemPrice() {
        MenuItem inputMenuItem = new MenuItem(1L, "Pizza Margherita", "Pizza clássica", 65.90, false, "/images/pizza.jpg", 1L);

        when(menuItemRepository.update(any(MenuItem.class))).thenReturn(inputMenuItem);
        MenuItem result = updateMenuItemUseCase.execute(inputMenuItem);
        assertNotNull(result);
        assertEquals(65.90, result.getPrice());
        verify(menuItemRepository, times(1)).update(inputMenuItem);
    }

    @Test
    void shouldUpdateMenuItemRestaurantOnlyFlag() {
        MenuItem inputMenuItem = new MenuItem(1L, "Café Expresso", "Café tradicional", 4.50, true, "/images/cafe.jpg", 1L);

        when(menuItemRepository.update(any(MenuItem.class))).thenReturn(inputMenuItem);
        MenuItem result = updateMenuItemUseCase.execute(inputMenuItem);
        assertNotNull(result);
        assertTrue(result.getRestaurantOnly());
        verify(menuItemRepository, times(1)).update(inputMenuItem);
    }

    @Test
    void shouldUpdateMenuItemName() {
        MenuItem inputMenuItem = new MenuItem(1L, "Novo Nome do Item", "Descrição original", 45.90, false, "/images/item.jpg", 1L);

        when(menuItemRepository.update(any(MenuItem.class))).thenReturn(inputMenuItem);
        MenuItem result = updateMenuItemUseCase.execute(inputMenuItem);
        assertNotNull(result);
        assertEquals("Novo Nome do Item", result.getName());
        verify(menuItemRepository, times(1)).update(inputMenuItem);
    }

    @Test
    void shouldUpdateMenuItemDescription() {
        MenuItem inputMenuItem = new MenuItem(1L, "Pizza Margherita", "Descrição completamente nova e detalhada", 45.90, false, "/images/pizza.jpg", 1L);

        when(menuItemRepository.update(any(MenuItem.class))).thenReturn(inputMenuItem);
        MenuItem result = updateMenuItemUseCase.execute(inputMenuItem);
        assertNotNull(result);
        assertEquals("Descrição completamente nova e detalhada", result.getDescription());
        verify(menuItemRepository, times(1)).update(inputMenuItem);
    }

    @Test
    void shouldUpdateMenuItemImagePath() {
        MenuItem inputMenuItem = new MenuItem(1L, "Pizza Margherita", "Pizza clássica", 45.90, false, "/images/nova-imagem-pizza.jpg", 1L);

        when(menuItemRepository.update(any(MenuItem.class))).thenReturn(inputMenuItem);
        MenuItem result = updateMenuItemUseCase.execute(inputMenuItem);
        assertNotNull(result);
        assertEquals("/images/nova-imagem-pizza.jpg", result.getImagePath());
        verify(menuItemRepository, times(1)).update(inputMenuItem);
    }

    @Test
    void shouldUpdateFromDeliveryToRestaurantOnly() {
        MenuItem inputMenuItem = new MenuItem(1L, "Café Especial", "Agora disponível apenas no restaurante", 8.90, true, "/images/cafe.jpg", 1L);

        when(menuItemRepository.update(any(MenuItem.class))).thenReturn(inputMenuItem);
        MenuItem result = updateMenuItemUseCase.execute(inputMenuItem);
        assertNotNull(result);
        assertTrue(result.getRestaurantOnly());
        verify(menuItemRepository, times(1)).update(inputMenuItem);
    }

    @Test
    void shouldUpdateFromRestaurantOnlyToDelivery() {
        MenuItem inputMenuItem = new MenuItem(1L, "Pizza Delivery", "Agora disponível para delivery", 45.90, false, "/images/pizza.jpg", 1L);

        when(menuItemRepository.update(any(MenuItem.class))).thenReturn(inputMenuItem);
        MenuItem result = updateMenuItemUseCase.execute(inputMenuItem);
        assertNotNull(result);
        assertFalse(result.getRestaurantOnly());
        verify(menuItemRepository, times(1)).update(inputMenuItem);
    }

    @Test
    void shouldHandleNullValues() {
        MenuItem inputMenuItem = new MenuItem(1L, null, null, null, null, null, null);

        when(menuItemRepository.update(any(MenuItem.class))).thenReturn(inputMenuItem);
        MenuItem result = updateMenuItemUseCase.execute(inputMenuItem);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertNull(result.getName());
        assertNull(result.getDescription());
        assertNull(result.getPrice());
        assertNull(result.getRestaurantOnly());
        assertNull(result.getImagePath());

        verify(menuItemRepository, times(1)).update(inputMenuItem);
    }
}

