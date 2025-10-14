package com.fiap.tech_challenge.infrastructure.repository;

import com.fiap.tech_challenge.core.domain.Owner;
import com.fiap.tech_challenge.core.exception.InvalidAttributeException;
import com.fiap.tech_challenge.core.exception.ResourceNotFoundException;
import com.fiap.tech_challenge.core.repository.OwnerRepository;
import com.fiap.tech_challenge.infrastructure.entity.*;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaOwner;
import com.fiap.tech_challenge.interfaces.mapper.OwnerMapper;
import com.fiap.tech_challenge.interfaces.mapper.RestaurantMapper;
import com.fiap.tech_challenge.interfaces.mapper.UserMapper;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OwnerRepositoryJpa implements OwnerRepository {

    private final SpringDataJpaOwner springDataJpaOwner;

    public OwnerRepositoryJpa(SpringDataJpaOwner springDataJpaOwner) {
        this.springDataJpaOwner = springDataJpaOwner;
    }

    @Override
    public boolean save(Owner owner) {
        if(owner.getDocument()==null) throw new InvalidAttributeException("Document cannot be null.");
        if(owner.getUser()==null) throw new InvalidAttributeException("User's info cannot be null.");
        if(owner.getRestaurants()==null) throw new InvalidAttributeException("Restaurant's info cannot be null.");

        OwnerJpa ownerJpa = new OwnerJpa();
        if(owner.getId()!=null) ownerJpa.setId(owner.getId());
        ownerJpa.setDocument(owner.getDocument());
        // Restaurants
        List<RestaurantJpa> restaurantJpaList = new ArrayList<>();
        owner.getRestaurants().forEach(restaurant -> restaurantJpaList.add(RestaurantMapper.convertDomainToJpaWithoutOwner(restaurant)));
        restaurantJpaList.forEach(restaurantJpa -> restaurantJpa.setOwner(ownerJpa));
        ownerJpa.setRestaurants(restaurantJpaList);
        // User
        ownerJpa.setUser(UserMapper.convertEntityToJpa(owner.getUser()));
        ownerJpa.setActive(true);
        OwnerJpa savedOwner = springDataJpaOwner.save(ownerJpa);
        return savedOwner.isActive();
    }

    @Override
    public Owner findById(Long id) {
        if(id==null) throw new InvalidAttributeException("Id cannot be null.");
        Optional<OwnerJpa> foundCustomer = springDataJpaOwner.findById(id);
        return foundCustomer.map(OwnerMapper::convertJpaToEntity).orElse(null);
    }

    @Override
    public boolean update(Long id, Owner owner) {
        if(owner.getDocument()==null) throw new InvalidAttributeException("Document cannot be null.");
        if(owner.getUser()==null) throw new InvalidAttributeException("User's info cannot be null.");
        if(owner.getRestaurants()==null) throw new InvalidAttributeException("Restaurant's info cannot be null.");

        Optional<OwnerJpa> foundOwner = springDataJpaOwner.findById(id);
        if(foundOwner.isPresent()){
            OwnerJpa foundOwnerJpa = foundOwner.get();
            foundOwnerJpa.setDocument(owner.getDocument());
            // Restaurants
            List<RestaurantJpa> restaurantJpaList = new ArrayList<>();
            owner.getRestaurants().forEach(restaurant -> restaurantJpaList.add(RestaurantMapper.convertDomainToJpaWithoutOwner(restaurant)));
            restaurantJpaList.forEach(restaurantJpa -> restaurantJpa.setOwner(foundOwnerJpa));
            foundOwnerJpa.setRestaurants(restaurantJpaList);
            // User
            foundOwnerJpa.setUser(UserMapper.convertEntityToJpa(owner.getUser()));
            foundOwnerJpa.setActive(true);

            OwnerJpa savedOwner = springDataJpaOwner.save(foundOwnerJpa);
            return savedOwner.isActive();
        } else {
            throw new ResourceNotFoundException("Owner not found.");
        }
    }

    @Override
    public Boolean delete(Long id) {
        if(id==null) throw new InvalidAttributeException("Id cannot be null.");
        Optional<OwnerJpa> ownerJpa = springDataJpaOwner.findById(id);
        if(ownerJpa.isPresent()){
            ownerJpa.get().setActive(false);
            springDataJpaOwner.save(ownerJpa.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Owner> findByActiveTrue() {
        List<OwnerJpa> ownersJpaList = springDataJpaOwner.findAll();
        List<Owner> ownersList = new ArrayList<>();
        ownersJpaList.forEach(ownerJpa -> ownersList.add(OwnerMapper.convertJpaToEntity(ownerJpa)));
        return ownersList;
    }
}
