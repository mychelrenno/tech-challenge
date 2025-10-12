package com.fiap.tech_challenge.core.usecase.usertype;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.exception.ResourceNotFoundException;
import com.fiap.tech_challenge.core.repository.UserTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FindUserTypeByIdUseCaseTest {
    private UserTypeRepository userTypeRepository;
    private FindUserTypeByIdUseCase findUserTypeByIdUseCase;

    @BeforeEach
    void setUp() {
        userTypeRepository = mock(UserTypeRepository.class);
        findUserTypeByIdUseCase = new FindUserTypeByIdUseCase(userTypeRepository);
    }

    @Test
    void mustReturnUserTypeWhenFound() {
        UserType userTypeInput = new UserType(1L);
        UserType userTypeResult = new UserType(1L, "owner");
        when(userTypeRepository.findById(userTypeInput)).thenReturn(userTypeResult);

        UserType result = findUserTypeByIdUseCase.execute(userTypeInput);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("owner", result.getName());
        verify(userTypeRepository, times(1)).findById(userTypeInput);
    }

    @Test
    void mustThrowExceptionWhenNotFound() {
        UserType userType = new UserType(99L);
        when(userTypeRepository.findById(userType)).thenReturn(null);

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> findUserTypeByIdUseCase.execute(userType)
        );
        assertEquals("UserType not found", exception.getMessage());
        verify(userTypeRepository, times(1)).findById(userType);
    }
}
