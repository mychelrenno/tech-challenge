package com.fiap.tech_challenge.infrastructure.repository.jpa;

import com.fiap.tech_challenge.infrastructure.entity.AddressJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaAddress extends JpaRepository<AddressJpa, Long> {
}
