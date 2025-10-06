package com.fiap.tech_challenge.infrastructure.repository;
import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.infrastructure.entity.UserTypeJpa;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaUserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserTypeRepositoryJpaTest {

    private SpringDataJpaUserType springDataJpaUserType;
    private UserTypeRepositoryJpa userTypeRepositoryJpa;

    @BeforeEach
    void setUp() {
        springDataJpaUserType = Mockito.mock(SpringDataJpaUserType.class);
        userTypeRepositoryJpa = new UserTypeRepositoryJpa(springDataJpaUserType);
    }

    @Test
    void mustSaveUserType() {
        UserType domainUserType = new UserType("owner");
        UserTypeJpa jpaUserType = new UserTypeJpa();
        jpaUserType.setName("Admin");
        when(springDataJpaUserType.save(any(UserTypeJpa.class))).thenReturn(jpaUserType);

        UserType result = userTypeRepositoryJpa.save(domainUserType);

        assertNotNull(result);
        assertEquals("Admin", result.getName());
        verify(springDataJpaUserType, times(1)).save(any(UserTypeJpa.class));
    }

    @Test
    void mustReturnAllUserType() {
        UserTypeJpa ut1 = new UserTypeJpa(); ut1.setName("owner");
        UserTypeJpa ut2 = new UserTypeJpa(); ut2.setName("customer");

        when(springDataJpaUserType.findAll()).thenReturn(Arrays.asList(ut1, ut2));

        List<UserType> result = userTypeRepositoryJpa.listAll();

        assertEquals(2, result.size());
        assertEquals("owner", result.get(0).getName());
        assertEquals("customer", result.get(1).getName());
        verify(springDataJpaUserType, times(1)).findAll();
    }

    @Test
    void mustUpdateUserType() {
        UserType domainUserType = new UserType("owner");
        UserTypeJpa jpaUserType = new UserTypeJpa();
        jpaUserType.setName("owner");
        when(springDataJpaUserType.save(any(UserTypeJpa.class))).thenReturn(jpaUserType);

        UserType result = userTypeRepositoryJpa.update(domainUserType);

        assertNotNull(result);
        assertEquals("owner", result.getName());
        verify(springDataJpaUserType, times(1)).save(any(UserTypeJpa.class));
    }

    @Test
    void deveDeletarUserTypePorId() {
        Long id = 1L;

        userTypeRepositoryJpa.delete(id);

        verify(springDataJpaUserType, times(1)).deleteById(id);
    }

    @Test
    void mustFindUserTypeByName() {
        UserType domainUserType = new UserType("owner");
        UserTypeJpa jpaUserType = new UserTypeJpa();
        jpaUserType.setName("owner");
        when(springDataJpaUserType.findByName("owner")).thenReturn(jpaUserType);

        UserType result = userTypeRepositoryJpa.findByName(domainUserType);

        assertNotNull(result);
        assertEquals("owner", result.getName());
        verify(springDataJpaUserType, times(1)).findByName("owner");
    }
}