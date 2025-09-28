package com.fiap.tech_challenge.core.domain;

public class UserType {
    private Long id;
    private String name;

    public UserType(Long id) {
        this.id = id;
    }

    public UserType(String name) {
        this.name = name;
    }

    public UserType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
