package com.fiap.tech_challenge.infrastructure.configuration;

import com.fiap.tech_challenge.core.repository.CustomerRepository;
import com.fiap.tech_challenge.core.usecase.customer.CreateCustomerUseCase;
import com.fiap.tech_challenge.core.usecase.customer.DeleteCustomerUseCase;
//import com.fiap.tech_challenge.core.usecase.customer.ListAllCustomersUseCase;
//import com.fiap.tech_challenge.core.usecase.customer.UpdateCustomerUseCase;
import com.fiap.tech_challenge.core.usecase.usertype.*;
import com.fiap.tech_challenge.infrastructure.repository.UserTypeRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserTypeUseCaseConfiguration {

    private final UserTypeRepositoryJpa userTypeRepositoryJpa;
    private final CustomerRepository customerRepository;

    public UserTypeUseCaseConfiguration(UserTypeRepositoryJpa userTypeRepositoryJpa,
                                        CustomerRepository customerRepository) {
        this.userTypeRepositoryJpa = userTypeRepositoryJpa;
        this.customerRepository = customerRepository;
    }

    // CRUD UserType
    @Bean
    public CreateUserTypeUseCase makeCreateUserTypeUseCase() {
        return new CreateUserTypeUseCase(userTypeRepositoryJpa);
    }

    @Bean
    public ListAllUserTypeUseCase makeListAllUserTypeUseCase() {
        return new ListAllUserTypeUseCase(userTypeRepositoryJpa);
    }

    @Bean
    public UpdateUserTypeUseCase makeUpdateUserTypeUseCase() {
        return new UpdateUserTypeUseCase(userTypeRepositoryJpa);
    }

    @Bean
    public DeleteUserTypeUseCase makeDeleteUserTypeUseCase() {
        return new DeleteUserTypeUseCase(userTypeRepositoryJpa);
    }

    @Bean
    public FindByIdUseCase makeFindByIdUseCase() {
        return new FindByIdUseCase(userTypeRepositoryJpa);
    }

    // CRUD Customer
    @Bean
    public CreateCustomerUseCase createCustomerUseCase(){
        return new CreateCustomerUseCase(customerRepository);
    }

    @Bean
    public DeleteCustomerUseCase deleteCustomerUseCase(){
        return new DeleteCustomerUseCase(customerRepository);
    }

//    @Bean
//    public ListAllCustomersUseCase listAllCustomersUseCase(){
//        return new ListAllCustomersUseCase(customerRepository);
//    }

//    @Bean
//    public UpdateCustomerUseCase updateCustomerUseCase(){
//        return new UpdateCustomerUseCase(customerRepository);
//    }

}
