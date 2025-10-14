package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.domain.Customer;
import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.infrastructure.entity.AddressJpa;
import com.fiap.tech_challenge.infrastructure.entity.CustomerJpa;
import com.fiap.tech_challenge.infrastructure.entity.UserJpa;
import com.fiap.tech_challenge.infrastructure.entity.UserTypeJpa;
import com.fiap.tech_challenge.interfaces.dto.AddressDto;
import com.fiap.tech_challenge.interfaces.dto.UserTypeDto;
import com.fiap.tech_challenge.interfaces.dto.customer.CustomerInputDto;
import com.fiap.tech_challenge.interfaces.dto.customer.CustomerOutputDto;
import com.fiap.tech_challenge.interfaces.dto.user.UserInputDto;
import com.fiap.tech_challenge.interfaces.dto.user.UserOutputDto;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerMapperTest {
    private Address createDomainAddress() {
        return new Address("12345", "Main Street", "Apt 10", "New York", "USA");
    }

    private AddressJpa createAddressJpa() {
        return new AddressJpa("12345", "Main Street", "Apt 10", "New York", "USA");
    }

    private AddressDto createAddressDto() {
        return new AddressDto("12345", "Main Street", "Apt 10", "New York", "USA");
    }

    private UserType createDomainUserType() {
        return new UserType(1L, "CUSTOMER");
    }

    private UserTypeJpa createUserTypeJpa() {
        UserTypeJpa type = new UserTypeJpa();
        type.setId(1L);
        type.setName("CUSTOMER");
        return type;
    }

    private User createDomainUser() {
        return new User(1L, "John Doe", "john@example.com", "johndoe", "password123", createDomainUserType(), createDomainAddress(), new Date(), true);
    }

    private UserJpa createUserJpa() {
        UserJpa userJpa = new UserJpa();
        userJpa.setId(1L);
        userJpa.setName("John Doe");
        userJpa.setEmail("john@example.com");
        userJpa.setPassword("password123");
        userJpa.setAddressJpa(createAddressJpa());
        userJpa.setUserTypeJpa(createUserTypeJpa());
        return userJpa;
    }

    private UserInputDto createUserInputDto() {
        return new UserInputDto("John Doe", "john@example.com", "johndoe", "password123", new UserTypeDto(1L, "CLIENT"),createAddressDto());
    }

    private UserOutputDto createUserOutputDto() {
        return new UserOutputDto(1L, "John Doe",
                "john@example.com",
                "johndoe",
                new UserTypeDto(1L, "CLIENT"),
                createAddressDto(),
                new Date(),
                true
        );
    }

    @Test
    void shouldConvertEntityToJpaSuccessfully() {
        Customer customer = new Customer(1L, "12345678900", createDomainUser());

        CustomerJpa customerJpa = CustomerMapper.convertEntityToJpa(customer);

        assertNotNull(customerJpa);
        assertEquals("12345678900", customerJpa.getDocument());
        assertNotNull(customerJpa.getUserJpa());
        assertEquals("John Doe", customerJpa.getUserJpa().getName());
        assertEquals("CUSTOMER", customerJpa.getUserJpa().getUserTypeJpa().getName());
        assertEquals("New York", customerJpa.getUserJpa().getAddressJpa().getCity());
    }

    @Test
    void shouldConvertJpaToEntitySuccessfully() {
        CustomerJpa customerJpa = new CustomerJpa("12345678900", createUserJpa());
        customerJpa.setId(5L);

        Customer customer = CustomerMapper.convertJpaToEntity(customerJpa);

        assertNotNull(customer);
        assertEquals(5L, customer.getId());
        assertEquals("12345678900", customer.getDocument());
        assertNotNull(customer.getUser());
        assertEquals("John Doe", customer.getUser().getName());
        assertEquals("CUSTOMER", customer.getUser().getUserType().getName());
        assertEquals("New York", customer.getUser().getAddress().getCity());
    }

    @Test
    void shouldConvertInputDtoToEntitySuccessfully() {
        CustomerInputDto inputDto = new CustomerInputDto("12345678900", createUserInputDto());
        Customer customer = CustomerMapper.convertDtoToEntity(inputDto);
        assertNotNull(customer);
        assertNull(customer.getId());
        assertEquals("12345678900", customer.getDocument());
        assertEquals("John Doe", customer.getUser().getName());
        assertEquals("john@example.com", customer.getUser().getEmail());
        assertEquals("New York", customer.getUser().getAddress().getCity());
    }

    @Test
    void shouldConvertOutputDtoToEntitySuccessfully() {
        CustomerOutputDto outputDto = new CustomerOutputDto(2L, "99999999999", createUserOutputDto());

        Customer customer = CustomerMapper.convertOutputDtoToEntity(outputDto);

        assertNotNull(customer);
        assertEquals(2L, customer.getId());
        assertEquals("99999999999", customer.getDocument());
        assertEquals("John Doe", customer.getUser().getName());
        assertEquals("john@example.com", customer.getUser().getEmail());
        assertEquals("Main Street", customer.getUser().getAddress().getStreet());
    }

    @Test
    void shouldConvertJpaListToEntityListSuccessfully() {
        CustomerJpa c1 = new CustomerJpa("11111111111", createUserJpa());
        c1.setId(10L);
        CustomerJpa c2 = new CustomerJpa("22222222222", createUserJpa());
        c2.setId(20L);

        List<CustomerJpa> jpaList = List.of(c1, c2);

        List<Customer> result = CustomerMapper.convertJpaToEntityList(jpaList);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(10L, result.getFirst().getId());
        assertEquals("11111111111", result.get(0).getDocument());
        assertEquals("CUSTOMER", result.get(0).getUser().getUserType().getName());
        assertEquals("New York", result.get(1).getUser().getAddress().getCity());
    }

    @Test
    void shouldHandleEmptyJpaListSafely() {
        List<Customer> result = CustomerMapper.convertJpaToEntityList(List.of());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldMaintainDataIntegrityAcrossConversions() {
        Customer original = new Customer(3L, "55555555555", createDomainUser());

        CustomerJpa jpa = CustomerMapper.convertEntityToJpa(original);
        Customer backToEntity = CustomerMapper.convertJpaToEntity(jpa);

        assertEquals(original.getDocument(), backToEntity.getDocument());
        assertEquals(original.getUser().getName(), backToEntity.getUser().getName());
        assertEquals(original.getUser().getAddress().getCity(), backToEntity.getUser().getAddress().getCity());
        assertEquals(original.getUser().getUserType().getName(), backToEntity.getUser().getUserType().getName());
    }
}
