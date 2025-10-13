package com.fiap.tech_challenge.core.usecase;

import com.fiap.tech_challenge.core.repository.MenuItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteMenuItemUseCaseTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    private DeleteMenuItemUseCase deleteMenuItemUseCase;

    @BeforeEach
    void setUp() {
        deleteMenuItemUseCase = new DeleteMenuItemUseCase(menuItemRepository);
    }

    @Test
    void shouldDeleteMenuItemSuccessfully() {
        Long menuItemId = 1L;

        doNothing().when(menuItemRepository).deleteById(menuItemId);

        deleteMenuItemUseCase.execute(menuItemId);

        verify(menuItemRepository, times(1)).deleteById(menuItemId);
    }

    @Test
    void shouldCallRepositoryDeleteByIdWithCorrectId() {
        Long menuItemId = 42L;

        doNothing().when(menuItemRepository).deleteById(menuItemId);

        deleteMenuItemUseCase.execute(menuItemId);

        verify(menuItemRepository, times(1)).deleteById(menuItemId);
        verify(menuItemRepository, never()).deleteById(argThat(id -> !id.equals(menuItemId)));
    }

    @Test
    void shouldDeleteMultipleMenuItems() {
        Long firstId = 1L;
        Long secondId = 2L;
        Long thirdId = 3L;

        doNothing().when(menuItemRepository).deleteById(anyLong());

        deleteMenuItemUseCase.execute(firstId);
        deleteMenuItemUseCase.execute(secondId);
        deleteMenuItemUseCase.execute(thirdId);

        verify(menuItemRepository, times(1)).deleteById(firstId);
        verify(menuItemRepository, times(1)).deleteById(secondId);
        verify(menuItemRepository, times(1)).deleteById(thirdId);
        verify(menuItemRepository, times(3)).deleteById(anyLong());
    }
}

