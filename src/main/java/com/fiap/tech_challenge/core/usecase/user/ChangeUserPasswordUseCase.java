package com.fiap.tech_challenge.core.usecase.user;

import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.core.repository.UserRepository;

public class ChangeUserPasswordUseCase {
    private final UserRepository userRepository;

    public ChangeUserPasswordUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(Long id, String oldPassword, String newPassword) {
        User foundUser = validateUser(id, oldPassword, newPassword);
        return userRepository.save(foundUser);
    }

    public User validateUser(Long id, String oldPassword, String newPassword){
        User user = userRepository.findById(id);
        if(user.getActive()){
            if(user.getPassword().matches(oldPassword)){
                user.changePassword(newPassword);
            }else{
                throw new IllegalArgumentException("Old password not match user's password.");
            }
        }
        return user;
    }
}
