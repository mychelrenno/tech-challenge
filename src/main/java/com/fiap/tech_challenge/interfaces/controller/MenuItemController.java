package com.fiap.tech_challenge.interfaces.controller;

import com.fiap.tech_challenge.core.usecase.CreateMenuItemUseCase;
import com.fiap.tech_challenge.interfaces.dto.MenuItemDto;
import com.fiap.tech_challenge.interfaces.mapper.MenuItemMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu-item")
public class MenuItemController {

    private final CreateMenuItemUseCase createMenuItemUseCase;

    public MenuItemController(CreateMenuItemUseCase createMenuItemUseCase) {
        this.createMenuItemUseCase = createMenuItemUseCase;
    }

    @PostMapping
    public void create(@RequestBody MenuItemDto menuItemDto) {
        var menuItem = createMenuItemUseCase.execute(MenuItemMapper.convertDtoToEntity(menuItemDto));
        
        String itemName = menuItem != null && menuItem.getName() != null ? menuItem.getName() : "Unknown";
        System.out.println("Menu item created: " + itemName);
    }
}



