package com.fiap.tech_challenge.infrastructure.configuration;

import com.fiap.tech_challenge.core.usecase.CreateUserTypeUseCase;
import com.fiap.tech_challenge.infrastructure.repository.UserTypeRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    private final UserTypeRepositoryJpa userTypeRepositoryJpa;

    public UseCaseConfiguration(UserTypeRepositoryJpa userTypeRepositoryJpa) {
        this.userTypeRepositoryJpa = userTypeRepositoryJpa;
    }

    @Bean
    public CreateUserTypeUseCase makeCreateUserTypeUseCase() {
        var createUserTypeUseCase = new CreateUserTypeUseCase(userTypeRepositoryJpa);
        return createUserTypeUseCase;
    }
}
