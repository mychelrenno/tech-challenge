package com.fiap.tech_challenge.core.repository;

import com.fiap.tech_challenge.core.domain.Restaurant;
import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);
    Optional<Restaurant> findById(Long id);
    List<Restaurant> findAll();
    void deleteById(Long id);
    Restaurant update(Restaurant restaurant);
}

