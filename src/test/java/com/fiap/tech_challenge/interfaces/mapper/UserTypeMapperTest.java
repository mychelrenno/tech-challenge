package com.fiap.tech_challenge.interfaces.mapper;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.infrastructure.entity.UserTypeJpa;
import com.fiap.tech_challenge.interfaces.dto.UserTypeDto;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserTypeMapperTest {
    @Test
    void shouldConvertDtoToDomainSuccessfully() {
        UserTypeDto dto = new UserTypeDto(1L, "ADMIN");
        UserType domain = UserTypeMapper.convertDtoToDomain(dto);
        assertNotNull(domain);
        assertEquals(dto.id(), domain.getId());
        assertEquals(dto.name(), domain.getName());
    }

    @Test
    void shouldReturnNullWhenConvertingNullDtoToDomain() {
        assertNull(UserTypeMapper.convertDtoToDomain(null));
    }

    @Test
    void shouldConvertDomainToJpaSuccessfully() {
        UserType domain = new UserType(2L, "CUSTOMER");
        UserTypeJpa jpa = UserTypeMapper.convertDomainToJpa(domain);
        assertNotNull(jpa);
        assertEquals(domain.getId(), jpa.getId());
        assertEquals(domain.getName(), jpa.getName());
    }

    @Test
    void shouldReturnNullWhenConvertingNullDomainToJpa() {
        assertNull(UserTypeMapper.convertDomainToJpa(null));
    }

    @Test
    void shouldConvertJpaToDtoSuccessfully() {
        UserTypeJpa jpa = new UserTypeJpa(3L, "OWNER");
        UserTypeDto dto = UserTypeMapper.convertJpaToDto(jpa);
        assertNotNull(dto);
        assertEquals(jpa.getId(), dto.id());
        assertEquals(jpa.getName(), dto.name());
    }

    @Test
    void shouldReturnNullWhenConvertingNullJpaToDto() {
        assertNull(UserTypeMapper.convertJpaToDto(null));
    }

    @Test
    void shouldConvertDomainToDtoSuccessfully() {
        UserType domain = new UserType(4L, "EMPLOYEE");
        UserTypeDto dto = UserTypeMapper.convertDomainToDto(domain);
        assertNotNull(dto);
        assertEquals(domain.getId(), dto.id());
        assertEquals(domain.getName(), dto.name());
    }

    @Test
    void shouldReturnNullWhenConvertingNullDomainToDto() {
        assertNull(UserTypeMapper.convertDomainToDto(null));
        List<UserType> emptyDomainList = List.of();
        assertTrue(UserTypeMapper.convertListDomainToDto(emptyDomainList).isEmpty());
    }

    @Test
    void shouldConvertJpaToDomainSuccessfully() {
        UserTypeJpa jpa = new UserTypeJpa(5L, "SUPPORT");
        UserType domain = UserTypeMapper.convertJpaToDomain(jpa);
        assertNotNull(domain);
        assertEquals(jpa.getId(), domain.getId());
        assertEquals(jpa.getName(), domain.getName());
    }

    @Test
    void shouldReturnNullWhenConvertingNullJpaToDomain() {
        assertNull(UserTypeMapper.convertJpaToDomain(null));
        List<UserType> emptyDomainList = List.of();
        assertTrue(UserTypeMapper.convertListDomainToDto(emptyDomainList).isEmpty());
    }

    @Test
    void shouldConvertJpaListToDomainListSuccessfully() {
        List<UserTypeJpa> jpaList = List.of(
                new UserTypeJpa(1L, "ADMIN"),
                new UserTypeJpa(2L, "CUSTOMER")
        );
        List<UserType> domainList = UserTypeMapper.convertListJpaToDomain(jpaList);
        assertNotNull(domainList);
        assertEquals(2, domainList.size());
        assertEquals("ADMIN", domainList.get(0).getName());
        assertEquals("CUSTOMER", domainList.get(1).getName());
    }

    @Test
    void shouldConvertDomainListToDtoListSuccessfully() {
        List<UserType> domainList = List.of(
                new UserType(10L, "OWNER"),
                new UserType(20L, "SUPPORT")
        );
        List<UserTypeDto> dtoList = UserTypeMapper.convertListDomainToDto(domainList);
        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());
        assertEquals("OWNER", dtoList.get(0).name());
        assertEquals("SUPPORT", dtoList.get(1).name());
    }

    @Test
    void shouldHandleEmptyListsGracefully() {
        List<UserType> emptyDomainList = List.of();
        List<UserTypeJpa> emptyJpaList = List.of();
        assertTrue(UserTypeMapper.convertListDomainToDto(emptyDomainList).isEmpty());
        assertTrue(UserTypeMapper.convertListJpaToDomain(emptyJpaList).isEmpty());
    }
}
