package com.fiap.tech_challenge.infrastructure.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "addresses")
public class AddressJpa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String postalCode;
    private String street;
    private String additionalDetails;
    private String city;
    private String country;

    public AddressJpa() {
    }

    public AddressJpa(String postalCode,
                      String street, String additionalDetails,
                      String city, String country) {
        this.postalCode = postalCode;
        this.street = street;
        this.additionalDetails = additionalDetails;
        this.city = city;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "AddressJpa{" +
                "id=" + id +
                ", postalCode='" + postalCode + '\'' +
                ", street='" + street + '\'' +
                ", additionalDetails='" + additionalDetails + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
