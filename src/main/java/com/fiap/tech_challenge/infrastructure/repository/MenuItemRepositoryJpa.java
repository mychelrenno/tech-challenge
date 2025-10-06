package com.fiap.tech_challenge.infrastructure.repository;

import com.fiap.tech_challenge.core.entity.MenuItem;
import com.fiap.tech_challenge.core.repository.MenuItemRepository;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaMenuItem;
import com.fiap.tech_challenge.interfaces.mapper.MenuItemMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MenuItemRepositoryJpa implements MenuItemRepository {

    private final SpringDataJpaMenuItem springDataJpaMenuItem;

    public MenuItemRepositoryJpa(SpringDataJpaMenuItem springDataJpaMenuItem) {
        this.springDataJpaMenuItem = springDataJpaMenuItem;
    }

    @Override
    public MenuItem save(MenuItem menuItem) {
        var menuItemJpa = MenuItemMapper.convertEntityToJpa(menuItem);
        var _menuItemJpa = springDataJpaMenuItem.save(menuItemJpa);
        var _menuItem = MenuItemMapper.convertJpaToEntity(_menuItemJpa);
        return _menuItem;
    }
}



