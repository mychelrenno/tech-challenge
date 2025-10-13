package com.fiap.tech_challenge.core.usecase.customer;

import com.fiap.tech_challenge.core.domain.Customer;
import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.core.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateCustomerUseCaseTest {

    private CustomerRepository customerRepository;
    private CreateCustomerUseCase createCustomerUseCase;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        createCustomerUseCase = new CreateCustomerUseCase(customerRepository);
    }

    @Test
    void mustCreateCustumerSucessfully() {
        User user = new User(1L,
                "João Silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                null,
                null,
                new Date(),
                true);

        Customer customer = new Customer(1L, "987654321", user);

        Customer savedCustomer = new Customer(1L, "987654321", user);

        when(customerRepository.save(customer)).thenReturn(savedCustomer);

        Customer result = createCustomerUseCase.execute(customer);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("987654321", result.getDocument());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void mustThrowExceptionWhenDocumentIsNull() {
        Customer customer = new Customer(1L, null, null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> createCustomerUseCase.execute(customer)
        );

        assertEquals("Document cannot be empty.", exception.getMessage());
        verify(customerRepository, never()).save(any());
    }

    @Test
    void mustThrowExceptionWhenDocumentIsEmpty() {
        Customer customer = new Customer(1L, "   ", null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> createCustomerUseCase.execute(customer)
        );

        assertEquals("Document cannot be empty.", exception.getMessage());
        verify(customerRepository, never()).save(any());
    }

    @Test
    void mustThrowExceptionWhenUserIsNull() {
        Customer customer = new Customer(1L, "123456789", null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> createCustomerUseCase.execute(customer)
        );

        assertEquals("User's info cannot be empty.", exception.getMessage());
        verify(customerRepository, never()).save(any());
    }
}
