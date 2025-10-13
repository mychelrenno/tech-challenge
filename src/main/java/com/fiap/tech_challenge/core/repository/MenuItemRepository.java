package com.fiap.tech_challenge.core.repository;

import com.fiap.tech_challenge.core.domain.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository {

    public MenuItem save(MenuItem menuItem);
    
    public List<MenuItem> findAll();
    
    public Optional<MenuItem> findById(Long id);
    
    public MenuItem update(MenuItem menuItem);
    
    public void deleteById(Long id);
}



