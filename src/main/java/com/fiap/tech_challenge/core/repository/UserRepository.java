package com.fiap.tech_challenge.core.repository;

import com.fiap.tech_challenge.core.domain.User;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
