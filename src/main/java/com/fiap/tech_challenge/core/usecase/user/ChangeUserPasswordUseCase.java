package com.fiap.tech_challenge.core.usecase.user;

import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.core.exception.ResourceNotFoundException;
import com.fiap.tech_challenge.core.repository.UserRepository;

public class ChangeUserPasswordUseCase {
    private final UserRepository userRepository;

    public ChangeUserPasswordUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean execute(Long id, String oldPassword, String newPassword) {
        validateUser(id, oldPassword, newPassword);
        return userRepository.changePassword(id, oldPassword, newPassword);
    }

    public void validateUser(Long id, String oldPassword, String newPassword){
        User user = userRepository.findById(id);
        if(user.getActive()){
            if(user.getPassword().matches(oldPassword)){
                user.changePassword(newPassword);
            }else{
                throw new IllegalArgumentException("Old password not match user's password.");
            }
        } else {
            throw new ResourceNotFoundException("User is inactive.");
        }
    }
}
