package com.fiap.tech_challenge.core.domain.shared;

public class Address {
    private Long id;
    private String postalCode;
    private String street;
    private String additionalDetails;
    private String city;
    private String country;

    // All Args Constructor
    public Address(Long id, String postalCode,
                   String street, String additionalDetails,
                   String city, String country) {
        if (postalCode == null || postalCode.isBlank()) {
            throw new IllegalArgumentException("Postal code cannot be empty.");
        }
        this.id = id;
        this.postalCode = postalCode;
        this.street = street;
        this.additionalDetails = additionalDetails;
        this.city = city;
        this.country = country;
    }

    public Address(String postalCode,
                   String street, String additionalDetails,
                   String city, String country) {
        if (postalCode == null || postalCode.isBlank()) {
            throw new IllegalArgumentException("Postal code cannot be empty.");
        }
        this.postalCode = postalCode;
        this.street = street;
        this.additionalDetails = additionalDetails;
        this.city = city;
        this.country = country;
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
