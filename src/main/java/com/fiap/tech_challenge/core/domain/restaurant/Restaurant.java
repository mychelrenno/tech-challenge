package com.fiap.tech_challenge.core.domain.restaurant;

import com.fiap.tech_challenge.core.domain.Owner;
import com.fiap.tech_challenge.core.domain.shared.Address;

public class Restaurant {
    private Long id;
    private String name;
    private Address address;
    private String cuisineType;
    private String openingHours;
    private Owner owner; // Referência ao usuário dono

    public Restaurant(Long id, String name, Address address, String cuisineType, String openingHours, Owner owner) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome do restaurante não pode ser vazio.");
        }
        if (address == null) {
            throw new IllegalArgumentException("Endereço não pode ser nulo.");
        }
        if (cuisineType == null || cuisineType.isBlank()) {
            throw new IllegalArgumentException("Tipo de cozinha não pode ser vazio.");
        }
        if (openingHours == null || openingHours.isBlank()) {
            throw new IllegalArgumentException("Horário de funcionamento não pode ser vazio.");
        }
        if (owner == null) {
            throw new IllegalArgumentException("Dono do restaurante não pode ser nulo.");
        }
        this.id = id;
        this.name = name;
        this.address = address;
        this.cuisineType = cuisineType;
        this.openingHours = openingHours;
        this.owner = owner;
    }

    public Restaurant(String name, Address address, String cuisineType, String openingHours, Owner owner) {
        this(null, name, address, cuisineType, openingHours, owner);
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Address getAddress() { return address; }
    public String getCuisineType() { return cuisineType; }
    public String getOpeningHours() { return openingHours; }
    public Owner getOwner() { return owner; }
}

