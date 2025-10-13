package com.fiap.tech_challenge.core.usecase.menu_item;

import com.fiap.tech_challenge.core.repository.MenuItemRepository;

public class DeleteMenuItemUseCase {

    private final MenuItemRepository menuItemRepository;

    public DeleteMenuItemUseCase(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public void execute(Long id) {
        menuItemRepository.deleteById(id);
    }
}
