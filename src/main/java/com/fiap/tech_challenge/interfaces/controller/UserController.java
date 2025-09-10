package com.fiap.tech_challenge.interfaces.controller;

import com.fiap.tech_challenge.core.domain.User;
import com.fiap.tech_challenge.core.usecase.user.CreateUserUseCase;
import com.fiap.tech_challenge.core.usecase.usertype.CreateUserTypeUseCase;
import com.fiap.tech_challenge.interfaces.dto.UserInputDto;
import com.fiap.tech_challenge.interfaces.dto.UserTypeDto;
import com.fiap.tech_challenge.interfaces.mapper.UserMapper;
import com.fiap.tech_challenge.interfaces.mapper.UserTypeMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping
    public User create(@RequestBody UserInputDto userInputDto) {
        return createUserUseCase.execute(UserMapper.convertDtoToEntity(userInputDto));
    }
}
