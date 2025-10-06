package com.fiap.tech_challenge.core.domain;

import com.fiap.tech_challenge.core.domain.restaurant.Restaurant;
import com.fiap.tech_challenge.core.domain.user.User;

import java.util.List;

public class Owner {
    private Long id;
    private String document;
    private List<Restaurant> restaurants;
    private User user;

    public Owner(Long id, String document, List<Restaurant> restaurants, User user) {
        this.id = id;
        this.document = document;
        this.restaurants = restaurants;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getDocument() {
        return document;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public User getUser() {
        return user;
    }
}
