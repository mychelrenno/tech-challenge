package com.fiap.tech_challenge.core.usecase.user;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.core.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UpdateUserUseCaseTest {
    private UserRepository userRepository;
    private UpdateUserUseCase updateUserUseCase;

    private UserType userType;
    private Address address;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        updateUserUseCase = new UpdateUserUseCase(userRepository);

        userType = new UserType("CUSTOMER");
        address = new Address(null,
                "12345-678",
                null,
                null,
                null,
                null );
    }

    @Test
    void shouldUpdateUserSuccessfully() {
        // Arrange
        Long userId = 1L;

        User existingUser = new User(1L,
                "Alice",
                "alice@example.com",
                "alice123",
                "alice123",
                userType,
                address,
                new Date(),
                true
        );

        User updatedUser = new User(1L,
                "Alice Silva",
                "alice@example.com",
                "alice",
                "alice",
                userType,
                address,
                new Date(),
                true
        );

        when(userRepository.findById(userId)).thenReturn(existingUser);
        when(userRepository.findByUsername(updatedUser.getUsername())).thenReturn(existingUser);
        when(userRepository.findByEmail(updatedUser.getEmail())).thenReturn(existingUser);
        when(userRepository.update(userId, updatedUser)).thenReturn(updatedUser);

        // Act
        User result = updateUserUseCase.execute(userId, updatedUser);

        // Assert
        assertNotNull(result);
        assertEquals("Alice Silva", result.getName());
        verify(userRepository, times(1)).update(userId, updatedUser);
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        // Arrange
        User updatedUser = new User(1L,
                " ",
                "alice@example.com",
                "alice",
                "alice",
                userType,
                address,
                new Date(),
                true
        );

        // Act + Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> updateUserUseCase.execute(1L, updatedUser));

        assertEquals("Name cannot be empty.", ex.getMessage());
        verifyNoInteractions(userRepository);
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {
        // Arrange
        Long userId = 1L;
        User updatedUser = new User(1L,
                "Alice Silva",
                "aliceexample.com",
                "alice",
                "alice",
                userType,
                address,
                new Date(),
                true
        );
        // Act + Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> updateUserUseCase.execute(userId, updatedUser));

        assertEquals("Invalid email provided.", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyRegistered() {
        // Arrange
        Long userId = 1L;

        User updatedUser = new User(1L,
                "Alice",
                "alice@example.com",
                "alice123",
                "alice123",
                userType,
                address,
                new Date(),
                true
        );

        User anotherUser = new User(2L,
                "Bob",
                "alice@example.com",
                "alice",
                "alice",
                userType,
                address,
                new Date(),
                true
        );

        when(userRepository.findByEmail("alice@example.com")).thenReturn(anotherUser);

        // Act + Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> updateUserUseCase.execute(userId, updatedUser));

        assertEquals("E-mail already registered.", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUsernameAlreadyRegistered() {
        // Arrange

        User updatedUser = new User(1L,
                "Alice",
                "alice@example.com",
                "bob",
                "alice123",
                userType,
                address,
                new Date(),
                true
        );

        User anotherUser = new User(2L,
                "Bob",
                "bob@example.com",
                "bob",
                "bob",
                userType,
                address,
                new Date(),
                true
        );

        when(userRepository.findByUsername("bob")).thenReturn(anotherUser);

        // Act + Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> updateUserUseCase.execute(1L, updatedUser));

        assertEquals("Username already registered.", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // Arrange
        Long userId = 99L;
        User updatedUser = new User(userId,
                "New Name",
                "new@example.com",
                "newuser",
                "newuser",
                userType,
                address,
                new Date(),
                true
        );

        when(userRepository.findById(userId)).thenReturn(null);
        when(userRepository.findByUsername("newuser")).thenReturn(null);
        when(userRepository.findByEmail("new@example.com")).thenReturn(null);

        // Act + Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> updateUserUseCase.execute(userId, updatedUser));

        assertEquals("Invalid user provided.", ex.getMessage());
    }
}
