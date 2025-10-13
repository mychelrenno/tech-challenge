package com.fiap.tech_challenge.core.usecase.customer;

import com.fiap.tech_challenge.core.domain.Customer;
import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.core.repository.CustomerRepository;

public class UpdateCustomerUseCase {
    private final CustomerRepository customerRepository;

    public UpdateCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer execute(Long customerId, Customer updatedCustomerData){
        // check customer's id
        Customer existingCustomer = customerRepository.findById(customerId);
        if(existingCustomer==null){
            throw new IllegalArgumentException("Customer not found on system.");
        } else {
            // check customer's data
            validateDocument(updatedCustomerData.getDocument());
            validateUser(updatedCustomerData.getUser());
            // update customer's data
            return customerRepository.update(customerId, updatedCustomerData);
        }
    }

    public void validateDocument(String document){
        if (document == null || document.isBlank()) {
            throw new IllegalArgumentException("Document cannot be empty.");
        }
    }

    public void validateUser(User user){
        if (user.getName() == null || user.getName().isBlank()){
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (!user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email provided.");
        }
        if(!user.getActive()){
            throw new IllegalArgumentException("User not active.");
        }
    }
}
