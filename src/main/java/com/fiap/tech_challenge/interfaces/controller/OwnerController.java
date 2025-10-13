package com.fiap.tech_challenge.interfaces.controller;

import com.fiap.tech_challenge.core.domain.Owner;
import com.fiap.tech_challenge.core.usecase.owner.CreateOwnerUseCase;
import com.fiap.tech_challenge.core.usecase.owner.DeleteOwnerUseCase;
import com.fiap.tech_challenge.core.usecase.owner.ListAllOwnersUseCase;
import com.fiap.tech_challenge.core.usecase.owner.UpdateOwnerUseCase;
import com.fiap.tech_challenge.interfaces.dto.owner.OwnerInputDto;
import com.fiap.tech_challenge.interfaces.mapper.OwnerMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {
    private final CreateOwnerUseCase createOwnerUseCase;
    private final DeleteOwnerUseCase deleteOwnerUseCase;
    private final ListAllOwnersUseCase listAllOwnersUseCase;
    private final UpdateOwnerUseCase updateOwnerUseCase;

    public OwnerController(CreateOwnerUseCase createOwnerUseCase,
                           DeleteOwnerUseCase deleteOwnerUseCase,
                           ListAllOwnersUseCase listAllOwnersUseCase,
                           UpdateOwnerUseCase updateOwnerUseCase) {
        this.createOwnerUseCase = createOwnerUseCase;
        this.deleteOwnerUseCase = deleteOwnerUseCase;
        this.listAllOwnersUseCase = listAllOwnersUseCase;
        this.updateOwnerUseCase = updateOwnerUseCase;
    }

    @PostMapping
    public Owner createOwner(@RequestBody OwnerInputDto ownerInputDto){
        return createOwnerUseCase.execute(OwnerMapper.convertInputDtoToDomain(ownerInputDto));
    }

    @DeleteMapping
    public Boolean deleteOwner(@RequestParam Long ownerId){
        return deleteOwnerUseCase.delete(ownerId);
    }

    @GetMapping
    public List<Owner> listAllOwners(){
        return listAllOwnersUseCase.listAllOwnersActive();
    }

    @PutMapping
    public Owner updateOwner(@RequestParam Long ownerId, @RequestBody OwnerInputDto ownerInputDto){
        Owner owner = OwnerMapper.convertInputDtoToDomain(ownerInputDto);
        return updateOwnerUseCase.execute(ownerId, owner);
    }
}
