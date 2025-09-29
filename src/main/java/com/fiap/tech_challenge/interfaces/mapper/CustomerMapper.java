package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.domain.Customer;
import com.fiap.tech_challenge.infrastructure.entity.CustomerJpa;
import com.fiap.tech_challenge.interfaces.dto.customer.CustomerInputDto;
import com.fiap.tech_challenge.interfaces.dto.customer.CustomerOutputDto;

import java.util.ArrayList;
import java.util.List;

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

    public static Customer convertOutputDtoToEntity(CustomerOutputDto customerOutputDto) {
        return new Customer(
                customerOutputDto.id(),
                customerOutputDto.document(),
                UserMapper.convertDtoToEntity(customerOutputDto.userOutputDto()),
                null
        );
    }

    public static List<Customer> convertJpaToEntityList(List<CustomerJpa> customersJpaList) {
        var customersList = new ArrayList<Customer>();
        customersJpaList.forEach( c -> {
            var customer = new Customer(
                    c.getId(),
                    c.getDocument(),
                    UserMapper.convertJpaToEntity(c.getUserJpa()),
                    null
            );
            customersList.add(customer);
        });
        return customersList;
    }
}
