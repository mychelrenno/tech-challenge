package com.fiap.tech_challenge.core.usecase.customer;

import com.fiap.tech_challenge.core.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteCustomerUseCaseTest {

    private CustomerRepository customerRepository;
    private DeleteCustomerUseCase deleteCustomerUseCase;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        deleteCustomerUseCase = new DeleteCustomerUseCase(customerRepository);
    }

    @Test
    void mustReturnTrueWhenDeleteIsSuccessfully() {
        Long customerId = 1L;
        when(customerRepository.delete(customerId)).thenReturn(true);

        boolean result = deleteCustomerUseCase.delete(customerId);

        assertTrue(result);
        verify(customerRepository, times(1)).delete(customerId);
    }

    @Test
    void mustReturnFalseWhenDeleteFail() {
        Long customerId = 2L;
        when(customerRepository.delete(customerId)).thenReturn(false);

        boolean result = deleteCustomerUseCase.delete(customerId);

        assertFalse(result);
        verify(customerRepository, times(1)).delete(customerId);
    }

    @Test
    void mustReturnFalseWhenCustomerIdIsNull() {
        boolean result = deleteCustomerUseCase.delete(null);

        assertFalse(result);
        verify(customerRepository, times(1)).delete(null);
    }
}
