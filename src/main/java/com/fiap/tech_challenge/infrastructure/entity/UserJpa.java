package com.fiap.tech_challenge.infrastructure.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
public class UserJpa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_type_jpa_id")
    private UserTypeJpa userTypeJpa;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_jpa_id")
    private AddressJpa addressJpa;
    private Date lastUpdateDate;
    private Boolean active;

    public UserJpa() {
    }

    public UserJpa(String name,
                   String email, String username,
                   String password, UserTypeJpa userTypeJpa,
                   AddressJpa addressJpa, Date lastUpdateDate,
                   Boolean active) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userTypeJpa = userTypeJpa;
        this.addressJpa = addressJpa;
        this.lastUpdateDate = lastUpdateDate;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserTypeJpa getUserTypeJpa() {
        return userTypeJpa;
    }

    public void setUserTypeJpa(UserTypeJpa userTypeJpa) {
        this.userTypeJpa = userTypeJpa;
    }

    public AddressJpa getAddressJpa() {
        return addressJpa;
    }

    public void setAddressJpa(AddressJpa addressJpa) {
        this.addressJpa = addressJpa;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "UserJpa{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userTypeJpa=" + userTypeJpa +
                ", addressJpa=" + addressJpa +
                ", lastUpdateDate=" + lastUpdateDate +
                ", active=" + active +
                '}';
    }
}
