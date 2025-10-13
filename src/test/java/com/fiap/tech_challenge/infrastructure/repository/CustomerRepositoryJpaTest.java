package com.fiap.tech_challenge.infrastructure.repository;

import com.fiap.tech_challenge.core.domain.Customer;
import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.infrastructure.entity.AddressJpa;
import com.fiap.tech_challenge.infrastructure.entity.CustomerJpa;
import com.fiap.tech_challenge.infrastructure.entity.UserJpa;
import com.fiap.tech_challenge.infrastructure.entity.UserTypeJpa;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaCustomer;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaUser;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaUserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class CustomerRepositoryJpaTest {

    private CustomerRepositoryJpa customerRepositoryJpa;
    private SpringDataJpaCustomer springDataJpaCustomer;
    private SpringDataJpaUser springDataJpaUser;
    private SpringDataJpaUserType springDataJpaUserType;

    @BeforeEach
    void setUp() {
        springDataJpaCustomer = mock(SpringDataJpaCustomer.class);
        springDataJpaUser = mock(SpringDataJpaUser.class);
        springDataJpaUserType = mock(SpringDataJpaUserType.class);
        customerRepositoryJpa = new CustomerRepositoryJpa(springDataJpaCustomer, springDataJpaUser, springDataJpaUserType);
    }

    @Test
    void mustSaveCustomerSuccessfully() {
        // GIVEN
        var userType = new UserType(null, "customer");
        var address = new Address(null, "12345-678", "rua teste", "apartamento teste", "curitiba", "brasil");
        var user = new User(null,
                "joao da silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                userType,
                address,
                new Date(),
                true);
        var customer = new Customer(null, "987654321", user);
        var userTypeJpa = new UserTypeJpa(null, "customer");
        var addressJpa = new AddressJpa(null, "12345-678", "rua teste", "apartamento teste", "curitiba", "brasil");
        var userJpa = new UserJpa(null,
                "joao da silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                userTypeJpa,
                addressJpa,
                new Date(),
                true);
        // WHEN
        when(springDataJpaUser.findByUsername("joaosilva")).thenReturn(Optional.of(userJpa));
        when(springDataJpaCustomer.save(any(CustomerJpa.class)))
                .thenAnswer(invocation -> {
                    CustomerJpa toSave = invocation.getArgument(0);
                    toSave.setId(1L);
                    return toSave;
                });
        var result = customerRepositoryJpa.save(customer);
        //THEN
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(springDataJpaUser, times(1)).findByUsername(any(String.class));
        verify(springDataJpaCustomer, times(1)).save(any(CustomerJpa.class));
    }

    @Test
    void mustSaveCustomerAndUserSuccessfully() {
        // GIVEN
        var userType = new UserType(null, "customer");
        var address = new Address(null, "12345-678", "rua teste", "apartamento teste", "curitiba", "brasil");
        var user = new User(null,
                "joao da silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                userType,
                address,
                new Date(),
                true);
        var customer = new Customer(null, "987654321", user);
        var userTypeJpa = new UserTypeJpa(null, "customer");
        var addressJpa = new AddressJpa(null, "12345-678", "rua teste", "apartamento teste", "curitiba", "brasil");
        var userJpa = new UserJpa(null,
                "joao da silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                userTypeJpa,
                addressJpa,
                new Date(),
                true);
        // WHEN
        when(springDataJpaUser.findByUsername("joaosilva")).thenReturn(Optional.empty());
        when(springDataJpaUserType.findByName("customer")).thenReturn(userTypeJpa);
        when(springDataJpaUser.save(any(UserJpa.class)))
                .thenAnswer(invocation -> {
                    UserJpa toSave = invocation.getArgument(0);
                    toSave.setId(1L);
                    return toSave;
                });
        when(springDataJpaCustomer.save(any(CustomerJpa.class)))
                .thenAnswer(invocation -> {
                    CustomerJpa toSave = invocation.getArgument(0);
                    toSave.setId(1L);
                    return toSave;
                });
        var result = customerRepositoryJpa.save(customer);
        //THEN
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getUser().getId());
        verify(springDataJpaUser, times(1)).findByUsername(any(String.class));
        verify(springDataJpaUserType, times(1)).findByName(any(String.class));
        verify(springDataJpaUser, times(1)).save(any(UserJpa.class));
        verify(springDataJpaCustomer, times(1)).save(any(CustomerJpa.class));
    }

    @Test
    void mustThrowExceptionUserTypeJpaNotFound() {
        // GIVEN
        var userType = new UserType(null, "customer");
        var address = new Address(null, "12345-678", "rua teste", "apartamento teste", "curitiba", "brasil");
        var user = new User(null,
                "joao da silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                userType,
                address,
                new Date(),
                true);
        var customer = new Customer(null, "987654321", user);
        // WHEN
        when(springDataJpaUser.findByUsername("mariaoliveira")).thenReturn(Optional.empty());
        when(springDataJpaUserType.findByName("delivery")).thenReturn(null);
        // THEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            customerRepositoryJpa.save(customer);
        });
    }

    @Test
    void mustDeleteCustomerWithSuccess() {
        // given
        Long customerId = 1L;
        var userTypeJpa = new UserTypeJpa(1L, "customer");
        var addressJpa = new AddressJpa(1L, "12345-678", "rua teste", "apartamento teste", "curitiba", "brasil");
        var userJpa = new UserJpa(1L,
                "joao da silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                userTypeJpa,
                addressJpa,
                new Date(),
                true);
        var customerJpa = new CustomerJpa(1L, "987654321", userJpa);
        // when
        when(springDataJpaCustomer.findById(customerId)).thenReturn(Optional.of(customerJpa));
        when(springDataJpaUser.findById(customerJpa.getUserJpa().getId())).thenReturn(Optional.of(userJpa));
        when(springDataJpaUser.save(customerJpa.getUserJpa())).thenReturn(userJpa);
        Boolean result = customerRepositoryJpa.delete(customerId);
        // then
        assertNotNull(result);
        assertEquals(true, result);
        verify(springDataJpaCustomer, times(1)).findById(any(Long.class));
        verify(springDataJpaUser, times(1)).findById(any(Long.class));
        verify(springDataJpaUser, times(1)).save(any(UserJpa.class));
    }

    @Test
    void mustReturnFalseWhenTryDeleteCustomer() {
        // given
        Long customerId = 999L;
        // when
        when(springDataJpaCustomer.findById(customerId)).thenReturn(Optional.empty());
        Boolean result = customerRepositoryJpa.delete(customerId);
        // then
        assertNotNull(result);
        assertEquals(false, result);
        verify(springDataJpaCustomer, times(1)).findById(any(Long.class));
    }

    @Test
    void mustReturnAllCustomer() {
        //given
        var userTypeJpa = new UserTypeJpa(null, "owner");
        var addressJpa = new AddressJpa(null, "12345-678", "rua teste", "apartamento teste", "curitiba", "brasil");
        var userJpa = new UserJpa(null,
                "joao da silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                userTypeJpa,
                addressJpa,
                new Date(),
                true);
        var customerJpa = new CustomerJpa(null, "987654321", userJpa);

        var userTypeJpa1 = new UserTypeJpa(null, "customer");
        var addressJpa1 = new AddressJpa(null, "12345-678", "rua teste", "apartamento teste", "curitiba", "brasil");
        var userJpa1 = new UserJpa(null,
                "maria do teste",
                "mariateste@teste.com",
                "mariateste",
                "123456",
                userTypeJpa1,
                addressJpa1,
                new Date(),
                true);
        var customerJpa1 = new CustomerJpa(null, "987654321", userJpa1);
        // when
        when(springDataJpaCustomer.findAll()).thenReturn(List.of(customerJpa, customerJpa1));
        var result = customerRepositoryJpa.listAllCustomers();
        // then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(springDataJpaCustomer, times(1)).findAll();
    }

    @Test
    void mustReturnCustomer() {
        // given
        Long customerId = 1L;
        var userTypeJpa = new UserTypeJpa(null, "customer");
        var addressJpa = new AddressJpa(null, "12345-678", "rua teste", "apartamento teste", "curitiba", "brasil");
        var userJpa = new UserJpa(null,
                "joao da silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                userTypeJpa,
                addressJpa,
                new Date(),
                true);
        var customerJpa = new CustomerJpa(1L, "987654321", userJpa);
        // when
        when(springDataJpaCustomer.findById(customerId)).thenReturn(Optional.of(customerJpa));
        var result = customerRepositoryJpa.findById(customerId);
        // then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(springDataJpaCustomer, times(1)).findById(any(Long.class));
    }

    @Test
    void mustUpdateCustomerWithSuccess() {
        // given
        var userType = new UserType(null, "customer");
        var address = new Address(null, "12345-678", "rua teste", "apartamento teste", "curitiba", "brasil");
        var user = new User(null,
                "joao da silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                userType,
                address,
                new Date(),
                true);
        var customer = new Customer(null, "987654321", user);

        var userTypeJpa = new UserTypeJpa(1L, "customer");
        var addressJpa = new AddressJpa(1L, "12345-678", "rua teste", "apartamento teste", "curitiba", "brasil");
        var userJpa = new UserJpa(1L,
                "joao da silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                userTypeJpa,
                addressJpa,
                new Date(),
                true);
        var customerJpa = new CustomerJpa(1L, "987654321", userJpa);
        // when
        when(springDataJpaCustomer.findById(1L)).thenReturn(Optional.of(customerJpa));
        when(springDataJpaUserType.findByName("customer")).thenReturn(userTypeJpa);
        when(springDataJpaUser.findById(1L)).thenReturn(Optional.of(userJpa));
        when(springDataJpaCustomer.save(customerJpa)).thenAnswer(invocation -> {
            CustomerJpa _customer = invocation.getArgument(0);
            _customer.setDocument("654456654");
            return _customer;
        });
        var result = customerRepositoryJpa.update(1L, customer);
        // then
        assertNotNull(result);
        assertEquals("654456654", result.getDocument());
        verify(springDataJpaCustomer, times(1)).findById(any(Long.class));
        verify(springDataJpaUserType, times(1)).findByName(any(String.class));
        verify(springDataJpaUser, times(1)).findById(any(Long.class));
        verify(springDataJpaCustomer, times(1)).save(any(CustomerJpa.class));
    }

    @Test
    void mustThrowExceptionUserTypeNotFound() {
        // given
        var userType = new UserType(null, "customer");
        var address = new Address(null, "12345-678", "rua teste", "apartamento teste", "curitiba", "brasil");
        var user = new User(null,
                "joao da silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                userType,
                address,
                new Date(),
                true);
        var customer = new Customer(null, "987654321", user);

        var userTypeJpa = new UserTypeJpa(1L, "customer");
        var addressJpa = new AddressJpa(1L, "12345-678", "rua teste", "apartamento teste", "curitiba", "brasil");
        var userJpa = new UserJpa(1L,
                "joao da silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                userTypeJpa,
                addressJpa,
                new Date(),
                true);
        var customerJpa = new CustomerJpa(1L, "987654321", userJpa);
        // when
        when(springDataJpaCustomer.findById(1L)).thenReturn(Optional.of(customerJpa));
        when(springDataJpaUserType.findByName("customer")).thenReturn(null);
        when(springDataJpaUser.findById(1L)).thenReturn(Optional.of(userJpa));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            customerRepositoryJpa.update(1L, customer);
        });
        // then
        assertEquals("User type not found on system.", exception.getMessage());
        verify(springDataJpaCustomer, times(1)).findById(any(Long.class));
        verify(springDataJpaUserType, times(1)).findByName(any(String.class));
        verify(springDataJpaUser, times(1)).findById(any(Long.class));
        verifyNoMoreInteractions(springDataJpaCustomer);
    }

    @Test
    void mustThrowExceptionUserNotFound() {
        // given
        var userType = new UserType(null, "customer");
        var address = new Address(null, "12345-678", "rua teste", "apartamento teste", "curitiba", "brasil");
        var user = new User(null,
                "joao da silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                userType,
                address,
                new Date(),
                true);
        var customer = new Customer(null, "987654321", user);

        var userTypeJpa = new UserTypeJpa(1L, "customer");
        var addressJpa = new AddressJpa(1L, "12345-678", "rua teste", "apartamento teste", "curitiba", "brasil");
        var userJpa = new UserJpa(1L,
                "joao da silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                userTypeJpa,
                addressJpa,
                new Date(),
                true);
        var customerJpa = new CustomerJpa(1L, "987654321", userJpa);
        // when
        when(springDataJpaCustomer.findById(1L)).thenReturn(Optional.of(customerJpa));
        when(springDataJpaUserType.findByName("customer")).thenReturn(userTypeJpa);
        when(springDataJpaUser.findById(1L)).thenReturn(Optional.empty());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            customerRepositoryJpa.update(1L, customer);
        });
        // then
        assertEquals("User not found.", exception.getMessage());
        verify(springDataJpaCustomer, times(1)).findById(any(Long.class));
        verify(springDataJpaUserType, times(1)).findByName(any(String.class));
        verify(springDataJpaUser, times(1)).findById(any(Long.class));
        verifyNoMoreInteractions(springDataJpaCustomer);
    }

    @Test
    void mustThrowExceptionCustomerNotFound() {
        // given
        var userType = new UserType(null, "customer");
        var address = new Address(null, "12345-678", "rua teste", "apartamento teste", "curitiba", "brasil");
        var user = new User(null,
                "joao da silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                userType,
                address,
                new Date(),
                true);
        var customer = new Customer(null, "987654321", user);
        // when
        when(springDataJpaCustomer.findById(1L)).thenReturn(Optional.empty());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            customerRepositoryJpa.update(1L, customer);
        });
        // then
        assertEquals("Customer not found.", exception.getMessage());
        verify(springDataJpaCustomer, times(1)).findById(any(Long.class));
        verifyNoMoreInteractions(springDataJpaCustomer);
    }
}
