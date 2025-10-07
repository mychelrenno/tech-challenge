package com.fiap.tech_challenge.infrastructure.repository.jpa;

import com.fiap.tech_challenge.infrastructure.entity.MenuItemJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SpringDataJpaMenuItem extends JpaRepository<MenuItemJpa, Long> {
}



