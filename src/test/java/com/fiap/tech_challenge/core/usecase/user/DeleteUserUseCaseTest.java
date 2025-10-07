package com.fiap.tech_challenge.core.usecase.user;

import com.fiap.tech_challenge.core.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;

public class DeleteUserUseCaseTest {

    private UserRepository userRepository;
    private DeleteUserUseCase deleteUserUseCase;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        deleteUserUseCase = new DeleteUserUseCase(userRepository);
    }

    @Test
    void shouldDeleteUser() {
        // Arrange
        Long userId = 1L;
        when(userRepository.delete(userId)).thenReturn(true);

        // Act
        boolean result = deleteUserUseCase.delete(userId);

        // Assert
        assertTrue(result);
        verify(userRepository, times(1)).delete(userId);
    }

    @Test
    void shouldFailDeleteUser() {
        // Arrange
        Long userId = 2L;
        when(userRepository.delete(userId)).thenReturn(false);

        // Act
        boolean result = deleteUserUseCase.delete(userId);

        // Assert
        assertFalse(result);
        verify(userRepository, times(1)).delete(userId);
    }

    @Test
    void shouldFailDeleteUserCauseNullId() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> deleteUserUseCase.delete(null));
        verify(userRepository, never()).delete(any());
    }
}
