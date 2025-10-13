package com.fiap.tech_challenge.interfaces.controller;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.usecase.usertype.*;
import com.fiap.tech_challenge.interfaces.dto.UserTypeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserTypeControllerTest {

    private CreateUserTypeUseCase createUserTypeUseCase;
    private ListAllUserTypeUseCase listAllUserTypeUseCase;
    private UpdateUserTypeUseCase updateUserTypeUseCase;
    private DeleteUserTypeUseCase deleteUserTypeUseCase;
    private FindUserTypeByIdUseCase findByIdUseCase;
    private UserTypeController controller;

    @BeforeEach
    void setUp() {
        createUserTypeUseCase = mock(CreateUserTypeUseCase.class);
        listAllUserTypeUseCase = mock(ListAllUserTypeUseCase.class);
        updateUserTypeUseCase = mock(UpdateUserTypeUseCase.class);
        deleteUserTypeUseCase = mock(DeleteUserTypeUseCase.class);
        findByIdUseCase = mock(FindUserTypeByIdUseCase.class);

        controller = new UserTypeController(
                createUserTypeUseCase,
                listAllUserTypeUseCase,
                updateUserTypeUseCase,
                deleteUserTypeUseCase,
                findByIdUseCase
        );
    }

    @Test
    void mustCreateUserType() throws Exception {
        UserTypeDto dto = new UserTypeDto(null, "owner");
        UserType domain = new UserType(1L,"owner");
        when(createUserTypeUseCase.execute(any())).thenReturn(domain);

        UserTypeDto result = controller.create(dto);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("owner", result.name());
        verify(createUserTypeUseCase, times(1)).execute(any());
    }

    @Test
    void mustListAllUserTypes() {
        UserType ut1 = new UserType(1L,"owner");
        UserType ut2 = new UserType(2L,"custumer");
        when(listAllUserTypeUseCase.execute()).thenReturn(Arrays.asList(ut1, ut2));

        List<UserTypeDto> result = controller.listAll();

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).id());
        assertEquals("owner", result.get(0).name());
        assertEquals(2L, result.get(1).id());
        assertEquals("custumer", result.get(1).name());
        verify(listAllUserTypeUseCase, times(1)).execute();
    }

    @Test
    void mustUpdateUserType() {
        UserTypeDto dto = new UserTypeDto(null,"owner");
        UserType domain = new UserType(1L, "owner");

        when(updateUserTypeUseCase.execute(any())).thenReturn(domain);

        UserTypeDto result = controller.update(dto);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("owner", result.name());
        verify(updateUserTypeUseCase, times(1)).execute(any());
    }

    @Test
    void mustDeleteUserType() {
        Long id = 1L;

        controller.delete(id);

        verify(deleteUserTypeUseCase, times(1)).execute(id);
    }
}

