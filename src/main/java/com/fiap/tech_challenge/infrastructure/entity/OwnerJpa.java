package com.fiap.tech_challenge.infrastructure.entity;

import com.fiap.tech_challenge.core.domain.restaurant.Restaurant;
import com.fiap.tech_challenge.core.domain.user.User;
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
    private List<Restaurant> restaurants;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private User user;

    public OwnerJpa() {
    }

    public OwnerJpa(Long id,
                    String document,
                    List<Restaurant> restaurants,
                    User user) {
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

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
