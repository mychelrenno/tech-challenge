package com.fiap.tech_challenge.infrastructure.repository.jpa;

import com.fiap.tech_challenge.infrastructure.entity.UserTypeJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaUserType extends JpaRepository<UserTypeJpa, Long> {
    UserTypeJpa findByName(String name);
}
