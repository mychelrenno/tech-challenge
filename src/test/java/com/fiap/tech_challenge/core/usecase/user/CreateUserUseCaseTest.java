package com.fiap.tech_challenge.core.usecase.user;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.core.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateUserUseCaseTest {

    private UserRepository userRepository;
    private CreateUserUseCase createUserUseCase;

    private UserType userType;
    private Address address;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        createUserUseCase = new CreateUserUseCase(userRepository);

        userType = new UserType("ADMIN");
        address = new Address(null,
                "12345-678",
                null,
                null,
                null,
                null );
    }

    @Test
    void shouldSaveUserWhenValid() {
        User user = new User(null,
                "John",
                "john@email.com",
                "john123",
                "123456",
                userType,
                address,
                new Date(),
                true
                );

        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        when(userRepository.findByUsername(user.getUsername())).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);

        User result = createUserUseCase.execute(user);

        assertEquals(user, result);
        verify(userRepository).save(user);
    }

    @Test
    void shouldThrowWhenUsernameIsNull() {
        User user = new User(null,
                "John",
                "john@email.com",
                null,
                "123456",
                userType,
                address,
                new Date(),
                true
        );

        Exception ex = assertThrows(IllegalArgumentException.class, () -> createUserUseCase.execute(user));
        assertEquals("Username cannot be empty.", ex.getMessage());
    }

    @Test
    void shouldThrowWhenUsernameIsBlank() {
        User user = new User(null,
                "John",
                "john@email.com",
                "   ",
                "123456",
                userType,
                address,
                new Date(),
                true
        );
        Exception ex = assertThrows(IllegalArgumentException.class, () -> createUserUseCase.execute(user));
        assertEquals("Username cannot be empty.", ex.getMessage());
    }

    @Test
    void shouldThrowWhenEmailIsEmpty() {
        User user = new User(null,
                "John",
                "",
                "john",
                "123456",
                userType,
                address,
                new Date(),
                true
        );
        Exception ex = assertThrows(IllegalArgumentException.class, () -> createUserUseCase.execute(user));
        assertEquals("Invalid email provided.", ex.getMessage());
    }

    @Test
    void shouldThrowWhenEmailIsInvalid() {
        User user = new User(null,
                "John",
                "johngmail.com",
                "john",
                "123456",
                userType,
                address,
                new Date(),
                true
        );
        Exception ex = assertThrows(IllegalArgumentException.class, () -> createUserUseCase.execute(user));
        assertEquals("Invalid email provided.", ex.getMessage());
    }

    @Test
    void shouldThrowWhenPasswordIsEmpty() {
        User user = new User(null,
                "John",
                "john@gmail.com",
                "john",
                "",
                userType,
                address,
                new Date(),
                true
        );
        Exception ex = assertThrows(IllegalArgumentException.class, () -> createUserUseCase.execute(user));
        assertEquals("Password cannot be empty.", ex.getMessage());
    }

    // Using UserRepository
    @Test
    void shouldThrowWhenEmailAlreadyExists() {
        User user = new User(null,
                "John",
                "john@gmail.com",
                "john",
                "john",
                userType,
                address,
                new Date(),
                true
        );
        when(userRepository.findByEmail(user.getEmail())).thenReturn(new User());
        Exception ex = assertThrows(IllegalArgumentException.class, () -> createUserUseCase.execute(user));
        assertEquals("E-mail already registered.", ex.getMessage());
    }

    @Test
    void shouldThrowWhenUsernameAlreadyExists() {
        User user = new User(null,
                "John",
                "john@gmail.com",
                "john",
                "john",
                userType,
                address,
                new Date(),
                true
        );
        when(userRepository.findByUsername(user.getUsername())).thenReturn(new User());
        Exception ex = assertThrows(IllegalArgumentException.class, () -> createUserUseCase.execute(user));
        assertEquals("Username already registered.", ex.getMessage());
    }

}
