package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.domain.MenuItem;
import com.fiap.tech_challenge.infrastructure.entity.MenuItemJpa;
import com.fiap.tech_challenge.interfaces.dto.MenuItemDto;

public class MenuItemMapper {

    public static MenuItem convertDtoToEntity(MenuItemDto dto) {
        return new MenuItem(
                dto.id(),
                dto.name(),
                dto.description(),
                dto.price(),
                dto.restaurantOnly(),
                dto.imagePath()
        );
    }

    public static MenuItemJpa convertEntityToJpa(MenuItem menuItem) {
        MenuItemJpa menuItemJpa = new MenuItemJpa(
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getPrice(),
                menuItem.getRestaurantOnly(),
                menuItem.getImagePath()
        );
        if (menuItem.getId() != null) {
            menuItemJpa.setId(menuItem.getId());
        }
        return menuItemJpa;
    }

    public static MenuItem convertJpaToEntity(MenuItemJpa menuItemJpa) {
        return new MenuItem(
                menuItemJpa.getId(),
                menuItemJpa.getName(),
                menuItemJpa.getDescription(),
                menuItemJpa.getPrice(),
                menuItemJpa.getRestaurantOnly(),
                menuItemJpa.getImagePath()
        );
    }
}



