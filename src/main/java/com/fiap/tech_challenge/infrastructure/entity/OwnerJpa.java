package com.fiap.tech_challenge.infrastructure.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "owners")
public class OwnerJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String document;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<RestaurantJpa> restaurants;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private UserJpa user;

    public OwnerJpa() {
    }

    public OwnerJpa(Long id,
                    String document,
                    List<RestaurantJpa> restaurants,
                    UserJpa user) {
        this.id = id;
        this.document = document;
        this.restaurants = restaurants;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public List<RestaurantJpa> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantJpa> restaurants) {
        this.restaurants = restaurants;
    }

    public UserJpa getUser() {
        return user;
    }

    public void setUser(UserJpa user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "OwnerJpa{" +
                "id=" + id +
                ", document='" + document + '\'' +
                ", restaurants=" + restaurants +
                ", user=" + user +
                '}';
    }
}
