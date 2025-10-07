package com.fiap.tech_challenge.core.usecase.user;

import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.core.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ChangeUserPasswordUseCaseTest {

    private UserRepository userRepository;
    private ChangeUserPasswordUseCase changeUserPasswordUseCase;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        changeUserPasswordUseCase = new ChangeUserPasswordUseCase(userRepository);
    }

    @Test
    void shouldChangeActiveUserPassword() {
        // Arrange
        Long userId = 1L;
        String oldPassword = "123";
        String newPassword = "456";

        User user = mock(User.class);
        when(user.getActive()).thenReturn(true);
        when(user.getPassword()).thenReturn(oldPassword);
        when(userRepository.findById(userId)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        // Act
        User result = changeUserPasswordUseCase.execute(userId, oldPassword, newPassword);

        // Assert
        assertEquals(user, result);
        verify(user).changePassword(newPassword);
        verify(userRepository).save(user);
    }

    @Test
    void shouldNotChangeActiveUserPasswordCauseIncorrectPassword() {
        // Arrange
        Long userId = 1L;
        String oldPassword = "123";
        String wrongOldPassword = "000";
        String newPassword = "456";

        User user = mock(User.class);
        when(user.getActive()).thenReturn(true);
        when(user.getPassword()).thenReturn(oldPassword);
        when(userRepository.findById(userId)).thenReturn(user);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                changeUserPasswordUseCase.execute(userId, wrongOldPassword, newPassword));

        assertEquals("Old password not match user's password.", exception.getMessage());
        verify(user, never()).changePassword(anyString());
        verify(userRepository, never()).save(any());
    }

    @Test
    void testChangePassword_UserInactive() {
        // Arrange
        Long userId = 1L;
        String oldPassword = "123";
        String newPassword = "456";

        User user = mock(User.class);
        when(user.getActive()).thenReturn(false);
        when(userRepository.findById(userId)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        // Act
        User result = changeUserPasswordUseCase.execute(userId, oldPassword, newPassword);

        // Assert
        assertEquals(user, result);
        verify(user, never()).changePassword(anyString());
        verify(userRepository).save(user);
    }
}
