package com.fiap.tech_challenge.infrastructure.configuration;

import com.fiap.tech_challenge.core.usecase.restaurant.CreateRestaurantUseCase;
import com.fiap.tech_challenge.core.usecase.restaurant.DeleteRestaurantUseCase;
import com.fiap.tech_challenge.core.usecase.restaurant.GetRestaurantUseCase;
import com.fiap.tech_challenge.core.usecase.restaurant.UpdateRestaurantUseCase;
import com.fiap.tech_challenge.infrastructure.repository.RestaurantRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestaurantUseCaseConfiguration {

    private final RestaurantRepositoryJpa restaurantRepositoryJpa;

    public RestaurantUseCaseConfiguration(RestaurantRepositoryJpa restaurantRepositoryJpa) {
        this.restaurantRepositoryJpa = restaurantRepositoryJpa;
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
