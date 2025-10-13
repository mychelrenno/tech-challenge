package com.fiap.tech_challenge.core.domain;

public class MenuItem {

    public MenuItem(String name, String description, Double price, Boolean restaurantOnly, String imagePath, Long restaurantId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurantOnly = restaurantOnly;
        this.imagePath = imagePath;
        this.restaurantId = restaurantId;
    }

    public MenuItem(Long id, String name, String description, Double price, Boolean restaurantOnly, String imagePath, Long restaurantId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurantOnly = restaurantOnly;
        this.imagePath = imagePath;
        this.restaurantId = restaurantId;
    }

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Boolean restaurantOnly;
    private String imagePath;
    private Long restaurantId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
