package com.fiap.tech_challenge.interfaces.controller;

import com.fiap.tech_challenge.core.usecase.usertype.CreateUserTypeUseCase;
import com.fiap.tech_challenge.core.usecase.usertype.DeleteUserTypeUseCase;
import com.fiap.tech_challenge.core.usecase.usertype.ListAllUserTypeUseCase;
import com.fiap.tech_challenge.core.usecase.usertype.UpdateUserTypeUseCase;
import com.fiap.tech_challenge.interfaces.dto.UserTypeDto;
import com.fiap.tech_challenge.interfaces.mapper.UserTypeMapper;
import com.fiap.tech_challenge.interfaces.dto.validation.group.Create;
import com.fiap.tech_challenge.interfaces.dto.validation.group.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type-user")
public class UserTypeController {

    private final CreateUserTypeUseCase createUserTypeUseCase;
    private final ListAllUserTypeUseCase listAllUserTypeUseCase;
    private final UpdateUserTypeUseCase updateUserTypeUseCase;
    private final DeleteUserTypeUseCase deleteUserTypeUseCase;

    public UserTypeController(CreateUserTypeUseCase createUserTypeUseCase,
                              ListAllUserTypeUseCase listAllUserTypeUseCase,
                              UpdateUserTypeUseCase updateUserTypeUseCase,
                              DeleteUserTypeUseCase deleteUserTypeUseCase) {
        this.createUserTypeUseCase = createUserTypeUseCase;
        this.listAllUserTypeUseCase = listAllUserTypeUseCase;
        this.updateUserTypeUseCase = updateUserTypeUseCase;
        this.deleteUserTypeUseCase = deleteUserTypeUseCase;
    }

    @PostMapping
    public UserTypeDto create(@RequestBody @Validated(Create.class) UserTypeDto userTypeDto) throws Exception {
        var userType = UserTypeMapper.convertDtoToDomain(userTypeDto);
        var _userType = createUserTypeUseCase.execute(userType);
        var _userTypeDto = UserTypeMapper.convertDomainToDto(_userType);
        return _userTypeDto;
    }

    @GetMapping
    public List<UserTypeDto> listAll() {
        var userTypeList = listAllUserTypeUseCase.execute();
        var userTypeDtoList = UserTypeMapper.convertDomainToDto(userTypeList);
        return userTypeDtoList;
    }

    @PutMapping
    public UserTypeDto update(@RequestBody @Validated(Update.class) UserTypeDto userTypeDto) {
        var userType = UserTypeMapper.convertDtoToDomain(userTypeDto);
        var _userType = updateUserTypeUseCase.execute(userType);
        var _userTypeDto = UserTypeMapper.convertDomainToDto(_userType);
        return _userTypeDto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteUserTypeUseCase.execute(id);
    }
}
