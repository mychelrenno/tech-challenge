package com.fiap.tech_challenge.core.usecase;

import com.fiap.tech_challenge.core.entity.MenuItem;
import com.fiap.tech_challenge.core.repository.MenuItemRepository;

import java.util.Optional;

public class FindMenuItemByIdUseCase {

    private final MenuItemRepository menuItemRepository;

    public FindMenuItemByIdUseCase(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public Optional<MenuItem> execute(Long id) {
        return menuItemRepository.findById(id);
    }
}
