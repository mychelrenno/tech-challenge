package com.fiap.tech_challenge.core.usecase.restaurant;

import com.fiap.tech_challenge.core.domain.Owner;
import com.fiap.tech_challenge.core.domain.restaurant.Restaurant;
import com.fiap.tech_challenge.core.repository.OwnerRepository;
import com.fiap.tech_challenge.core.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;

public class CreateRestaurantUseCase {
    private final RestaurantRepository restaurantRepository;
    private final OwnerRepository ownerRepository;

    public CreateRestaurantUseCase(RestaurantRepository restaurantRepository, OwnerRepository ownerRepository) {
        this.restaurantRepository = restaurantRepository;
        this.ownerRepository = ownerRepository;
    }

    public Restaurant execute(Restaurant restaurant) {
        Owner owner = ownerRepository.findById(restaurant.getOwner().getId());
        if (owner == null) {
            throw new EntityNotFoundException("Owner not found with id: " + restaurant.getOwner().getId());
        }

        restaurant.setOwner(owner);
        return restaurantRepository.save(restaurant);
    }
}
