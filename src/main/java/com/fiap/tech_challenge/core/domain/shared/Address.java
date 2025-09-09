package com.fiap.tech_challenge.core.domain.shared;

public class Address {
    private Long id;
    private String postalCode;
    private String street;
    private String additionalDetails;
    private String city;
    private String country;

    // All Args Constructor
    public Address() {
        if (postalCode == null || postalCode.isBlank()) {
            throw new IllegalArgumentException("Postal code cannot be empty.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
