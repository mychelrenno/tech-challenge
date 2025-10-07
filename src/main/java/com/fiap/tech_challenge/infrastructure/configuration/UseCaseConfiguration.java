package com.fiap.tech_challenge.infrastructure.configuration;

import com.fiap.tech_challenge.core.usecase.*;
import com.fiap.tech_challenge.infrastructure.repository.MenuItemRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    private final MenuItemRepositoryJpa menuItemRepositoryJpa;

    public UseCaseConfiguration(MenuItemRepositoryJpa menuItemRepositoryJpa) {
        this.menuItemRepositoryJpa = menuItemRepositoryJpa;
    }

    @Bean
    public CreateMenuItemUseCase makeCreateMenuItemUseCase() {
        return new CreateMenuItemUseCase(menuItemRepositoryJpa);
    }

    @Bean
    public FindAllMenuItemsUseCase makeFindAllMenuItemsUseCase() {
        return new FindAllMenuItemsUseCase(menuItemRepositoryJpa);
    }

    @Bean
    public FindMenuItemByIdUseCase makeFindMenuItemByIdUseCase() {
        return new FindMenuItemByIdUseCase(menuItemRepositoryJpa);
    }

    @Bean
    public UpdateMenuItemUseCase makeUpdateMenuItemUseCase() {
        return new UpdateMenuItemUseCase(menuItemRepositoryJpa);
    }

    @Bean
    public DeleteMenuItemUseCase makeDeleteMenuItemUseCase() {
        return new DeleteMenuItemUseCase(menuItemRepositoryJpa);
    }
}
