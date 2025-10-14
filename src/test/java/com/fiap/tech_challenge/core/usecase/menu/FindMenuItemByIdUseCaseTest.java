package com.fiap.tech_challenge.core.usecase.menu;

import com.fiap.tech_challenge.core.domain.restaurant.MenuItem;
import com.fiap.tech_challenge.core.repository.MenuItemRepository;
import com.fiap.tech_challenge.core.usecase.menu_item.FindMenuItemByIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindMenuItemByIdUseCaseTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    private FindMenuItemByIdUseCase findMenuItemByIdUseCase;

    @BeforeEach
    void setUp() {
        findMenuItemByIdUseCase = new FindMenuItemByIdUseCase(menuItemRepository);
    }

    @Test
    void shouldFindMenuItemByIdSuccessfully() {
        Long menuItemId = 1L;
        MenuItem menuItem = new MenuItem(
                menuItemId,
                "Pizza Margherita",
                "Pizza clássica com molho de tomate, mussarela e manjericão fresco",
                45.90,
                false,
                "/images/pizza-margherita.jpg",
                1L
        );

        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.of(menuItem));
        Optional<MenuItem> result = findMenuItemByIdUseCase.execute(menuItemId);
        assertTrue(result.isPresent());
        assertEquals(menuItemId, result.get().getId());
        assertEquals("Pizza Margherita", result.get().getName());
        assertEquals("Pizza clássica com molho de tomate, mussarela e manjericão fresco", result.get().getDescription());
        assertEquals(45.90, result.get().getPrice());
        assertFalse(result.get().getRestaurantOnly());
        assertEquals("/images/pizza-margherita.jpg", result.get().getImagePath());

        verify(menuItemRepository, times(1)).findById(menuItemId);
    }

    @Test
    void shouldReturnEmptyOptionalWhenItemNotFound() {
        Long menuItemId = 999L;

        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.empty());
        Optional<MenuItem> result = findMenuItemByIdUseCase.execute(menuItemId);
        assertFalse(result.isPresent());
        assertTrue(result.isEmpty());

        verify(menuItemRepository, times(1)).findById(menuItemId);
    }

    @Test
    void shouldFindRestaurantOnlyItem() {
        Long menuItemId = 2L;
        MenuItem restaurantOnlyItem = new MenuItem(
                menuItemId,
                "Café Expresso",
                "Café expresso tradicional, servido apenas no local",
                4.50,
                true,
                "/images/cafe-expresso.jpg",
                1L
        );

        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.of(restaurantOnlyItem));
        Optional<MenuItem> result = findMenuItemByIdUseCase.execute(menuItemId);
        assertTrue(result.isPresent());
        assertTrue(result.get().getRestaurantOnly());
        assertEquals("Café Expresso", result.get().getName());
        assertEquals(4.50, result.get().getPrice());

        verify(menuItemRepository, times(1)).findById(menuItemId);
    }

    @Test
    void shouldFindDeliveryItem() {
        Long menuItemId = 3L;
        MenuItem deliveryItem = new MenuItem(
                menuItemId,
                "Hambúrguer Artesanal",
                "Hambúrguer com carne artesanal, disponível para delivery",
                32.90,
                false,
                "/images/hamburger.jpg",
                1L
        );

        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.of(deliveryItem));
        Optional<MenuItem> result = findMenuItemByIdUseCase.execute(menuItemId);
        assertTrue(result.isPresent());
        assertFalse(result.get().getRestaurantOnly());
        assertEquals("Hambúrguer Artesanal", result.get().getName());
        assertEquals(32.90, result.get().getPrice());

        verify(menuItemRepository, times(1)).findById(menuItemId);
    }

    @Test
    void shouldFindMenuItemWithAllFieldsPopulated() {
        Long menuItemId = 10L;
        MenuItem menuItem = new MenuItem(
                menuItemId,
                "Nome Completo",
                "Descrição Completa",
                99.99,
                true,
                "/images/caminho/completo.jpg",
                1L
        );

        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.of(menuItem));
        Optional<MenuItem> result = findMenuItemByIdUseCase.execute(menuItemId);
        assertTrue(result.isPresent());
        MenuItem foundItem = result.get();
        assertNotNull(foundItem.getId());
        assertNotNull(foundItem.getName());
        assertNotNull(foundItem.getDescription());
        assertNotNull(foundItem.getPrice());
        assertNotNull(foundItem.getRestaurantOnly());
        assertNotNull(foundItem.getImagePath());

        verify(menuItemRepository, times(1)).findById(menuItemId);
    }

    @Test
    void shouldCallRepositoryWithCorrectId() {
        Long menuItemId = 42L;

        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.empty());
        findMenuItemByIdUseCase.execute(menuItemId);
        verify(menuItemRepository, times(1)).findById(menuItemId);
        verify(menuItemRepository, never()).findById(argThat(id -> !id.equals(menuItemId)));
    }

    @Test
    void shouldHandleDifferentIds() {
        Long firstId = 1L;
        Long secondId = 2L;

        MenuItem firstItem = new MenuItem(
                firstId,
                "Item 1",
                "Desc 1",
                10.00,
                false,
                "/img1.jpg",
                1L
        );
        MenuItem secondItem = new MenuItem(
                secondId,
                "Item 2",
                "Desc 2",
                20.00,
                true,
                "/img2.jpg",
                1L
        );

        when(menuItemRepository.findById(firstId)).thenReturn(Optional.of(firstItem));
        when(menuItemRepository.findById(secondId)).thenReturn(Optional.of(secondItem));
        Optional<MenuItem> result1 = findMenuItemByIdUseCase.execute(firstId);
        Optional<MenuItem> result2 = findMenuItemByIdUseCase.execute(secondId);
        assertTrue(result1.isPresent());
        assertTrue(result2.isPresent());
        assertEquals(firstId, result1.get().getId());
        assertEquals(secondId, result2.get().getId());
        assertNotEquals(result1.get().getName(), result2.get().getName());

        verify(menuItemRepository, times(1)).findById(firstId);
        verify(menuItemRepository, times(1)).findById(secondId);
    }
}

