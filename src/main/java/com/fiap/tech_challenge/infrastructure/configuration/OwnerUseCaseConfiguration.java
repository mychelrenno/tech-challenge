package com.fiap.tech_challenge.infrastructure.configuration;

import com.fiap.tech_challenge.core.repository.OwnerRepository;
import com.fiap.tech_challenge.core.usecase.owner.CreateOwnerUseCase;
import com.fiap.tech_challenge.core.usecase.owner.DeleteOwnerUseCase;
import com.fiap.tech_challenge.core.usecase.owner.ListAllOwnersUseCase;
import com.fiap.tech_challenge.core.usecase.owner.UpdateOwnerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OwnerUseCaseConfiguration {
    private final OwnerRepository ownerRepository;

    public OwnerUseCaseConfiguration(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Bean
    public CreateOwnerUseCase createOwnerUseCase(){
        return new CreateOwnerUseCase(ownerRepository);
    }

    @Bean
    public DeleteOwnerUseCase deleteOwnerUseCase(){
        return new DeleteOwnerUseCase(ownerRepository);
    }

    @Bean
    public ListAllOwnersUseCase listAllOwnersUseCase(){
        return new ListAllOwnersUseCase(ownerRepository);
    }

    @Bean
    public UpdateOwnerUseCase updateOwnerUseCase(){
        return new UpdateOwnerUseCase(ownerRepository);
    }
}
