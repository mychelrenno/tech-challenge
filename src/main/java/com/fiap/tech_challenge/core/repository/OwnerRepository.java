package com.fiap.tech_challenge.core.repository;

import com.fiap.tech_challenge.core.domain.Owner;

import java.util.List;

public interface OwnerRepository {
    boolean save(Owner owner);
    Owner findById(Long id);
    boolean update(Long id, Owner customer);
    Boolean delete(Long id);
    List<Owner> findByActiveTrue();
}
