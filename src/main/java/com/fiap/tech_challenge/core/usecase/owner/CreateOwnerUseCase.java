package com.fiap.tech_challenge.core.usecase.owner;

import com.fiap.tech_challenge.core.domain.Owner;
import com.fiap.tech_challenge.core.exception.InvalidAttributeException;
import com.fiap.tech_challenge.core.repository.OwnerRepository;

public class CreateOwnerUseCase {
    private final OwnerRepository ownerRepository;

    public CreateOwnerUseCase(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Boolean execute(Owner owner) {
        validateOwner(owner);
        return ownerRepository.save(owner);
    }

    public void validateOwner(Owner owner){
        if(owner==null){
            throw new InvalidAttributeException("Owner cannot be empty.");
        }
        if(owner.getDocument()==null){
            throw new InvalidAttributeException("Owner's document cannot be empty.");
        }
        if(owner.getRestaurants().isEmpty()){
            throw new InvalidAttributeException("Owner must have a least one restaurant active.");
        }
        if(owner.getUser()==null){
            throw new InvalidAttributeException("User's info cannot be empty.");
        }
    }
}
