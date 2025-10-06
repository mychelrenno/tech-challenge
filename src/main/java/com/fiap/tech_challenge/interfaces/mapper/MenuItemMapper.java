package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.entity.MenuItem;
import com.fiap.tech_challenge.infrastructure.entity.MenuItemJpa;
import com.fiap.tech_challenge.interfaces.dto.MenuItemDto;

public class MenuItemMapper {

    public static MenuItem convertDtoToEntity(MenuItemDto dto) {
        return new MenuItem(
                dto.name(),
                dto.description(),
                dto.price(),
                dto.restaurantOnly(),
                dto.imagePath()
        );
    }

    public static MenuItemJpa convertEntityToJpa(MenuItem menuItem) {
        return new MenuItemJpa(
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getPrice(),
                menuItem.getRestaurantOnly(),
                menuItem.getImagePath()
        );
    }

    public static MenuItem convertJpaToEntity(MenuItemJpa menuItemJpa) {
        return new MenuItem(
                menuItemJpa.getName(),
                menuItemJpa.getDescription(),
                menuItemJpa.getPrice(),
                menuItemJpa.getRestaurantOnly(),
                menuItemJpa.getImagePath()
        );
    }
}



