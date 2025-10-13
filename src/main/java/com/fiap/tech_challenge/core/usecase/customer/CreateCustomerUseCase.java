package com.fiap.tech_challenge.core.usecase.customer;

import com.fiap.tech_challenge.core.domain.Customer;
import com.fiap.tech_challenge.core.repository.CustomerRepository;

public class CreateCustomerUseCase {
    private final CustomerRepository customerRepository;

    public CreateCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer execute(Customer customer){
        validateCustomer(customer);
        return customerRepository.save(customer);
    }

    public void validateCustomer(Customer customer){
        if(customer.getDocument() == null || customer.getDocument().isBlank()){
            throw new IllegalArgumentException("Document cannot be empty.");
        }
        if(customer.getUser() == null){
            throw new IllegalArgumentException("User's info cannot be empty.");
        }
    }
}
