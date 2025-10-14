package com.fiap.tech_challenge.core.usecase.owner;

import com.fiap.tech_challenge.core.exception.InvalidAttributeException;
import com.fiap.tech_challenge.core.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeleteOwnerUseCaseTest {
    private OwnerRepository ownerRepository;
    private DeleteOwnerUseCase deleteOwnerUseCase;

    @BeforeEach
    void setUp() {
        ownerRepository = mock(OwnerRepository.class);
        deleteOwnerUseCase = new DeleteOwnerUseCase(ownerRepository);
    }

    @Test
    void shouldDeleteOwnerSuccessfully() {
        // given
        Long ownerId = 1L;
        when(ownerRepository.delete(ownerId)).thenReturn(true);

        // when
        boolean result = deleteOwnerUseCase.delete(ownerId);

        // then
        assertTrue(result);
        verify(ownerRepository, times(1)).delete(ownerId);
    }

    @Test
    void shouldReturnFalseWhenRepositoryFailsToDelete() {
        // given
        Long ownerId = 99L;
        when(ownerRepository.delete(ownerId)).thenReturn(false);

        // when
        boolean result = deleteOwnerUseCase.delete(ownerId);

        // then
        assertFalse(result);
        verify(ownerRepository, times(1)).delete(ownerId);
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        assertThrows(InvalidAttributeException.class, () -> deleteOwnerUseCase.delete(null));
        verifyNoInteractions(ownerRepository);
    }
}
