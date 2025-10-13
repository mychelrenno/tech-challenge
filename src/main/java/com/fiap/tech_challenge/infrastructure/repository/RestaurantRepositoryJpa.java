package com.fiap.tech_challenge.infrastructure.repository;

import com.fiap.tech_challenge.core.domain.restaurant.Restaurant;
import com.fiap.tech_challenge.core.repository.RestaurantRepository;
import com.fiap.tech_challenge.infrastructure.entity.RestaurantJpa;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaRestaurant;
import com.fiap.tech_challenge.interfaces.mapper.RestaurantMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RestaurantRepositoryJpa implements RestaurantRepository {
    private final SpringDataJpaRestaurant springDataJpaRestaurant;

    public RestaurantRepositoryJpa(SpringDataJpaRestaurant springDataJpaRestaurant) {
        this.springDataJpaRestaurant = springDataJpaRestaurant;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {

        RestaurantJpa entity = RestaurantMapper.convertEntityToJpa(restaurant);
        RestaurantJpa saved = springDataJpaRestaurant.save(entity);
        return RestaurantMapper.convertJpaToEntity(saved);
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return springDataJpaRestaurant.findById(id)
                .map(RestaurantMapper::convertJpaToEntity);
    }

    @Override
    public List<Restaurant> findAll() {
        return springDataJpaRestaurant.findAll().stream()
                .map(RestaurantMapper::convertJpaToEntity)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        springDataJpaRestaurant.deleteById(id);
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        RestaurantJpa entity = RestaurantMapper.convertEntityToJpa(restaurant);
        RestaurantJpa updated = springDataJpaRestaurant.save(entity);
        return RestaurantMapper.convertJpaToEntity(updated);
    }
}

