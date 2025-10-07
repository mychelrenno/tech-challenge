package com.fiap.tech_challenge.core.usecase;

import com.fiap.tech_challenge.core.entity.MenuItem;
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
