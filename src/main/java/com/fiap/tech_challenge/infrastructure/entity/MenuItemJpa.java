package com.fiap.tech_challenge.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "menu_item")
public class MenuItemJpa {

    public MenuItemJpa(String name, String description, Double price, Boolean restaurantOnly, String imagePath, Long restaurantId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurantOnly = restaurantOnly;
        this.imagePath = imagePath;
        this.restaurantId = restaurantId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Boolean restaurantOnly;
    private String imagePath;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    public MenuItemJpa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getRestaurantOnly() {
        return restaurantOnly;
    }

    public void setRestaurantOnly(Boolean restaurantOnly) {
        this.restaurantOnly = restaurantOnly;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
