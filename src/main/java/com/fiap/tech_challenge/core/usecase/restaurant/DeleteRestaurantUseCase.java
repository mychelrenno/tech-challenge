package com.fiap.tech_challenge.core.usecase.restaurant;

import com.fiap.tech_challenge.core.repository.RestaurantRepository;

public class DeleteRestaurantUseCase {
    private final RestaurantRepository restaurantRepository;

    public DeleteRestaurantUseCase(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void execute(Long id) {
        restaurantRepository.deleteById(id);
    }
}
