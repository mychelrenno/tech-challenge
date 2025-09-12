package com.fiap.tech_challenge.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurants")
public class RestaurantJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cuisineType;
    private String openingHours;
    private Long ownerId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_jpa_id")
    private AddressJpa addressJpa;


    public RestaurantJpa() {}

    public RestaurantJpa(Long id, String name, AddressJpa addressJpa, String cuisineType, String openingHours, Long ownerId) {
        this.id = id;
        this.name = name;
        this.addressJpa = addressJpa;
        this.cuisineType = cuisineType;
        this.openingHours = openingHours;
        this.ownerId = ownerId;
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public AddressJpa getAddressJpa() { return addressJpa; }
    public void setAddressJpa(AddressJpa address) { this.addressJpa = address; }
    public String getCuisineType() { return cuisineType; }
    public void setCuisineType(String cuisineType) { this.cuisineType = cuisineType; }
    public String getOpeningHours() { return openingHours; }
    public void setOpeningHours(String openingHours) { this.openingHours = openingHours; }
    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
}
