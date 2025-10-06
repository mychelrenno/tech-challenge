package com.fiap.tech_challenge.infrastructure.repository.jpa;

import com.fiap.tech_challenge.infrastructure.entity.RestaurantJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaRestaurant extends JpaRepository<RestaurantJpa, Long> {
}
