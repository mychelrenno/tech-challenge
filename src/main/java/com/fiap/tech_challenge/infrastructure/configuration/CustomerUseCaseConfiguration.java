package com.fiap.tech_challenge.infrastructure.configuration;

import com.fiap.tech_challenge.core.repository.CustomerRepository;
import com.fiap.tech_challenge.core.usecase.customer.CreateCustomerUseCase;
import com.fiap.tech_challenge.core.usecase.customer.DeleteCustomerUseCase;
import com.fiap.tech_challenge.core.usecase.customer.ListAllCustomersUseCase;
import com.fiap.tech_challenge.core.usecase.customer.UpdateCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerUseCaseConfiguration {
    private final CustomerRepository customerRepository;

    public CustomerUseCaseConfiguration(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(){
        return new CreateCustomerUseCase(customerRepository);
    }

    @Bean
    public DeleteCustomerUseCase deleteCustomerUseCase(){
        return new DeleteCustomerUseCase(customerRepository);
    }

    @Bean
    public ListAllCustomersUseCase listAllCustomersUseCase(){
        return new ListAllCustomersUseCase(customerRepository);
    }

    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(){
        return new UpdateCustomerUseCase(customerRepository);
    }
}
