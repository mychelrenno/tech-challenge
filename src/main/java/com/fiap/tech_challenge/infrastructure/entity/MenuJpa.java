package com.fiap.tech_challenge.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "menus")
public class MenuJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String dishPhoto;
}
