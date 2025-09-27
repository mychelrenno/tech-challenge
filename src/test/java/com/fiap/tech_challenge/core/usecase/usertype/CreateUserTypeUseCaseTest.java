package com.fiap.tech_challenge.core.usecase.usertype;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.exception.ResourceAlreadyExistsException;
import com.fiap.tech_challenge.core.repository.UserTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateUserTypeUseCaseTest {

    private UserTypeRepository userTypeRepository;
    private CreateUserTypeUseCase createUserTypeUseCase;

    @BeforeEach
    void setUp() {
        userTypeRepository = Mockito.mock(UserTypeRepository.class);
        createUserTypeUseCase = new CreateUserTypeUseCase(userTypeRepository);
    }

    @Test
    void mustSaveUserTypeWithoutProblem() throws Exception {
        // Arrange
        UserType newUserType = new UserType("admin");

        when(userTypeRepository.findByName(newUserType)).thenReturn(null);
        when(userTypeRepository.save(newUserType)).thenReturn(newUserType);

        // Act
        UserType result = createUserTypeUseCase.execute(newUserType);

        // Assert
        assertNotNull(result);
        assertEquals("admin", result.getName());
        verify(userTypeRepository, times(1)).findByName(newUserType);
        verify(userTypeRepository, times(1)).save(newUserType);
    }

    @Test
    void mustThrowExceptionWhenUserTypeAlreadyExists() {
        // Arrange
        UserType existingUserType = new UserType("admin");

        when(userTypeRepository.findByName(existingUserType)).thenReturn(existingUserType);

        // Act & Assert
        ResourceAlreadyExistsException exception = assertThrows(
                ResourceAlreadyExistsException.class,
                () -> createUserTypeUseCase.execute(existingUserType)
        );

        assertEquals("UserType already exists", exception.getMessage());

        // Verifica que o save nunca foi chamado
        verify(userTypeRepository, times(1)).findByName(existingUserType);
        verify(userTypeRepository, never()).save(any());
    }
}
