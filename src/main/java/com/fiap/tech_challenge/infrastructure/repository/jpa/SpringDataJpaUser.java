package com.fiap.tech_challenge.infrastructure.repository.jpa;

import com.fiap.tech_challenge.infrastructure.entity.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaUser extends JpaRepository<UserJpa, Long> {
}
