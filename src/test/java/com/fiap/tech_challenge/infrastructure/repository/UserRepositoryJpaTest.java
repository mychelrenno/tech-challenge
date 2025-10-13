package com.fiap.tech_challenge.infrastructure.repository;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.domain.shared.Address;
import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.infrastructure.entity.AddressJpa;
import com.fiap.tech_challenge.infrastructure.entity.UserJpa;
import com.fiap.tech_challenge.infrastructure.entity.UserTypeJpa;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaUser;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaUserType;
import com.fiap.tech_challenge.interfaces.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserRepositoryJpaTest {

    private SpringDataJpaUser springDataJpaUser;
    private SpringDataJpaUserType springDataJpaUserType;
    private UserRepositoryJpa userRepositoryJpa;

    @BeforeEach
    void setUp() {
        springDataJpaUser = mock(SpringDataJpaUser.class);
        springDataJpaUserType = mock(SpringDataJpaUserType.class);
        userRepositoryJpa = new UserRepositoryJpa(springDataJpaUser, springDataJpaUserType);
    }

    @Test
    void mustSaveUserSuccessfullyWhenUserTypeExists() {
        UserType userType = new UserType("owner");
        Address address = new Address("01234-567", "Rua A", "Apto 101", "São Paulo", "Brasil");
        User user = new User(1L,
                "João Silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                userType,
                address,
                new Date(),
                true);

        UserJpa userJpa = UserMapper.convertEntityToJpa(user);
        UserTypeJpa userTypeJpa = new UserTypeJpa();
        userTypeJpa.setName("owner");

        when(springDataJpaUserType.findByName("owner")).thenReturn(userTypeJpa);
        when(springDataJpaUser.save(any(UserJpa.class))).thenReturn(userJpa);

        User result = userRepositoryJpa.save(user);

        assertNotNull(result);
        assertEquals("João Silva", result.getName());
        verify(springDataJpaUserType, times(1)).findByName("owner");
        verify(springDataJpaUser, times(1)).save(any(UserJpa.class));
    }

    @Test
    void mustReturnUserWhenFindByEmailExists() {
        String email = "joaosilva@test.com";
        UserTypeJpa userTypeJpa = new UserTypeJpa("ADMIN");
        AddressJpa addressJpa = new AddressJpa("01234-567", "Rua A", "Apto 101", "São Paulo", "Brasil");
        UserJpa userJpa = new UserJpa("Nome Owner", email, "owneruser", "senha123", userTypeJpa, addressJpa, new java.util.Date(), true);

        when(springDataJpaUser.findByEmail(email)).thenReturn(Optional.of(userJpa));

        User result = userRepositoryJpa.findByEmail(email);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(springDataJpaUser, times(1)).findByEmail(email);
    }

    @Test
    void mustReturnNullWhenFindByEmailNotExist() {
        String email = "notfound@test.com";
        when(springDataJpaUser.findByEmail(email)).thenReturn(Optional.empty());

        User result = userRepositoryJpa.findByEmail(email);

        assertNull(result);
        verify(springDataJpaUser, times(1)).findByEmail(email);
    }

    @Test
    void mustUpdateUserSuccessfullyWhenUserExists() {
        Long userId = 1L;
        UserType userType = new UserType("OWNER");
        Address address = new Address("01234-567", "Rua A", "Apto 101", "São Paulo", "Brasil");
        User user = new User(userId,
                "João Silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                userType,
                address,
                new Date(),
                true);

        UserJpa existingJpa = UserMapper.convertEntityToJpa(user);

        when(springDataJpaUser.findById(userId)).thenReturn(Optional.of(existingJpa));
        when(springDataJpaUserType.findByName("ADMIN")).thenReturn(new UserTypeJpa());
        when(springDataJpaUser.save(existingJpa)).thenReturn(existingJpa);

        User result = userRepositoryJpa.update(userId, user);

        assertNotNull(result);
        assertEquals("João Silva", result.getName());
        verify(springDataJpaUser, times(1)).findById(userId);
        verify(springDataJpaUser, times(1)).save(existingJpa);
    }

    @Test
    void mustReturnNullWhenUpdateUserNotExists() {
        Long userId = 2L;
        User user = new User(userId,
                "João Silva",
                "joaosilva@teste.com",
                "joaosilva",
                "123456",
                new UserType("owner"),
                null,
                new Date(),
                true);

        when(springDataJpaUser.findById(userId)).thenReturn(Optional.empty());

        User result = userRepositoryJpa.update(userId, user);

        assertNull(result);
        verify(springDataJpaUser, times(1)).findById(userId);
        verify(springDataJpaUser, never()).save(any());
    }

    @Test
    void mustDeleteUserSuccessfullyWhenUserExists() {
        Long userId = 1L;
        UserJpa userJpa = new UserJpa();
        userJpa.setActive(true);

        when(springDataJpaUser.findById(userId)).thenReturn(Optional.of(userJpa));
        when(springDataJpaUser.save(userJpa)).thenReturn(userJpa);

        Boolean result = userRepositoryJpa.delete(userId);

        assertTrue(result);
        assertFalse(userJpa.getActive()); // soft delete
        verify(springDataJpaUser, times(1)).save(userJpa);
    }

    @Test
    void mustReturnFalseWhenDeleteUserNotExists() {
        Long userId = 2L;
        when(springDataJpaUser.findById(userId)).thenReturn(Optional.empty());

        Boolean result = userRepositoryJpa.delete(userId);

        assertFalse(result);
        verify(springDataJpaUser, never()).save(any());
    }

    @Test
    void mustReturnListOfActiveUsersWhenUsersExist() {
        // given
        UserTypeJpa userTypeJpa = new UserTypeJpa("ADMIN");
        AddressJpa addressJpa = new AddressJpa("01234-567", "Rua A", "Apto 101", "São Paulo", "Brasil");
        UserJpa userJpa = new UserJpa("Nome Owner", "owner@email.com", "owneruser", "senha123", userTypeJpa, addressJpa, new java.util.Date(), true);

        // when
        when(springDataJpaUser.findByActiveTrue()).thenReturn(List.of(userJpa));

        List<User> result = userRepositoryJpa.findByActiveTrue();

        // then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(springDataJpaUser, times(1)).findByActiveTrue();
    }
}
