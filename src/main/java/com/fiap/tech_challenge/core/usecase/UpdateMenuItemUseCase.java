package com.fiap.tech_challenge.core.usecase;

import com.fiap.tech_challenge.core.entity.MenuItem;
import com.fiap.tech_challenge.core.repository.MenuItemRepository;

public class UpdateMenuItemUseCase {

    private final MenuItemRepository menuItemRepository;

    public UpdateMenuItemUseCase(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public MenuItem execute(MenuItem menuItem) {
        return menuItemRepository.update(menuItem);
    }
}
