package com.fiap.tech_challenge.core.repository;

import com.fiap.tech_challenge.core.domain.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer save(Customer customer);
    Boolean delete(Long customerId);
    List<Customer> listAllCustomers();
    Customer findById(Long customerId);
    Customer update(Long customerId, Customer customer);
}
