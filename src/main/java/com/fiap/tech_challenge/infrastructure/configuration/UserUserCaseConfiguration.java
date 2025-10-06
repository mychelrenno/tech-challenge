package com.fiap.tech_challenge.infrastructure.configuration;

import com.fiap.tech_challenge.core.usecase.user.*;
import com.fiap.tech_challenge.infrastructure.repository.UserRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUserCaseConfiguration {

    private final UserRepositoryJpa userRepositoryJpa;

    public UserUserCaseConfiguration(UserRepositoryJpa userRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @Bean
    public CreateUserUseCase makeCreateUserUseCase() {
        return new CreateUserUseCase(userRepositoryJpa);
    }

    @Bean
    public UpdateUserUseCase makeUpdateUserUseCase(){
        return new UpdateUserUseCase(userRepositoryJpa);
    }

    @Bean
    public ChangeUserPasswordUseCase changeUserPasswordUserUseCase() {
        return new ChangeUserPasswordUseCase(userRepositoryJpa);
    }

    @Bean
    public DeleteUserUseCase deleteUserUseCase(){
        return new DeleteUserUseCase(userRepositoryJpa);
    }

    @Bean
    public ListAllActiveUsersUseCase listAllActiveUsersUseCase(){
        return new ListAllActiveUsersUseCase(userRepositoryJpa);
    }
}
