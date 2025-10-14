package com.fiap.tech_challenge.core.usecase.user;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.core.exception.InvalidAttributeException;
import com.fiap.tech_challenge.core.exception.ResourceNotFoundException;
import com.fiap.tech_challenge.core.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ChangeUserPasswordUseCaseTest {

    private UserRepository userRepository;
    private ChangeUserPasswordUseCase changeUserPasswordUseCase;
    private UserType userType;
    private Address address;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        changeUserPasswordUseCase = new ChangeUserPasswordUseCase(userRepository);
        userType = new UserType("ADMIN");
        address = new Address(null,
                "12345-678",
                null,
                null,
                null,
                null );
    }

    @Test
    void shouldChangeActiveUserPassword() {
        // Arrange
        Long userId = 1L;
        String oldPassword = "123";
        String newPassword = "456";

        User user = new User(userId,
                "John Doe",
                "john@email.com",
                "johndoe",
                oldPassword,
                userType,
                address,
                new Date(),
                true
        );
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findById(userId)).thenReturn(user);
        // Act
        Boolean result = changeUserPasswordUseCase.execute(userId, oldPassword, newPassword);
        // Assert
        assertEquals(true, result);
        assertEquals(newPassword, user.getPassword()); // password was updated
        verify(userRepository).save(user); // ensure save was called
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
        // Arrange: mock an inactive user
        User inactiveUser = new User(1L,
                "John",
                "john@email.com",
                "john123",
                "123456",
                userType,
                address,
                new Date(),
                false
        );
        when(userRepository.findById(1L)).thenReturn(inactiveUser);

        // Act & Assert: executing should throw an exception
        assertThrows(ResourceNotFoundException.class, () ->
                changeUserPasswordUseCase.execute(1L, "123456", "456")
        );

        // Verify interactions
        verify(userRepository).findById(1L); // must be called
        verify(userRepository, never()).changePassword(anyLong(), anyString(), anyString()); // should NOT call changePassword
        verify(userRepository, never()).save(any(User.class)); // save should NOT be called
    }
}
