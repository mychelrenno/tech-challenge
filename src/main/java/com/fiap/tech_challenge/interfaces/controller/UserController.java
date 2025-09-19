package com.fiap.tech_challenge.interfaces.controller;

import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.core.usecase.user.*;
import com.fiap.tech_challenge.interfaces.dto.PasswordRequestDto;
import com.fiap.tech_challenge.interfaces.dto.UserInputDto;
import com.fiap.tech_challenge.interfaces.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final ChangeUserPasswordUseCase changeUserPasswordUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final ListAllActiveUsersUseCase listAllActiveUsersUseCase;

    public UserController(CreateUserUseCase createUserUseCase,
                          ChangeUserPasswordUseCase changeUserPasswordUseCase, UpdateUserUseCase updateUserUseCase, DeleteUserUseCase deleteUserUseCase, ListAllActiveUsersUseCase listAllActiveUsersUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.changeUserPasswordUseCase = changeUserPasswordUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.listAllActiveUsersUseCase = listAllActiveUsersUseCase;
    }

    @PostMapping
    public User create(@RequestBody UserInputDto userInputDto) {
        return createUserUseCase.execute(UserMapper.convertDtoToEntity(userInputDto));
    }

    @PutMapping
    public User update(@RequestParam Long userId, @RequestBody UserInputDto userInputDto){
        return updateUserUseCase.execute(userId, UserMapper.convertDtoToEntity(userInputDto));
    }

    @PatchMapping
    public User updatePassword(@RequestBody PasswordRequestDto passwordRequestDto) {
        return changeUserPasswordUseCase.execute(passwordRequestDto.id(),
                passwordRequestDto.oldPassword(),
                passwordRequestDto.newPassword());
    }

    @DeleteMapping
    public Boolean delete(@RequestParam Long userId){
        return deleteUserUseCase.delete(userId);
    }

    @GetMapping
    public List<User> listAllActive(){
        return listAllActiveUsersUseCase.listAllUsersActive();
    }
}
