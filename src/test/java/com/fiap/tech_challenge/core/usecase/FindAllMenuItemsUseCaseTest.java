package com.fiap.tech_challenge.core.usecase;

import com.fiap.tech_challenge.core.domain.restaurant.MenuItem;
import com.fiap.tech_challenge.core.repository.MenuItemRepository;
import com.fiap.tech_challenge.core.usecase.menu_item.FindAllMenuItemsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindAllMenuItemsUseCaseTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    private FindAllMenuItemsUseCase findAllMenuItemsUseCase;

    @BeforeEach
    void setUp() {
        findAllMenuItemsUseCase = new FindAllMenuItemsUseCase(menuItemRepository);
    }

    @Test
    void shouldFindAllMenuItems() {
        List<MenuItem> menuItems = Arrays.asList(
                new MenuItem(1L, "Pizza Margherita", "Pizza clássica", 45.90, false, "/images/pizza.jpg", 1L),
                new MenuItem(2L, "Café Expresso", "Café tradicional", 4.50, true, "/images/cafe.jpg", 1L),
                new MenuItem(3L, "Hambúrguer", "Hambúrguer artesanal", 32.90, false, "/images/hamburger.jpg", 1L)
        );

        when(menuItemRepository.findAll()).thenReturn(menuItems);
        List<MenuItem> result = findAllMenuItemsUseCase.execute();
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("Pizza Margherita", result.get(0).getName());
        assertEquals("Café Expresso", result.get(1).getName());
        assertEquals("Hambúrguer", result.get(2).getName());

        verify(menuItemRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoItems() {
        List<MenuItem> emptyList = new ArrayList<>();

        when(menuItemRepository.findAll()).thenReturn(emptyList);
        List<MenuItem> result = findAllMenuItemsUseCase.execute();
        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(0, result.size());

        verify(menuItemRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnMultipleMenuItems() {
        List<MenuItem> menuItems = Arrays.asList(
                new MenuItem(1L, "Item 1", "Descrição 1", 10.00, false, "/img1.jpg", 1L),
                new MenuItem(2L, "Item 2", "Descrição 2", 20.00, false, "/img2.jpg", 1L),
                new MenuItem(3L, "Item 3", "Descrição 3", 30.00, true, "/img3.jpg", 1L),
                new MenuItem(4L, "Item 4", "Descrição 4", 40.00, false, "/img4.jpg", 1L),
                new MenuItem(5L, "Item 5", "Descrição 5", 50.00, true, "/img5.jpg", 1L)
        );

        when(menuItemRepository.findAll()).thenReturn(menuItems);
        List<MenuItem> result = findAllMenuItemsUseCase.execute();
        assertNotNull(result);
        assertEquals(5, result.size());

        verify(menuItemRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnBothRestaurantOnlyAndDeliveryItems() {
        MenuItem restaurantOnlyItem = new MenuItem(1L, "Café Expresso", "Apenas no local", 4.50, true, "/images/cafe.jpg", 1L);
        MenuItem deliveryItem = new MenuItem(2L, "Pizza", "Disponível para delivery", 45.90, false, "/images/pizza.jpg", 1L);

        List<MenuItem> menuItems = Arrays.asList(restaurantOnlyItem, deliveryItem);

        when(menuItemRepository.findAll()).thenReturn(menuItems);
        List<MenuItem> result = findAllMenuItemsUseCase.execute();
        assertNotNull(result);
        assertEquals(2, result.size());

        MenuItem firstItem = result.get(0);
        assertTrue(firstItem.getRestaurantOnly());
        assertEquals("Café Expresso", firstItem.getName());

        MenuItem secondItem = result.get(1);
        assertFalse(secondItem.getRestaurantOnly());
        assertEquals("Pizza", secondItem.getName());

        verify(menuItemRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnAllItemsWithCompleteData() {
        MenuItem menuItem = new MenuItem(1L, "Pizza Margherita", "Pizza clássica com molho de tomate, mussarela e manjericão fresco", 45.90, false, "/images/pizza-margherita.jpg", 1L);

        when(menuItemRepository.findAll()).thenReturn(Arrays.asList(menuItem));
        List<MenuItem> result = findAllMenuItemsUseCase.execute();
        assertNotNull(result);
        assertEquals(1, result.size());

        MenuItem returnedItem = result.get(0);
        assertEquals(1L, returnedItem.getId());
        assertEquals("Pizza Margherita", returnedItem.getName());
        assertEquals("Pizza clássica com molho de tomate, mussarela e manjericão fresco", returnedItem.getDescription());
        assertEquals(45.90, returnedItem.getPrice());
        assertFalse(returnedItem.getRestaurantOnly());
        assertEquals("/images/pizza-margherita.jpg", returnedItem.getImagePath());

        verify(menuItemRepository, times(1)).findAll();
    }

    @Test
    void shouldCallRepositoryOnlyOnce() {
        List<MenuItem> menuItems = Arrays.asList(
                new MenuItem(1L, "Item 1", "Desc 1", 10.00, false, "/img1.jpg", 1L)
        );

        when(menuItemRepository.findAll()).thenReturn(menuItems);
        findAllMenuItemsUseCase.execute();
        verify(menuItemRepository, times(1)).findAll();
        verify(menuItemRepository, only()).findAll();
    }
}

