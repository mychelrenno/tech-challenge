package com.fiap.tech_challenge.infrastructure.repository.jpa;

import com.fiap.tech_challenge.infrastructure.entity.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataJpaUser extends JpaRepository<UserJpa, Long> {
    Optional<UserJpa> findByEmail(String email);
    Optional<UserJpa> findByUsername(String username);
    List<UserJpa> findByActiveTrue();
}
