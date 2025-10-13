package com.fiap.tech_challenge.core.usecase;

import com.fiap.tech_challenge.core.domain.restaurant.MenuItem;
import com.fiap.tech_challenge.core.repository.MenuItemRepository;
import com.fiap.tech_challenge.core.usecase.menu_item.CreateMenuItemUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateMenuItemUseCaseTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    private CreateMenuItemUseCase createMenuItemUseCase;

    @BeforeEach
    void setUp() {
        createMenuItemUseCase = new CreateMenuItemUseCase(menuItemRepository);
    }

    @Test
    void shouldCreateMenuItemSuccessfully() {
        // Given
        MenuItem inputMenuItem = new MenuItem(
                "Pizza Margherita",
                "Pizza clássica com molho de tomate, mussarela e manjericão fresco",
                45.90,
                false,
                "/images/pizza-margherita.jpg"
        );

        MenuItem savedMenuItem = new MenuItem(
                "Pizza Margherita",
                "Pizza clássica com molho de tomate, mussarela e manjericão fresco",
                45.90,
                false,
                "/images/pizza-margherita.jpg"
        );

        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(savedMenuItem);

        // When
        MenuItem result = createMenuItemUseCase.execute(inputMenuItem);

        // Then
        assertNotNull(result);
        assertEquals(inputMenuItem.getName(), result.getName());
        assertEquals(inputMenuItem.getDescription(), result.getDescription());
        assertEquals(inputMenuItem.getPrice(), result.getPrice());
        assertEquals(inputMenuItem.getRestaurantOnly(), result.getRestaurantOnly());
        assertEquals(inputMenuItem.getImagePath(), result.getImagePath());

        verify(menuItemRepository, times(1)).save(inputMenuItem);
    }

    @Test
    void shouldCreateMenuItemForRestaurantOnly() {
        // Given
        MenuItem inputMenuItem = new MenuItem(
                "Café Expresso",
                "Café expresso tradicional, servido apenas no local",
                4.50,
                true,
                "/images/cafe-expresso.jpg"
        );

        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(inputMenuItem);

        // When
        MenuItem result = createMenuItemUseCase.execute(inputMenuItem);

        // Then
        assertNotNull(result);
        assertEquals("Café Expresso", result.getName());
        assertTrue(result.getRestaurantOnly());
        assertEquals(4.50, result.getPrice());

        verify(menuItemRepository, times(1)).save(inputMenuItem);
    }

    @Test
    void shouldCreateMenuItemForDelivery() {
        // Given
        MenuItem inputMenuItem = new MenuItem(
                "Hambúrguer Artesanal",
                "Hambúrguer com carne artesanal, disponível para delivery",
                32.90,
                false,
                "/images/hamburger.jpg"
        );

        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(inputMenuItem);

        // When
        MenuItem result = createMenuItemUseCase.execute(inputMenuItem);

        // Then
        assertNotNull(result);
        assertEquals("Hambúrguer Artesanal", result.getName());
        assertFalse(result.getRestaurantOnly());
        assertEquals(32.90, result.getPrice());

        verify(menuItemRepository, times(1)).save(inputMenuItem);
    }

    @Test
    void shouldHandleNullValues() {
        // Given
        MenuItem inputMenuItem = new MenuItem(null, null, null, null, null);

        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(inputMenuItem);

        // When
        MenuItem result = createMenuItemUseCase.execute(inputMenuItem);

        // Then
        assertNotNull(result);
        assertNull(result.getName());
        assertNull(result.getDescription());
        assertNull(result.getPrice());
        assertNull(result.getRestaurantOnly());
        assertNull(result.getImagePath());

        verify(menuItemRepository, times(1)).save(inputMenuItem);
    }
}

