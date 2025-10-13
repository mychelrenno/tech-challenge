package com.fiap.tech_challenge.infrastructure.configuration;

import com.fiap.tech_challenge.core.usecase.usertype.*;
import com.fiap.tech_challenge.infrastructure.repository.UserTypeRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserTypeUseCaseConfiguration {

    private final UserTypeRepositoryJpa userTypeRepositoryJpa;

    public UserTypeUseCaseConfiguration(UserTypeRepositoryJpa userTypeRepositoryJpa) {
        this.userTypeRepositoryJpa = userTypeRepositoryJpa;
    }

    @Bean
    public CreateUserTypeUseCase makeCreateUserTypeUseCase() {
        return new CreateUserTypeUseCase(userTypeRepositoryJpa);
    }

    @Bean
    public ListAllUserTypeUseCase makeListAllUserTypeUseCase() {
        return new ListAllUserTypeUseCase(userTypeRepositoryJpa);
    }

    @Bean
    public UpdateUserTypeUseCase makeUpdateUserTypeUseCase() {
        return new UpdateUserTypeUseCase(userTypeRepositoryJpa);
    }

    @Bean
    public DeleteUserTypeUseCase makeDeleteUserTypeUseCase() {
        return new DeleteUserTypeUseCase(userTypeRepositoryJpa);
    }

    @Bean
    public FindUserTypeByIdUseCase makeFindByIdUseCase() {
        return new FindUserTypeByIdUseCase(userTypeRepositoryJpa);
    }

}
