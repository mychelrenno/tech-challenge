package com.fiap.tech_challenge.core.usecase.usertype;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.repository.UserTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ListAllUserTypeUseCaseTest {

    private UserTypeRepository userTypeRepository;
    private ListAllUserTypeUseCase listAllUserTypeUseCase;

    @BeforeEach
    void setUp() {
        userTypeRepository = Mockito.mock(UserTypeRepository.class);
        listAllUserTypeUseCase = new ListAllUserTypeUseCase(userTypeRepository);
    }

    @Test
    void mustReturnUserTyeList() {
        UserType ut1 = new UserType("owner");
        UserType ut2 = new UserType("custumer");
        List<UserType> mockList = Arrays.asList(ut1, ut2);
        when(userTypeRepository.listAll()).thenReturn(mockList);

        List<UserType> result = listAllUserTypeUseCase.execute();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("owner", result.get(0).getName());
        assertEquals("custumer", result.get(1).getName());
        verify(userTypeRepository, times(1)).listAll();
    }
}
