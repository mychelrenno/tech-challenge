package com.fiap.tech_challenge.interfaces.controller;

import com.fiap.tech_challenge.core.domain.User;
import com.fiap.tech_challenge.core.usecase.address.CreateAddressUseCase;
import com.fiap.tech_challenge.core.usecase.user.ChangeUserPasswordUseCase;
import com.fiap.tech_challenge.core.usecase.user.CreateUserUseCase;
import com.fiap.tech_challenge.interfaces.dto.PasswordRequestDto;
import com.fiap.tech_challenge.interfaces.dto.UserInputDto;
import com.fiap.tech_challenge.interfaces.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final ChangeUserPasswordUseCase changeUserPasswordUseCase;

    public UserController(CreateUserUseCase createUserUseCase,
                          ChangeUserPasswordUseCase changeUserPasswordUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.changeUserPasswordUseCase = changeUserPasswordUseCase;
    }

    @PostMapping
    public User create(@RequestBody UserInputDto userInputDto) {
        return createUserUseCase.execute(UserMapper.convertDtoToEntity(userInputDto));
    }

    @PatchMapping
    public User updatePassword(@RequestBody PasswordRequestDto passwordRequestDto) {
        return changeUserPasswordUseCase.execute(passwordRequestDto.id(),
                passwordRequestDto.oldPassword(),
                passwordRequestDto.newPassword());
    }
}
