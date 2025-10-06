package com.fiap.tech_challenge.core.repository;

import com.fiap.tech_challenge.core.domain.user.User;
import java.util.List;

public interface UserRepository {
    User save(User user);
    User findByEmail(String email);
    User findByUsername(String username);
    User findById(Long id);
    User update(Long id, User user);
    Boolean delete(Long id);
    List<User> findByActiveTrue();
}
