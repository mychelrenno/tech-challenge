package com.fiap.tech_challenge.core.usecase.customer;

import com.fiap.tech_challenge.core.domain.Customer;
import com.fiap.tech_challenge.core.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ListAllCustomersUseCaseTest {

    private CustomerRepository customerRepository;
    private ListAllCustomersUseCase listAllCustomersUseCase;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        listAllCustomersUseCase = new ListAllCustomersUseCase(customerRepository);
    }

    @Test
    void mustReturnCustomerList() {
        Customer customer1 = new Customer(1L, "123456789", null);
        Customer customer2 = new Customer(2L, "987654321", null);
        List<Customer> mockCustomers = Arrays.asList(customer1, customer2);

        when(customerRepository.listAllCustomers()).thenReturn(mockCustomers);

        List<Customer> result = listAllCustomersUseCase.listAllCustomers();

        assertEquals(2, result.size());
        assertEquals(mockCustomers, result);
        verify(customerRepository, times(1)).listAllCustomers();
    }

    @Test
    void mustReturnEmptyList() {
        when(customerRepository.listAllCustomers()).thenReturn(Collections.emptyList());

        List<Customer> result = listAllCustomersUseCase.listAllCustomers();

        assertTrue(result.isEmpty());
        verify(customerRepository, times(1)).listAllCustomers();
    }

}
