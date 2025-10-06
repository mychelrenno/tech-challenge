package com.fiap.tech_challenge.core.usecase.usertype;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.exception.ResourceAlreadyExistsException;
import com.fiap.tech_challenge.core.repository.UserTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UpdateUserTypeUseCaseTest {

    private UserTypeRepository userTypeRepository;
    private UpdateUserTypeUseCase updateUserTypeUseCase;

    @BeforeEach
    void setUp() {
        userTypeRepository = Mockito.mock(UserTypeRepository.class);
        updateUserTypeUseCase = new UpdateUserTypeUseCase(userTypeRepository);
    }

    @Test
    void mustUpdateUserTypeWhenThereIsntAnotherWithSameName() {
        UserType userType = new UserType("owner");
        when(userTypeRepository.findByName(userType)).thenReturn(null);
        UserType updatedUserType = new UserType(1L, "owner");
        when(userTypeRepository.update(userType)).thenReturn(updatedUserType);
        UserType result = updateUserTypeUseCase.execute(userType);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("owner", result.getName());
        verify(userTypeRepository, times(1)).findByName(userType);
        verify(userTypeRepository, times(1)).update(userType);
    }

    @Test
    void mustThrowExceptionWhenUserTypeAlreadyExists() {
        UserType userType = new UserType("owner");
        when(userTypeRepository.findByName(userType)).thenReturn(userType);

        Exception exception = assertThrows(ResourceAlreadyExistsException.class, () -> {
            updateUserTypeUseCase.execute(userType);
        });
        assertEquals("UserType already exists", exception.getMessage());
        verify(userTypeRepository, times(1)).findByName(userType);
        verify(userTypeRepository, never()).update(any());
    }
}
