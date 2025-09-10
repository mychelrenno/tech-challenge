package com.fiap.tech_challenge.core.repository;

import com.fiap.tech_challenge.core.domain.User;

public interface UserRepository {
    User save(User user);
}
