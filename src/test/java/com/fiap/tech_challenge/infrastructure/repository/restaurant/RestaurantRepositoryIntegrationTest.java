package com.fiap.tech_challenge.infrastructure.repository.restaurant;

import com.fiap.tech_challenge.infrastructure.entity.AddressJpa;
import com.fiap.tech_challenge.infrastructure.entity.OwnerJpa;
import com.fiap.tech_challenge.infrastructure.entity.RestaurantJpa;
import com.fiap.tech_challenge.infrastructure.entity.UserJpa;
import com.fiap.tech_challenge.infrastructure.entity.UserTypeJpa;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaOwner;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaRestaurant;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaUser;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaUserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
class RestaurantRepositoryIntegrationTest {

    @Autowired
    private SpringDataJpaRestaurant restaurantRepository;

    @Autowired
    private SpringDataJpaOwner ownerRepository;

    @Autowired
    private SpringDataJpaUser userRepository;

    @Autowired
    private SpringDataJpaUserType userTypeRepository;

    @Autowired
    private EntityManager entityManager;

    private UserTypeJpa createAndPersistUserType() {
        UserTypeJpa userType = new UserTypeJpa("ADMIN");
        return userTypeRepository.save(userType);
    }

    private UserJpa createAndPersistUser() {
        UserTypeJpa userType = createAndPersistUserType();
        AddressJpa address = new AddressJpa("01234-567", "Rua A", "Apto 101", "São Paulo", "Brasil");
        UserJpa user = new UserJpa("Nome Owner", "owner@email.com", "owneruser", "senha123", userType, address, new java.util.Date(), true);
        return userRepository.save(user);
    }

    private OwnerJpa createAndPersistOwner() {
        UserJpa user = createAndPersistUser();
        OwnerJpa owner = new OwnerJpa();
        owner.setDocument("12345678900");
        owner.setUser(user);
        owner = ownerRepository.save(owner);
        return owner;
    }

    private RestaurantJpa createRestaurant(String name, OwnerJpa owner) {
        AddressJpa address = new AddressJpa("01234-567", "Rua A", "Apto 101", "São Paulo", "Brasil");
        return new RestaurantJpa(null, name, address, "Italiana", "08:00-18:00", owner);
    }

    @Test
    @DisplayName("Deve salvar um restaurante")
    @Transactional
    void testSaveRestaurant() {
        OwnerJpa owner = createAndPersistOwner();
        RestaurantJpa restaurant = createRestaurant("Restaurante Teste", owner);
        RestaurantJpa saved = restaurantRepository.save(restaurant);
        assertNotNull(saved.getId());
        assertEquals("Restaurante Teste", saved.getName());
    }

    @Test
    @DisplayName("Deve buscar restaurante por ID")
    void testFindById() {
        OwnerJpa owner = createAndPersistOwner();
        RestaurantJpa restaurant = createRestaurant("Restaurante Busca", owner);
        RestaurantJpa saved = restaurantRepository.save(restaurant);
        Optional<RestaurantJpa> found = restaurantRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("Restaurante Busca", found.get().getName());
    }

    @Test
    @DisplayName("Deve buscar todos os restaurantes")
    void testFindAll() {
        OwnerJpa owner = createAndPersistOwner();
        restaurantRepository.save(createRestaurant("Restaurante 1", owner));
        restaurantRepository.save(createRestaurant("Restaurante 2", owner));
        var all = restaurantRepository.findAll();
        assertTrue(all.size() >= 2);
    }

    @Test
    @DisplayName("Deve atualizar restaurante")
    void testUpdateRestaurant() {
        OwnerJpa owner = createAndPersistOwner();
        RestaurantJpa restaurant = createRestaurant("Restaurante Original", owner);
        RestaurantJpa saved = restaurantRepository.save(restaurant);
        saved.setName("Restaurante Atualizado");
        RestaurantJpa updated = restaurantRepository.save(saved);
        assertEquals("Restaurante Atualizado", updated.getName());
    }

    @Test
    @DisplayName("Deve deletar restaurante")
    void testDeleteRestaurant() {
        OwnerJpa owner = createAndPersistOwner();
        RestaurantJpa restaurant = createRestaurant("Restaurante Deletar", owner);
        RestaurantJpa saved = restaurantRepository.save(restaurant);
        restaurantRepository.deleteById(saved.getId());
        Optional<RestaurantJpa> found = restaurantRepository.findById(saved.getId());
        assertFalse(found.isPresent());
    }
}
