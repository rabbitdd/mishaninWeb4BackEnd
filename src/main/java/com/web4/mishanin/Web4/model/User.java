package com.web4.mishanin.Web4.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generate")
    //@SequenceGenerator(sequenceName = "customer_seq", allocationSize = 1, name = "CUSTOMER_SEQ")
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generate")
    //@Column(columnDefinition = "serial")
    @SequenceGenerator(sequenceName = "serial", allocationSize = 5, name="serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generate")
    private int id;

    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "active")
    private boolean active;
    @Column(name = "role")
    private String role;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "email: " + this.email + " username: " + this.username + " password: " + this.password;
    }
}
