package com.fiap.tech_challenge.core.usecase.customer;

import com.fiap.tech_challenge.core.domain.Customer;
import com.fiap.tech_challenge.core.repository.CustomerRepository;

import java.util.List;

public class ListAllCustomersUseCase {
    private final CustomerRepository customerRepository;

    public ListAllCustomersUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> listAllCustomers(){
        return customerRepository.listAllCustomers();
    }
}
