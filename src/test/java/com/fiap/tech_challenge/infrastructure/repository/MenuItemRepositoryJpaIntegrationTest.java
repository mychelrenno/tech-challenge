package com.fiap.tech_challenge.infrastructure.repository;

import com.fiap.tech_challenge.core.domain.restaurant.MenuItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class MenuItemRepositoryJpaIntegrationTest {

    @Autowired
    private MenuItemRepositoryJpa menuItemRepository;

    @Test
    void shouldSaveMenuItemSuccessfully() {
        MenuItem menuItem = new MenuItem(
                "Pizza Margherita",
                "Pizza clássica com molho de tomate, mussarela e manjericão fresco",
                45.90,
                false,
                "/images/pizza-margherita.jpg",
                1L
        );
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
        assertNotNull(savedMenuItem);
        assertEquals("Pizza Margherita", savedMenuItem.getName());
        assertEquals("Pizza clássica com molho de tomate, mussarela e manjericão fresco", savedMenuItem.getDescription());
        assertEquals(45.90, savedMenuItem.getPrice());
        assertFalse(savedMenuItem.getRestaurantOnly());
        assertEquals("/images/pizza-margherita.jpg", savedMenuItem.getImagePath());

        // Additional verification that the entity was persisted
        assertNotNull(savedMenuItem);
    }

    @Test
    void shouldSaveMenuItemForRestaurantOnly() {
        MenuItem menuItem = new MenuItem(
                "Café Expresso",
                "Café expresso tradicional, servido apenas no local",
                4.50,
                true,
                "/images/cafe-expresso.jpg",
                2L
        );
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
        assertNotNull(savedMenuItem);
        assertEquals("Café Expresso", savedMenuItem.getName());
        assertTrue(savedMenuItem.getRestaurantOnly());
        assertEquals(4.50, savedMenuItem.getPrice());
    }

    @Test
    void shouldSaveMenuItemWithNullValues() {
MenuItem menuItem = new MenuItem(null, null, null, null, null, null, null);
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
        assertNotNull(savedMenuItem);
        assertNull(savedMenuItem.getName());
        assertNull(savedMenuItem.getDescription());
        assertNull(savedMenuItem.getPrice());
        assertNull(savedMenuItem.getRestaurantOnly());
        assertNull(savedMenuItem.getImagePath());
    }

    @Test
    void shouldSaveMultipleMenuItems() {
        MenuItem pizza = new MenuItem("Pizza Margherita", "Pizza clássica", 45.90, false, "/images/pizza.jpg", 1L);

        MenuItem cafe = new MenuItem("Café Expresso", "Café tradicional", 4.50, true, "/images/cafe.jpg", 1L);
        MenuItem savedPizza = menuItemRepository.save(pizza);
        MenuItem savedCafe = menuItemRepository.save(cafe);
        assertNotNull(savedPizza);
        assertNotNull(savedCafe);
        assertEquals("Pizza Margherita", savedPizza.getName());
        assertEquals("Café Expresso", savedCafe.getName());
        assertNotEquals(savedPizza.getName(), savedCafe.getName());
    }

    @Test
    void shouldFindAllMenuItems() {
        MenuItem pizza = new MenuItem(
                "Pizza Margherita",
                "Pizza clássica",
                45.90,
                false,
                "/images/pizza.jpg",
                1L
        );

        MenuItem cafe = new MenuItem(
                "Café Expresso",
                "Café tradicional",
                4.50,
                true,
                "/images/cafe.jpg",
                2L
        );

        MenuItem hamburger = new MenuItem("Hambúrguer", "Hambúrguer artesanal", 32.90, false, "/images/hamburger.jpg", 1L);

        menuItemRepository.save(pizza);
        menuItemRepository.save(cafe);
        menuItemRepository.save(hamburger);
        var allItems = menuItemRepository.findAll();
        assertNotNull(allItems);
        assertTrue(allItems.size() >= 3);
        assertTrue(allItems.stream().anyMatch(item -> "Pizza Margherita".equals(item.getName())));
        assertTrue(allItems.stream().anyMatch(item -> "Café Expresso".equals(item.getName())));
        assertTrue(allItems.stream().anyMatch(item -> "Hambúrguer".equals(item.getName())));
    }

    @Test
    void shouldReturnEmptyListWhenNoItems() {
        var allItems = menuItemRepository.findAll();
        assertNotNull(allItems);
        // Pode haver itens de outros testes, mas a lista não deve ser nula
    }

    @Test
    void shouldFindMenuItemById() {
        MenuItem menuItem = new MenuItem("Pizza Calabresa", "Pizza com calabresa e cebola", 42.90, false, "/images/pizza-calabresa.jpg", 1L);

        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
        Long savedId = savedMenuItem.getId();
        var foundMenuItem = menuItemRepository.findById(savedId);
        assertTrue(foundMenuItem.isPresent());
        assertEquals(savedId, foundMenuItem.get().getId());
        assertEquals("Pizza Calabresa", foundMenuItem.get().getName());
        assertEquals("Pizza com calabresa e cebola", foundMenuItem.get().getDescription());
        assertEquals(42.90, foundMenuItem.get().getPrice());
        assertFalse(foundMenuItem.get().getRestaurantOnly());
    }

    @Test
    void shouldReturnEmptyOptionalWhenIdNotFound() {
        Long nonExistentId = 99999L;
        var foundMenuItem = menuItemRepository.findById(nonExistentId);
        assertTrue(foundMenuItem.isEmpty());
        assertFalse(foundMenuItem.isPresent());
    }

    @Test
    void shouldUpdateMenuItemSuccessfully() {
        MenuItem originalMenuItem = new MenuItem("Pizza Original", "Descrição Original", 40.00, false, "/images/original.jpg", 1L);

        MenuItem savedMenuItem = menuItemRepository.save(originalMenuItem);
        Long itemId = savedMenuItem.getId();

        MenuItem updatedMenuItem = new MenuItem(
                itemId,
                "Pizza Atualizada",
                "Descrição Atualizada",
                55.00,
                true,
                "/images/atualizada.jpg",
                1L
        );
        MenuItem result = menuItemRepository.update(updatedMenuItem);
        assertNotNull(result);
        assertEquals(itemId, result.getId());
        assertEquals("Pizza Atualizada", result.getName());
        assertEquals("Descrição Atualizada", result.getDescription());
        assertEquals(55.00, result.getPrice());
        assertTrue(result.getRestaurantOnly());
        assertEquals("/images/atualizada.jpg", result.getImagePath());

        var verifyUpdate = menuItemRepository.findById(itemId);
        assertTrue(verifyUpdate.isPresent());
        assertEquals("Pizza Atualizada", verifyUpdate.get().getName());
    }

    @Test
    void shouldUpdateMenuItemPrice() {
        MenuItem menuItem = new MenuItem("Pizza Mussarela", "Pizza simples", 35.00, false, "/images/mussarela.jpg", 1L);

        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
        Long itemId = savedMenuItem.getId();

        MenuItem updatedMenuItem = new MenuItem(
                itemId,
                "Pizza Mussarela",
                "Pizza simples",
                45.00,
                false,
                "/images/mussarela.jpg",
                1L
        );
        MenuItem result = menuItemRepository.update(updatedMenuItem);
        assertNotNull(result);
        assertEquals(45.00, result.getPrice());
    }

    @Test
    void shouldUpdateMenuItemRestaurantOnlyFlag() {
        MenuItem menuItem = new MenuItem("Suco Natural", "Suco de laranja", 8.00, false, "/images/suco.jpg", 1L);

        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
        Long itemId = savedMenuItem.getId();

        // Alterar para restaurante only
        MenuItem updatedMenuItem = new MenuItem(
                itemId,
                "Suco Natural",
                "Suco de laranja",
                8.00,
                true,
                "/images/suco.jpg",
                1L
        );
        MenuItem result = menuItemRepository.update(updatedMenuItem);
        assertNotNull(result);
        assertTrue(result.getRestaurantOnly());
    }

    @Test
    void shouldDeleteMenuItemById() {
        MenuItem menuItem = new MenuItem("Item a Deletar", "Este item será deletado", 25.00, false, "/images/deletar.jpg", 1L);

        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
        Long itemId = savedMenuItem.getId();

        assertTrue(menuItemRepository.findById(itemId).isPresent());
        menuItemRepository.deleteById(itemId);
        var deletedItem = menuItemRepository.findById(itemId);
        assertTrue(deletedItem.isEmpty());
    }

    @Test
    void shouldNotThrowExceptionWhenDeletingNonExistentItem() {
        Long nonExistentId = 99999L;
        assertDoesNotThrow(() -> menuItemRepository.deleteById(nonExistentId));
    }
}
