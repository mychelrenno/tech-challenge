package com.fiap.tech_challenge.core.domain.restaurant;

public class Menu {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String dishPhoto;

    public Menu(Long id, String name,
                String description,
                Double price,
                String dishPhoto) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.dishPhoto = dishPhoto;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getDishPhoto() {
        return dishPhoto;
    }
}
