package com.fiap.tech_challenge.core.repository;

import com.fiap.tech_challenge.core.domain.restaurant.MenuItem;
import java.util.List;
import java.util.Optional;

public interface MenuItemRepository {

    MenuItem save(MenuItem menuItem);
    
    List<MenuItem> findAll();
    
    Optional<MenuItem> findById(Long id);
    
    MenuItem update(MenuItem menuItem);
    
    void deleteById(Long id);
}



