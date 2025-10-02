package com.fiap.tech_challenge.core.usecase.owner;

import com.fiap.tech_challenge.core.domain.Owner;
import com.fiap.tech_challenge.core.repository.OwnerRepository;

import java.util.List;

public class ListAllOwnersUseCase {
    private final OwnerRepository ownerRepository;

    public ListAllOwnersUseCase(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> listAllOwnersActive(){
        return ownerRepository.findByActiveTrue();
    }
}
