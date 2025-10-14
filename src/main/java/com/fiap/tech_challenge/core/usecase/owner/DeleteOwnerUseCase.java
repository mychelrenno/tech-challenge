package com.fiap.tech_challenge.core.usecase.owner;

import com.fiap.tech_challenge.core.exception.InvalidAttributeException;
import com.fiap.tech_challenge.core.repository.OwnerRepository;

public class DeleteOwnerUseCase {
    private final OwnerRepository ownerRepository;

    public DeleteOwnerUseCase(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    // Method: Logic delete
    public boolean delete(Long ownerId){
        if(ownerId==null) throw new InvalidAttributeException("Owner's id cannot be null.");
        return ownerRepository.delete(ownerId);
    }
}
