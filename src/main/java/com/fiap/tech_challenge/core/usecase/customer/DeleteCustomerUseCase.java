package com.fiap.tech_challenge.core.usecase.customer;

import com.fiap.tech_challenge.core.repository.CustomerRepository;

public class DeleteCustomerUseCase {
    private final CustomerRepository customerRepository;

    public DeleteCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Method: Logic delete
    public boolean delete(Long customerId){
        return customerRepository.delete(customerId);
    }
}
