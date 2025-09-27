package com.fiap.tech_challenge.infrastructure.configuration;

import com.fiap.tech_challenge.core.repository.CustomerRepository;
import com.fiap.tech_challenge.core.usecase.customer.CreateCustomerUseCase;
import com.fiap.tech_challenge.core.usecase.customer.DeleteCustomerUseCase;
import com.fiap.tech_challenge.core.usecase.customer.ListAllCustomersUseCase;
import com.fiap.tech_challenge.core.usecase.customer.UpdateCustomerUseCase;
import com.fiap.tech_challenge.core.usecase.restaurant.CreateRestaurantUseCase;
import com.fiap.tech_challenge.core.usecase.restaurant.DeleteRestaurantUseCase;
import com.fiap.tech_challenge.core.usecase.restaurant.GetRestaurantUseCase;
import com.fiap.tech_challenge.core.usecase.restaurant.UpdateRestaurantUseCase;
import com.fiap.tech_challenge.core.usecase.user.*;
import com.fiap.tech_challenge.core.usecase.usertype.CreateUserTypeUseCase;
import com.fiap.tech_challenge.core.usecase.usertype.DeleteUserTypeUseCase;
import com.fiap.tech_challenge.core.usecase.usertype.ListAllUserTypeUseCase;
import com.fiap.tech_challenge.core.usecase.usertype.UpdateUserTypeUseCase;
import com.fiap.tech_challenge.infrastructure.repository.RestaurantRepositoryJpa;
import com.fiap.tech_challenge.infrastructure.repository.UserRepositoryJpa;
import com.fiap.tech_challenge.infrastructure.repository.UserTypeRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    private final UserTypeRepositoryJpa userTypeRepositoryJpa;
    private final UserRepositoryJpa userRepositoryJpa;
    private final RestaurantRepositoryJpa restaurantRepositoryJpa;
    private final CustomerRepository customerRepository;

    public UseCaseConfiguration(UserTypeRepositoryJpa userTypeRepositoryJpa,
                                UserRepositoryJpa userRepositoryJpa,
                                RestaurantRepositoryJpa restaurantRepositoryJpa,
                                CustomerRepository customerRepository) {
        this.userTypeRepositoryJpa = userTypeRepositoryJpa;
        this.userRepositoryJpa = userRepositoryJpa;
        this.restaurantRepositoryJpa = restaurantRepositoryJpa;
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

    // CRUD User
    @Bean
    public CreateUserUseCase makeCreateUserUseCase() {
        return new CreateUserUseCase(userRepositoryJpa);
    }

    @Bean
    public UpdateUserUseCase makeUpdateUserUseCase(){
        return new UpdateUserUseCase(userRepositoryJpa);
    }

    @Bean
    public ChangeUserPasswordUseCase changeUserPasswordUserUseCase() {
        return new ChangeUserPasswordUseCase(userRepositoryJpa);
    }

    @Bean
    public DeleteUserUseCase deleteUserUseCase(){
        return new DeleteUserUseCase(userRepositoryJpa);
    }

    @Bean
    public ListAllActiveUsersUseCase listAllActiveUsersUseCase(){
        return new ListAllActiveUsersUseCase(userRepositoryJpa);
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

    @Bean
    public ListAllCustomersUseCase listAllCustomersUseCase(){
        return new ListAllCustomersUseCase(customerRepository);
    }

    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(){
        return new UpdateCustomerUseCase(customerRepository);
    }

    // CRUD Owner

    // CRUD Restaurant
    @Bean
    public CreateRestaurantUseCase makeCreateRestaurantUseCase() {
        return new CreateRestaurantUseCase(restaurantRepositoryJpa);
    }

    @Bean
    public UpdateRestaurantUseCase makeUpdateRestaurantUseCase() {
        return new UpdateRestaurantUseCase(restaurantRepositoryJpa);
    }

    @Bean
    public DeleteRestaurantUseCase makeDeleteRestaurantUseCase() {
        return new DeleteRestaurantUseCase(restaurantRepositoryJpa);
    }

    @Bean
    public GetRestaurantUseCase makeGetRestaurantUseCase() {
        return new GetRestaurantUseCase(restaurantRepositoryJpa);
    }
}
