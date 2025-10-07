package com.fiap.tech_challenge.core.domain.user;

import com.fiap.tech_challenge.core.domain.UserType;
import com.fiap.tech_challenge.core.domain.shared.Address;

import java.util.Date;

public class User {
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
    private UserType userType;
    private Address address;
    private Date lastUpdateDate;
    private Boolean active;

    public User(Long id, String name,
                String email, String username,
                String password, UserType userType,
                Address address, Date lastUpdateDate,
                Boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.address = address;
        this.lastUpdateDate = lastUpdateDate;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    public Address getAddress() {
        return address;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void changePassword(String password){
        this.password = password;
    }
}
