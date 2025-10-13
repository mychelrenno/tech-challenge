package com.fiap.tech_challenge.infrastructure.repository;

import com.fiap.tech_challenge.core.domain.Owner;
import com.fiap.tech_challenge.core.repository.OwnerRepository;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaOwner;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OwnerRepositoryJpa implements OwnerRepository {

    private final SpringDataJpaOwner springDataJpaOwner;

    public OwnerRepositoryJpa(SpringDataJpaOwner springDataJpaOwner) {
        this.springDataJpaOwner = springDataJpaOwner;
    }

    @Override
    public Owner save(Owner owner) {
        return null;
    }

    @Override
    public Owner findById(Long id) {
        return null;
    }

    @Override
    public Owner update(Long id, Owner customer) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public List<Owner> findByActiveTrue() {
        return List.of();
    }
}
