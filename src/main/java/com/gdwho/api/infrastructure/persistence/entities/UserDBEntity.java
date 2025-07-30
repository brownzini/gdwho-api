package com.gdwho.api.infrastructure.persistence.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdwho.api.domain.entities.user.RoleEnum;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserDBEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column(length = 100)
    private String dataResponse;

    @Column(nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", updatable = false)
    private Instant createdAt;

    @JsonManagedReference("user-data")
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DataDBEntity> data = new ArrayList<>();

    public UserDBEntity() {
    }

    public UserDBEntity(String username, String password, RoleEnum role, String dataResponse, Instant createdAt,
            List<DataDBEntity> data) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.dataResponse = dataResponse;
        this.createdAt = createdAt;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public List<DataDBEntity> getData() {
        return data;
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

    public String getDataResponse() {
        return dataResponse;
    }

    public void setDataResponse(String dataResponse) {
        this.dataResponse = dataResponse;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    private void onCreate() {
        createdAt = Instant.now();
    }

}
