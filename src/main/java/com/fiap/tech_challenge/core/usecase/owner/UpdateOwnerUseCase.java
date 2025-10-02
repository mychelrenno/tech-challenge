package com.fiap.tech_challenge.core.usecase.owner;

import com.fiap.tech_challenge.core.domain.Owner;
import com.fiap.tech_challenge.core.exception.InvalidAttributeException;
import com.fiap.tech_challenge.core.exception.ResourceNotFoundException;
import com.fiap.tech_challenge.core.repository.OwnerRepository;

public class UpdateOwnerUseCase {
    private final OwnerRepository ownerRepository;

    public UpdateOwnerUseCase(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner execute(Long ownerId, Owner updatedOwnerData){
        // check owner id
        Owner foundOwner = ownerRepository.findById(ownerId);
        // check data
        validateUpdatedOwnerData(updatedOwnerData);
        // update data
        if(foundOwner==null){
            throw new ResourceNotFoundException("Owner not found.");
        } else {
            return ownerRepository.update(ownerId, updatedOwnerData);
        }
    }

    public void validateUpdatedOwnerData(Owner owner){
        if(owner==null){
            throw new InvalidAttributeException("Owner cannot be empty.");
        }
        if(owner.getDocument().isBlank() || owner.getDocument().isEmpty()){
            throw new InvalidAttributeException("Owner's document cannot be empty.");
        }
        if(owner.getRestaurants().isEmpty()){
            throw new InvalidAttributeException("Owner must have a least one restaurant.");
        }
        if(owner.getUser()==null){
            throw new InvalidAttributeException("User's info cannot be empty.");
        }
    }
}
