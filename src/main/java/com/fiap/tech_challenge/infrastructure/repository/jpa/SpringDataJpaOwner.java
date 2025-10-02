package com.fiap.tech_challenge.infrastructure.repository.jpa;

import com.fiap.tech_challenge.infrastructure.entity.OwnerJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaOwner extends JpaRepository<OwnerJpa, Long> {
}
