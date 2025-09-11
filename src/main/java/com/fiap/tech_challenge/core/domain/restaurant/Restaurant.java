package com.fiap.tech_challenge.core.domain.restaurant;

import com.fiap.tech_challenge.core.domain.Owner;
import com.fiap.tech_challenge.core.domain.shared.Address;

import java.util.List;

public class Restaurant {
    private Long id;
    private String name;
    private Address address;
    private RestaurantType restaurantType;
    private List<OpeningTime> openingTimePeriods;
    private Owner owner;

    public Restaurant(Long id, String name,
                      Address address,
                      RestaurantType restaurantType,
                      List<OpeningTime> openingTimePeriods,
                      Owner owner) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.restaurantType = restaurantType;
        this.openingTimePeriods = openingTimePeriods;
        this.owner = owner;
    }

    public Restaurant(String name,
                      Address address,
                      RestaurantType restaurantType,
                      List<OpeningTime> openingTimePeriods,
                      Owner owner) {
        this.name = name;
        this.address = address;
        this.restaurantType = restaurantType;
        this.openingTimePeriods = openingTimePeriods;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public RestaurantType getRestaurantType() {
        return restaurantType;
    }

    public List<OpeningTime> getOpeningTimePeriods() {
        return openingTimePeriods;
    }

    public Owner getOwner() {
        return owner;
    }
}
