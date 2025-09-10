package com.fiap.tech_challenge.infrastructure.configuration;

import com.fiap.tech_challenge.core.usecase.user.CreateUserUseCase;
import com.fiap.tech_challenge.core.usecase.usertype.CreateUserTypeUseCase;
import com.fiap.tech_challenge.infrastructure.repository.UserRepositoryJpa;
import com.fiap.tech_challenge.infrastructure.repository.UserTypeRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    private final UserTypeRepositoryJpa userTypeRepositoryJpa;
    private final UserRepositoryJpa userRepositoryJpa;

    public UseCaseConfiguration(UserTypeRepositoryJpa userTypeRepositoryJpa, UserRepositoryJpa userRepositoryJpa) {
        this.userTypeRepositoryJpa = userTypeRepositoryJpa;
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @Bean
    public CreateUserTypeUseCase makeCreateUserTypeUseCase() {
        return new CreateUserTypeUseCase(userTypeRepositoryJpa);
    }

    @Bean
    public CreateUserUseCase makeCreateUserUseCase() {
        return new CreateUserUseCase(userRepositoryJpa);
    }

}
