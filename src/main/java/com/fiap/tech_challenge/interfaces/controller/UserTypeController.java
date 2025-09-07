package com.fiap.tech_challenge.interfaces.controller;

import com.fiap.tech_challenge.core.usecase.CreateUserTypeUseCase;
import com.fiap.tech_challenge.interfaces.dto.UserTypeDto;
import com.fiap.tech_challenge.interfaces.mapper.UserTypeMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/type-user")
public class UserTypeController {

    private CreateUserTypeUseCase createUserTypeUseCase;

    public UserTypeController(CreateUserTypeUseCase createUserTypeUseCase) {
        this.createUserTypeUseCase = createUserTypeUseCase;
    }

    @PostMapping
    public void create(@RequestBody UserTypeDto userTypeDto) {
        var userType = createUserTypeUseCase.execute(UserTypeMapper.convertDtoToEntity(userTypeDto));

        System.out.println("mychelteste");
    }
}
