package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.infrastructure.entity.AddressJpa;
import com.fiap.tech_challenge.infrastructure.entity.UserJpa;
import com.fiap.tech_challenge.infrastructure.entity.UserTypeJpa;
import com.fiap.tech_challenge.interfaces.dto.AddressDto;
import com.fiap.tech_challenge.interfaces.dto.UserTypeDto;
import com.fiap.tech_challenge.interfaces.dto.user.UserInputDto;
import com.fiap.tech_challenge.interfaces.dto.user.UserOutputDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class UserMapperTest {
    private MockedStatic<UserTypeMapper> userTypeMapperMock;
    private MockedStatic<AddressMapper> addressMapperMock;

    private final UserTypeDto userTypeDto = new UserTypeDto(1L, "ADMIN");
    private final UserType userType = new UserType(1L, "ADMIN");
    private final UserTypeJpa userTypeJpa = new UserTypeJpa(1L, "ADMIN");

    private final AddressDto addressDto = new AddressDto("00000000","Street A", "12345-000", "City", "State");
    private final Address address = new Address("00000000","Street A", "12345-000", "City", "State");
    private final AddressJpa addressJpa = new AddressJpa("00000000", "Street A", "12345-000", "City", "State");

    @BeforeEach
    void setUp() {
        userTypeMapperMock = mockStatic(UserTypeMapper.class);
        addressMapperMock = mockStatic(AddressMapper.class);
    }

    @AfterEach
    void tearDown() {
        userTypeMapperMock.close();
        addressMapperMock.close();
    }

    @Test
    void shouldConvertUserInputDtoToDomainEntity() {
        // Arrange
        UserInputDto inputDto = new UserInputDto(
                "John Doe",
                "john@example.com",
                "johndoe",
                "password123",
                userTypeDto,
                addressDto
        );

        userTypeMapperMock.when(() -> UserTypeMapper.convertDtoToDomain(userTypeDto)).thenReturn(userType);
        addressMapperMock.when(() -> AddressMapper.convertDtoToEntity(addressDto)).thenReturn(address);

        // Act
        User result = UserMapper.convertDtoToEntity(inputDto);

        // Assert
        assertNotNull(result);
        assertNull(result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());
        assertEquals("johndoe", result.getUsername());
        assertEquals("password123", result.getPassword());
        assertEquals(userType, result.getUserType());
        assertEquals(address, result.getAddress());
        assertTrue(result.getActive());
        assertNotNull(result.getLastUpdateDate());
    }

    @Test
    void shouldReturnNullWhenUserInputDtoIsNull() {
        assertNull(UserMapper.convertDtoToEntity((UserInputDto) null));
    }

    @Test
    void shouldConvertUserOutputDtoToDomainEntity() {
        // Arrange
        Date date = new Date();
        UserOutputDto outputDto = new UserOutputDto(
                10L,
                "Jane Doe",
                "jane@example.com",
                "janedoe",
                userTypeDto,
                addressDto,
                date,
                true
        );

        userTypeMapperMock.when(() -> UserTypeMapper.convertDtoToDomain(userTypeDto)).thenReturn(userType);
        addressMapperMock.when(() -> AddressMapper.convertDtoToEntity(addressDto)).thenReturn(address);

        // Act
        User result = UserMapper.convertDtoToEntity(outputDto);

        // Assert
        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("Jane Doe", result.getName());
        assertEquals("jane@example.com", result.getEmail());
        assertEquals("janedoe", result.getUsername());
        assertNull(result.getPassword());
        assertEquals(userType, result.getUserType());
        assertEquals(address, result.getAddress());
        assertEquals(date, result.getLastUpdateDate());
        assertTrue(result.getActive());
    }

    @Test
    void shouldReturnNullWhenUserOutputDtoIsNull() {
        assertNull(UserMapper.convertDtoToEntity((UserOutputDto) null));
    }

    @Test
    void shouldConvertDomainEntityToJpaEntity() {
        // Arrange
        User user = new User(
                5L,
                "Alice",
                "alice@example.com",
                "alice123",
                "secret",
                userType,
                address,
                new Date(),
                true
        );

        userTypeMapperMock.when(() -> UserTypeMapper.convertDomainToJpa(userType)).thenReturn(userTypeJpa);
        addressMapperMock.when(() -> AddressMapper.convertEntityToJpa(address)).thenReturn(addressJpa);

        // Act
        UserJpa result = UserMapper.convertEntityToJpa(user);

        // Assert
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getPassword(), result.getPassword());
        assertEquals(userTypeJpa, result.getUserTypeJpa());
        assertEquals(addressJpa, result.getAddressJpa());
        assertTrue(result.getActive());
        assertNotNull(result.getLastUpdateDate());
    }

    @Test
    void shouldReturnNullWhenUserIsNull() {
        assertNull(UserMapper.convertEntityToJpa(null));
    }

    @Test
    void shouldConvertJpaEntityToDomainEntity() {
        // Arrange
        Date date = new Date();
        UserJpa userJpa = new UserJpa(
                99L,
                "Bob",
                "bob@example.com",
                "bobuser",
                "123",
                userTypeJpa,
                addressJpa,
                date,
                true
        );

        userTypeMapperMock.when(() -> UserTypeMapper.convertJpaToDomain(userTypeJpa)).thenReturn(userType);
        addressMapperMock.when(() -> AddressMapper.convertJpaToEntity(addressJpa)).thenReturn(address);

        // Act
        User result = UserMapper.convertJpaToEntity(userJpa);

        // Assert
        assertNotNull(result);
        assertEquals(99L, result.getId());
        assertEquals("Bob", result.getName());
        assertEquals("bob@example.com", result.getEmail());
        assertEquals("bobuser", result.getUsername());
        assertEquals("123", result.getPassword());
        assertEquals(userType, result.getUserType());
        assertEquals(address, result.getAddress());
        assertEquals(date, result.getLastUpdateDate());
        assertTrue(result.getActive());
    }

    @Test
    void shouldReturnNullWhenUserJpaIsNull() {
        assertNull(UserMapper.convertJpaToEntity(null));
    }
}
