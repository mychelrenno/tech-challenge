package com.fiap.tech_challenge.core.usecase.customer;

import com.fiap.tech_challenge.core.domain.Customer;
import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.core.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateCustomerUseCaseTest {

    private CustomerRepository customerRepository;
    private UpdateCustomerUseCase updateCustomerUseCase;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        updateCustomerUseCase = new UpdateCustomerUseCase(customerRepository);
    }

    @Test
    void mustUpdateCustumerSucessfully() {
        // Arrange
        User user = new User(1L,
                "João Silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                null,
                null,
                new Date(),
                true);

        Long customerId = 1L;
        Customer existingCustomer = new Customer(customerId, "987654321", user);

        Customer updatedData = new Customer(customerId, "123456789", user);

        Customer updatedCustomer = new Customer(customerId, "123456789", user);

        when(customerRepository.findById(customerId)).thenReturn(existingCustomer);
        when(customerRepository.update(customerId, updatedData)).thenReturn(updatedCustomer);

        // Act
        Customer result = updateCustomerUseCase.execute(customerId, updatedData);

        // Assert
        assertNotNull(result);
        assertEquals(customerId, result.getId());
        assertEquals("123456789", result.getDocument());
        verify(customerRepository, times(1)).findById(customerId);
        verify(customerRepository, times(1)).update(customerId, updatedData);
    }

    @Test
    void mustThrowExceptionWhenCustumerNotFound() {
        // Arrange
        Long customerId = 99L;
        when(customerRepository.findById(customerId)).thenReturn(null);

        Customer updatedCustomerData = new Customer(1L, "987654321", null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> updateCustomerUseCase.execute(customerId, updatedCustomerData)
        );

        assertEquals("Customer not found on system.", exception.getMessage());
        verify(customerRepository, times(1)).findById(customerId);
        verify(customerRepository, never()).update(any(), any());
    }

    @Test
    void mustThrowExceptionWhenDocumentEmpty() {
        // Arrange
        Customer customer = new Customer(1L, "", null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> updateCustomerUseCase.validateDocument(customer.getDocument())
        );

        assertEquals("Document cannot be empty.", exception.getMessage());
    }

    @Test
    void mustThrowExceptionWhenNameEmpty() {
        // Arrange
        User user = new User(1L,
                "",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                null,
                null,
                new Date(),
                true);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> updateCustomerUseCase.validateUser(user)
        );

        assertEquals("Name cannot be empty.", exception.getMessage());
    }

    @Test
    void mustThrowExceptionWhenInvalidEmail() {
        // Arrange
        User user = new User(1L,
                "João Silva",
                "aaaaaaaaaaaaaaaaaaaaaa",
                "joaosilva",
                "123456",
                null,
                null,
                new Date(),
                true);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> updateCustomerUseCase.validateUser(user)
        );

        assertEquals("Invalid email provided.", exception.getMessage());
    }

    @Test
    void mustThrowExceptionWhenCustumerDisabled() {
        // Arrange
        User user = new User(1L,
                "João Silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                null,
                null,
                new Date(),
                false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> updateCustomerUseCase.validateUser(user)
        );

        assertEquals("User not active.", exception.getMessage());
    }
}

