package com.fiap.tech_challenge.core.repository;

import com.fiap.tech_challenge.core.domain.Customer;

public interface CustomerRepository {
    Customer save(Customer customer);
    Boolean delete(Long customerId);
}
