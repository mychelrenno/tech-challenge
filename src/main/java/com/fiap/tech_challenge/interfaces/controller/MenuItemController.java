package com.fiap.tech_challenge.interfaces.controller;

import com.fiap.tech_challenge.core.domain.MenuItem;
import com.fiap.tech_challenge.core.usecase.menu_item.*;
import com.fiap.tech_challenge.interfaces.dto.MenuItemDto;
import com.fiap.tech_challenge.interfaces.mapper.MenuItemMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menu-item")
public class MenuItemController {

    private final CreateMenuItemUseCase createMenuItemUseCase;
    private final FindAllMenuItemsUseCase findAllMenuItemsUseCase;
    private final FindMenuItemByIdUseCase findMenuItemByIdUseCase;
    private final UpdateMenuItemUseCase updateMenuItemUseCase;
    private final DeleteMenuItemUseCase deleteMenuItemUseCase;

    public MenuItemController(CreateMenuItemUseCase createMenuItemUseCase,
                             FindAllMenuItemsUseCase findAllMenuItemsUseCase,
                             FindMenuItemByIdUseCase findMenuItemByIdUseCase,
                             UpdateMenuItemUseCase updateMenuItemUseCase,
                             DeleteMenuItemUseCase deleteMenuItemUseCase) {
        this.createMenuItemUseCase = createMenuItemUseCase;
        this.findAllMenuItemsUseCase = findAllMenuItemsUseCase;
        this.findMenuItemByIdUseCase = findMenuItemByIdUseCase;
        this.updateMenuItemUseCase = updateMenuItemUseCase;
        this.deleteMenuItemUseCase = deleteMenuItemUseCase;
    }

    @PostMapping
    public ResponseEntity<MenuItemDto> create(@RequestBody MenuItemDto menuItemDto) {
        var menuItem = createMenuItemUseCase.execute(MenuItemMapper.convertDtoToEntity(menuItemDto));
        var responseDto = new MenuItemDto(menuItem.getId(), menuItem.getName(), menuItem.getDescription(),
                menuItem.getPrice(), menuItem.getRestaurantOnly(), menuItem.getImagePath());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<MenuItemDto>> findAll() {
        List<MenuItem> menuItems = findAllMenuItemsUseCase.execute();
        List<MenuItemDto> responseDtos = menuItems.stream()
                .map(item -> new MenuItemDto(item.getId(), item.getName(), item.getDescription(),
                        item.getPrice(), item.getRestaurantOnly(), item.getImagePath()))
                .toList();
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemDto> findById(@PathVariable Long id) {
        Optional<MenuItem> menuItem = findMenuItemByIdUseCase.execute(id);
        if (menuItem.isPresent()) {
            MenuItem item = menuItem.get();
            var responseDto = new MenuItemDto(item.getId(), item.getName(), item.getDescription(),
                    item.getPrice(), item.getRestaurantOnly(), item.getImagePath());
            return ResponseEntity.ok(responseDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemDto> update(@PathVariable Long id, @RequestBody MenuItemDto menuItemDto) {
        var menuItem = new MenuItem(id, menuItemDto.name(), menuItemDto.description(),
                menuItemDto.price(), menuItemDto.restaurantOnly(), menuItemDto.imagePath());
        var updatedMenuItem = updateMenuItemUseCase.execute(menuItem);
        var responseDto = new MenuItemDto(updatedMenuItem.getId(), updatedMenuItem.getName(), updatedMenuItem.getDescription(),
                updatedMenuItem.getPrice(), updatedMenuItem.getRestaurantOnly(), updatedMenuItem.getImagePath());
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteMenuItemUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}



