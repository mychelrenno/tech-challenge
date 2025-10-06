package com.fiap.tech_challenge.core.usecase.owner;

import com.fiap.tech_challenge.core.repository.OwnerRepository;

public class DeleteOwnerUseCase {
    private final OwnerRepository ownerRepository;

    public DeleteOwnerUseCase(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    // Method: Logic delete
    public boolean delete(Long ownerId){
        return ownerRepository.delete(ownerId);
    }
}
