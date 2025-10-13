package com.fiap.tech_challenge.infrastructure.repository;

import com.fiap.tech_challenge.core.domain.restaurant.MenuItem;
import com.fiap.tech_challenge.infrastructure.entity.MenuItemJpa;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaMenuItem;
import com.fiap.tech_challenge.interfaces.mapper.MenuItemMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MenuItemRepositoryJpaTest {
    private MenuItemRepositoryJpa menuItemRepositoryJpa;
    private SpringDataJpaMenuItem springDataJpaMenuItem;

    @BeforeEach
    void setUp() {
        springDataJpaMenuItem = mock(SpringDataJpaMenuItem.class);
        menuItemRepositoryJpa = new MenuItemRepositoryJpa(springDataJpaMenuItem);
    }

    @Test
    void mustSaveMenuItemWithSuccess() {
        // given
        MenuItem menuItem = new MenuItem(null,
                "pizza",
                "deliciosa",
                new Double("49.90"),
                true,
                "/imagens/imagem-teste.jpg",
                1L);

        MenuItemJpa menuItemJpaSaved = new MenuItemJpa(1L,
                "pizza",
                "deliciosa",
                new Double("49.90"),
                true,
                "/imagens/imagem-teste.jpg",
                1L);

        // when
        when(springDataJpaMenuItem.save(any(MenuItemJpa.class))).thenReturn(menuItemJpaSaved);
        MenuItem result = menuItemRepositoryJpa.save(menuItem);

        // then
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(menuItem.getName(), result.getName());
        verify(springDataJpaMenuItem, times(1)).save(any(MenuItemJpa.class));
    }

    @Test
    void mustReturnAllMenuItemWithSuccess() {
        //given
        MenuItemJpa menuItemJpa1 = new MenuItemJpa(1L,
                "bacon com abacaxi",
                "pizza dos deuses",
                new Double("49.90"),
                true,
                "/imagens/imagem-teste.jpg",
                1L);
        MenuItemJpa menuItemJpa2 = new MenuItemJpa(2L,
                "portuguesa",
                "contem ovo para dar um pump",
                new Double("49.90"),
                true,
                "/imagens/imagem-teste.jpg",
                2L);
        //when
        when(springDataJpaMenuItem.findAll()).thenReturn(List.of(menuItemJpa1, menuItemJpa2));
        List<MenuItem> results = menuItemRepositoryJpa.findAll();
        //then
        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(2, results.size());
        verify(springDataJpaMenuItem, times(1)).findAll();
    }

    @Test
    void mustReturnOneMenuItemWithSuccess() {
        // given
        MenuItemJpa menuItemJpa = new MenuItemJpa(1L,
                "bacon com abacaxi",
                "pizza dos deuses",
                new Double("49.90"),
                true,
                "/imagens/imagem-teste.jpg",
                1L);
        // when
        when(springDataJpaMenuItem.findById(any(Long.class))).thenReturn(Optional.of(menuItemJpa));
        Optional<MenuItem> menuItemOpt = menuItemRepositoryJpa.findById(1L);
        // then
        assertNotNull(menuItemOpt);
        assertTrue(menuItemOpt.isPresent());
        assertEquals(1L, menuItemOpt.get().getId());
        verify(springDataJpaMenuItem, times(1)).findById(any(Long.class));
    }

    @Test
    void mustUpdateWithSuccess() {
        // given
        MenuItem menuItem = new MenuItem(1L,
                "bacon com abacaxi",
                "pizza dos deuses",
                49.90,
                true,
                "/imagens/imagem-teste.jpg",
                1L);

        MenuItemJpa menuItemJpaSaved = new MenuItemJpa(1L,
                "bacon com abacaxi",
                "pizza dos deuses",
                49.90,
                true,
                "/imagens/imagem-teste.jpg",
                1L);
        // when
        when(springDataJpaMenuItem.save(any(MenuItemJpa.class))).thenReturn(menuItemJpaSaved);
        MenuItem menuItemSaved = menuItemRepositoryJpa.update(menuItem);
        // then
        assertNotNull(menuItemSaved);
        assertEquals(1L, menuItemSaved.getId());
        assertEquals("bacon com abacaxi", menuItemSaved.getName());
        assertEquals("pizza dos deuses", menuItemSaved.getDescription());
        assertEquals(49.90, menuItemSaved.getPrice());
        assertEquals("/imagens/imagem-teste.jpg", menuItemSaved.getImagePath());
        verify(springDataJpaMenuItem, times(1)).save(any(MenuItemJpa.class));
    }

    @Test
    void mustDeleteMenuItemByIdWithSuccess() {
        // given
        Long menuItemId = 1L;
        // when
        menuItemRepositoryJpa.deleteById(menuItemId);
        // then
        verify(springDataJpaMenuItem, times(1)).deleteById(menuItemId);
    }

    @Test
    void mustFindMenuItemByRestaurantIdWithSuccess() {
        // given
        Long restaurantId = 1L;
        MenuItemJpa menuItemJpa = new MenuItemJpa(1L,
                "bacon com abacaxi",
                "pizza dos deuses",
                49.90,
                true,
                "/imagens/imagem-teste.jpg",
                restaurantId);

        when(springDataJpaMenuItem.findByRestaurantId(restaurantId)).thenReturn(Optional.of(menuItemJpa));
        // when
        Optional<MenuItem> result = menuItemRepositoryJpa.findByRestaurantId(restaurantId);
        // then
        assertTrue(result.isPresent());
        assertEquals(restaurantId, result.get().getRestaurantId());
        verify(springDataJpaMenuItem, times(1)).findByRestaurantId(restaurantId);
    }

    @Test
    void mustReturnEmptyWhenRestaurantIdNotFound() {
        // given
        Long restaurantId = 999L;
        when(springDataJpaMenuItem.findByRestaurantId(restaurantId)).thenReturn(Optional.empty());
        // when
        Optional<MenuItem> result = menuItemRepositoryJpa.findByRestaurantId(restaurantId);
        // then
        assertTrue(result.isEmpty());
        verify(springDataJpaMenuItem, times(1)).findByRestaurantId(restaurantId);
    }
}
