package com.fiap.tech_challenge.core.domain;

public class UserType {
    private Long id;
    private String name;

    public UserType(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
