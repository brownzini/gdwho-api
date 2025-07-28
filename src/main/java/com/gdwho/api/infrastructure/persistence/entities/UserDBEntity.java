package com.gdwho.api.infrastructure.persistence.entities;

import java.time.Instant;

import com.gdwho.api.domain.entities.user.RoleEnum;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserDBEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 700)
    private String username;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column(nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", updatable = false)
    private Instant createdAt;

    public UserDBEntity() {
    }

    public UserDBEntity(String username, String password, RoleEnum role, Instant createdAt) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    private void onCreate() {
        createdAt = Instant.now();
    }

}
