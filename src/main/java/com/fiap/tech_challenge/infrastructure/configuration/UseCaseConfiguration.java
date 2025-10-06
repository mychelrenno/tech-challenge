package com.fiap.tech_challenge.infrastructure.configuration;

import com.fiap.tech_challenge.core.usecase.CreateUserTypeUseCase;
import com.fiap.tech_challenge.core.usecase.CreateMenuItemUseCase;
import com.fiap.tech_challenge.infrastructure.repository.UserTypeRepositoryJpa;
import com.fiap.tech_challenge.infrastructure.repository.MenuItemRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    private final UserTypeRepositoryJpa userTypeRepositoryJpa;
    private final MenuItemRepositoryJpa menuItemRepositoryJpa;

    public UseCaseConfiguration(UserTypeRepositoryJpa userTypeRepositoryJpa, MenuItemRepositoryJpa menuItemRepositoryJpa) {
        this.userTypeRepositoryJpa = userTypeRepositoryJpa;
        this.menuItemRepositoryJpa = menuItemRepositoryJpa;
    }

    @Bean
    public CreateUserTypeUseCase makeCreateUserTypeUseCase() {
        var createUserTypeUseCase = new CreateUserTypeUseCase(userTypeRepositoryJpa);
        return createUserTypeUseCase;
    }

    @Bean
    public CreateMenuItemUseCase makeCreateMenuItemUseCase() {
        var createMenuItemUseCase = new CreateMenuItemUseCase(menuItemRepositoryJpa);
        return createMenuItemUseCase;
    }
}
