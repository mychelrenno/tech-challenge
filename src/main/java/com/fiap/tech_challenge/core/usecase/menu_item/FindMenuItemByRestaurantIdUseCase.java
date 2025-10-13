package com.fiap.tech_challenge.core.usecase.menu_item;

import com.fiap.tech_challenge.core.domain.MenuItem;
import com.fiap.tech_challenge.core.repository.MenuItemRepository;

import java.util.Optional;

public class FindMenuItemByRestaurantIdUseCase {

    private final MenuItemRepository menuItemRepository;

    public FindMenuItemByRestaurantIdUseCase(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public Optional<MenuItem> execute(Long id) {
        return menuItemRepository.findByRestaurantId(id);
    }
}
