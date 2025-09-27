package com.fiap.tech_challenge.infrastructure.repository.jpa;

import com.fiap.tech_challenge.infrastructure.entity.CustomerJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaCustomer extends JpaRepository<CustomerJpa, Long> {
}
