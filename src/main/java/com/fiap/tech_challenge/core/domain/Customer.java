package com.fiap.tech_challenge.core.domain;

import com.fiap.tech_challenge.core.domain.order.Order;
import com.fiap.tech_challenge.core.domain.user.User;

import java.util.List;

public class Customer {
    private Long id;
    private String document;
    private User user;

    public Customer(Long id, String document, User user) {
        this.id = id;
        this.document = document;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getDocument() {
        return document;
    }

    public User getUser() {
        return user;
    }
}
