package com.fiap.tech_challenge.core.usecase.menu_item;

import com.fiap.tech_challenge.core.domain.restaurant.MenuItem;
import com.fiap.tech_challenge.core.repository.MenuItemRepository;

import java.util.List;

public class FindAllMenuItemsUseCase {

    private final MenuItemRepository menuItemRepository;

    public FindAllMenuItemsUseCase(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> execute() {
        return menuItemRepository.findAll();
    }
}
