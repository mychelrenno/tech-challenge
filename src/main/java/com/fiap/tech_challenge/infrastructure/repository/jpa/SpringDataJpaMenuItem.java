package com.fiap.tech_challenge.infrastructure.repository.jpa;

import com.fiap.tech_challenge.infrastructure.entity.MenuItemJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMenuItem extends JpaRepository<MenuItemJpa, Long> {
    Optional<MenuItemJpa> findByRestaurantId(Long restaurantId);
}
