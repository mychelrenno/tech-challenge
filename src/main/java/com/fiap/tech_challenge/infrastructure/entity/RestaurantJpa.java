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
    @ManyToOne()
    @JoinColumn(name = "owner_id")
    private OwnerJpa owner;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_jpa_id")
    private AddressJpa addressJpa;


    public RestaurantJpa() {}

    public RestaurantJpa(Long id, String name, AddressJpa addressJpa, String cuisineType, String openingHours, OwnerJpa owner) {
        this.id = id;
        this.name = name;
        this.addressJpa = addressJpa;
        this.cuisineType = cuisineType;
        this.openingHours = openingHours;
        this.owner = owner;
    }

    public RestaurantJpa(String name, String cuisineType, String openingHours, AddressJpa addressJpa) {
        this.name = name;
        this.cuisineType = cuisineType;
        this.openingHours = openingHours;
        this.addressJpa = addressJpa;
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
    public OwnerJpa getOwner() { return owner; }
    public void setOwner(OwnerJpa owner) { this.owner = owner; }
}
