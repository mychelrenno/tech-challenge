package com.fiap.tech_challenge.core.usecase.usertype;

import com.fiap.tech_challenge.core.repository.UserTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DeleteUserTypeUseCaseTest {

    private UserTypeRepository userTypeRepository;
    private DeleteUserTypeUseCase deleteUserTypeUseCase;

    @BeforeEach
    void setUp() {
        userTypeRepository = Mockito.mock(UserTypeRepository.class);
        deleteUserTypeUseCase = new DeleteUserTypeUseCase(userTypeRepository);
    }

    @Test
    void mustCallDeleteWithGivenId() {
        Long id = 1L;

        deleteUserTypeUseCase.execute(id);

        verify(userTypeRepository, times(1)).delete(id);
    }
}
