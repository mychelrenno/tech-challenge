package com.fiap.tech_challenge.core.usecase.menu_item;

import com.fiap.tech_challenge.core.domain.MenuItem;
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
