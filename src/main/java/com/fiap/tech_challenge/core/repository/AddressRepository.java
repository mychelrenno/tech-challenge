package com.fiap.tech_challenge.core.repository;

import com.fiap.tech_challenge.core.domain.shared.Address;

public interface AddressRepository {
    Address save(Address address);
}
