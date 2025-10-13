package com.fiap.tech_challenge.core.usecase.user;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.core.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.AssertionsKt.assertNotNull;
import static org.mockito.Mockito.*;

public class ListAllActiveUsersUseCaseTest {
    private UserRepository userRepository;
    private ListAllActiveUsersUseCase listAllActiveUsersUseCase;

    private UserType userType;
    private Address address;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        listAllActiveUsersUseCase = new ListAllActiveUsersUseCase(userRepository);

        userType = new UserType("CUSTOMER");
        address = new Address(null,
                "12345-678",
                null,
                null,
                null,
                null );
    }

    @Test
    void shouldReturnAllActiveUsers() {
        // Arrange
        User user1 = new User(1L,
                "Alice",
                "alice@example.com",
                "alice123",
                "alice123",
                userType,
                address,
                new Date(),
                true
        );

        User user2 = new User(1L,
                "Bob",
                "bob@example.com",
                "bob123",
                "bob123",
                userType,
                address,
                new Date(),
                true
        );

        List<User> activeUsers = List.of(user1, user2);

        when(userRepository.findByActiveTrue()).thenReturn(activeUsers);

        // Act
        List<User> result = listAllActiveUsersUseCase.listAllUsersActive();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(User::getActive));
        verify(userRepository, times(1)).findByActiveTrue();
    }

    @Test
    void shouldReturnEmptyListWhenNoActiveUsersExist() {
        // Arrange
        when(userRepository.findByActiveTrue()).thenReturn(List.of());

        // Act
        List<User> result = listAllActiveUsersUseCase.listAllUsersActive();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(userRepository, times(1)).findByActiveTrue();
    }
}
