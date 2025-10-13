package com.fiap.tech_challenge.infrastructure.repository;

import com.fiap.tech_challenge.core.domain.MenuItem;
import com.fiap.tech_challenge.core.repository.MenuItemRepository;
import com.fiap.tech_challenge.infrastructure.entity.MenuItemJpa;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaMenuItem;
import com.fiap.tech_challenge.interfaces.mapper.MenuItemMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<MenuItem> findAll() {
        List<MenuItemJpa> menuItemJpaList = springDataJpaMenuItem.findAll();
        return menuItemJpaList.stream()
                .map(MenuItemMapper::convertJpaToEntity)
                .toList();
    }

    @Override
    public Optional<MenuItem> findById(Long id) {
        Optional<MenuItemJpa> menuItemJpaOptional = springDataJpaMenuItem.findById(id);
        return menuItemJpaOptional.map(MenuItemMapper::convertJpaToEntity);
    }

    @Override
    public MenuItem update(MenuItem menuItem) {
        var menuItemJpa = MenuItemMapper.convertEntityToJpa(menuItem);
        var _menuItemJpa = springDataJpaMenuItem.save(menuItemJpa);
        var _menuItem = MenuItemMapper.convertJpaToEntity(_menuItemJpa);
        return _menuItem;
    }

    @Override
    public void deleteById(Long id) {
        springDataJpaMenuItem.deleteById(id);
    }

    @Override
    public Optional<MenuItem> findByRestaurantId(Long restaurantId) {
        Optional<MenuItemJpa> menuItemJpaOptional = springDataJpaMenuItem.findByRestaurantId(restaurantId);
        return menuItemJpaOptional.map(MenuItemMapper::convertJpaToEntity);
    }
}



