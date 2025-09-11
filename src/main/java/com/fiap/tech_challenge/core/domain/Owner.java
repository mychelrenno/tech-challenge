package com.fiap.tech_challenge.core.domain;

import com.fiap.tech_challenge.core.domain.restaurant.Restaurant;

import java.util.List;

public class Owner {
    private Long id;
    private Long document;
    private List<Restaurant> restaurants;
    private User user;

    public Owner(Long id, Long document, List<Restaurant> restaurants, User user) {
        this.id = id;
        this.document = document;
        this.restaurants = restaurants;
        this.user = user;
    }

    public Owner(Long document, List<Restaurant> restaurants, User user) {
        this.document = document;
        this.restaurants = restaurants;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Long getDocument() {
        return document;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public User getUser() {
        return user;
    }
}
