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

    // All Args Constructor
    public User(Long id, String name, String email, String username,
                String password, UserType userType,
                Address address) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email.");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.address = address;
        this.lastUpdateDate = new Date();
        this.active = true;
    }

    public User(String name, String email, String username,
                String password, UserType userType,
                Address address) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email.");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.address = address;
        this.lastUpdateDate = new Date();
        this.active = true;
    }

    public User(String name, String email, String username, UserType userType,
                Address address) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email.");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }

        this.name = name;
        this.email = email;
        this.username = username;
        this.userType = userType;
        this.address = address;
        this.lastUpdateDate = new Date();
        this.active = true;
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

    public void changePassword(String newPassword){
        if(!newPassword.isEmpty()){
            this.password = newPassword;
        }else{
            throw new IllegalArgumentException("New password cannot be empty.");
        }
    }
}
