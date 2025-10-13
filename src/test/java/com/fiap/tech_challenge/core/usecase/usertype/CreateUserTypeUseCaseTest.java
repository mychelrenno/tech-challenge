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
        UserType userType = new UserType("owner");
        when(userTypeRepository.findByName(userType)).thenReturn(null);
        UserType savedUserType = new UserType(1L, "owner");
        when(userTypeRepository.save(userType)).thenReturn(savedUserType);

        UserType result = createUserTypeUseCase.execute(userType);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("owner", result.getName());
        verify(userTypeRepository, times(1)).findByName(userType);
        verify(userTypeRepository, times(1)).save(userType);
    }

    @Test
    void mustThrowExceptionWhenUserTypeAlreadyExists() {
        UserType existingUserType = new UserType("custumer");
        when(userTypeRepository.findByName(existingUserType)).thenReturn(existingUserType);

        ResourceAlreadyExistsException exception = assertThrows(
                ResourceAlreadyExistsException.class,
                () -> createUserTypeUseCase.execute(existingUserType)
        );
        assertEquals("UserType already exists", exception.getMessage());
        verify(userTypeRepository, times(1)).findByName(existingUserType);
        verify(userTypeRepository, never()).save(any());
    }
}
