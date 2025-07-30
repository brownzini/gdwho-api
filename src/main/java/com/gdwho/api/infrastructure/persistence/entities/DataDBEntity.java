package com.gdwho.api.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "user_data")
public class DataDBEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String value;

    @JsonBackReference("user-data")
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private UserDBEntity user;

    public DataDBEntity() {
    }

    public DataDBEntity(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setUser(UserDBEntity user) {
        this.user = user;
    }

    public String getValue() {
        return value;
    }

    public void setInput(String value) {
        this.value = value;
    }

    public UserDBEntity getUser() {
        return user;
    }

}
