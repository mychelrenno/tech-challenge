package com.fiap.tech_challenge.infrastructure.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "customers")
public class CustomerJpa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String document;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private UserJpa userJpa;

    public CustomerJpa() {
    }

    public CustomerJpa(String document,
                       UserJpa userJpa) {
        this.document = document;
        this.userJpa = userJpa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public UserJpa getUserJpa() {
        return userJpa;
    }

    public void setUserJpa(UserJpa userJpa) {
        this.userJpa = userJpa;
    }

    @Override
    public String toString() {
        return "CustomerJpa{" +
                "id=" + id +
                ", document='" + document + '\'' +
                ", userJpa=" + userJpa +
                '}';
    }
}
