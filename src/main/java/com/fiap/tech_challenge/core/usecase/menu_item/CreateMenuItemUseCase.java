package com.fiap.tech_challenge.core.usecase.menu_item;

import com.fiap.tech_challenge.core.domain.MenuItem;
import com.fiap.tech_challenge.core.repository.MenuItemRepository;

public class CreateMenuItemUseCase {

    private final MenuItemRepository menuItemRepository;

    public CreateMenuItemUseCase(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public MenuItem execute(MenuItem menuItem) {
        var _menuItem = menuItemRepository.save(menuItem);
        return _menuItem;
    }
}



