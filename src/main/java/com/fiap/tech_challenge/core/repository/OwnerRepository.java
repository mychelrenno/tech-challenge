package com.fiap.tech_challenge.core.repository;

import com.fiap.tech_challenge.core.domain.Owner;

import java.util.List;

public interface OwnerRepository {
    Owner save(Owner owner);
    Owner findById(Long id);
    Owner update(Long id, Owner customer);
    Boolean delete(Long id);
    List<Owner> findByActiveTrue();
}
