package com.fiap.tech_challenge.infrastructure.configuration;

import com.fiap.tech_challenge.core.usecase.restaurant.CreateRestaurantUseCase;
import com.fiap.tech_challenge.core.usecase.restaurant.DeleteRestaurantUseCase;
import com.fiap.tech_challenge.core.usecase.restaurant.GetRestaurantUseCase;
import com.fiap.tech_challenge.core.usecase.restaurant.UpdateRestaurantUseCase;
import com.fiap.tech_challenge.core.usecase.user.ChangeUserPasswordUseCase;
import com.fiap.tech_challenge.core.usecase.user.CreateUserUseCase;
import com.fiap.tech_challenge.core.usecase.usertype.CreateUserTypeUseCase;
import com.fiap.tech_challenge.infrastructure.repository.AddressRepositoryJpa;
import com.fiap.tech_challenge.infrastructure.repository.RestaurantRepositoryJpa;
import com.fiap.tech_challenge.infrastructure.repository.UserRepositoryJpa;
import com.fiap.tech_challenge.infrastructure.repository.UserTypeRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    private final UserTypeRepositoryJpa userTypeRepositoryJpa;
    private final UserRepositoryJpa userRepositoryJpa;
    private final RestaurantRepositoryJpa restaurantRepositoryJpa;

    public UseCaseConfiguration(UserTypeRepositoryJpa userTypeRepositoryJpa,
                                UserRepositoryJpa userRepositoryJpa,
                                RestaurantRepositoryJpa restaurantRepositoryJpa) {
        this.userTypeRepositoryJpa = userTypeRepositoryJpa;
        this.userRepositoryJpa = userRepositoryJpa;
        this.restaurantRepositoryJpa = restaurantRepositoryJpa;
    }

    @Bean
    public CreateUserTypeUseCase makeCreateUserTypeUseCase() {
        return new CreateUserTypeUseCase(userTypeRepositoryJpa);
    }

    @Bean
    public CreateUserUseCase makeCreateUserUseCase() {
        return new CreateUserUseCase(userRepositoryJpa);
    }

    @Bean
    public ChangeUserPasswordUseCase changeUserPasswordUserUseCase() {
        return new ChangeUserPasswordUseCase(userRepositoryJpa);
    }

    @Bean
    public CreateRestaurantUseCase makeCreateRestaurantUseCase() {
        return new CreateRestaurantUseCase(restaurantRepositoryJpa);
    }

    @Bean
    public UpdateRestaurantUseCase makeUpdateRestaurantUseCase() {
        return new UpdateRestaurantUseCase(restaurantRepositoryJpa);
    }

    @Bean
    public DeleteRestaurantUseCase makeDeleteRestaurantUseCase() {
        return new DeleteRestaurantUseCase(restaurantRepositoryJpa);
    }

    @Bean
    public GetRestaurantUseCase makeGetRestaurantUseCase() {
        return new GetRestaurantUseCase(restaurantRepositoryJpa);
    }

}
