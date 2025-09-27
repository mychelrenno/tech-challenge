package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.domain.Customer;
import com.fiap.tech_challenge.core.domain.order.Order;
import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.infrastructure.entity.CustomerJpa;
import com.fiap.tech_challenge.interfaces.dto.customer.CustomerInputDto;

public class CustomerMapper {
    public static CustomerJpa convertEntityToJpa(Customer customer){
        return new CustomerJpa(
                customer.getDocument(),
                UserMapper.convertEntityToJpa(customer.getUser())
        );
    }

    public static Customer convertJpaToEntity(CustomerJpa customerJpa) {
        return new Customer(
                customerJpa.getId(),
                customerJpa.getDocument(),
                UserMapper.convertJpaToEntity(customerJpa.getUserJpa()),
                null
        );
    }

    public static Customer convertDtoToEntity(CustomerInputDto customerInputDto) {
        return new Customer(
                null,
                customerInputDto.document(),
                UserMapper.convertDtoToEntity(customerInputDto.userInputDto()),
                null
        );
    }
}
