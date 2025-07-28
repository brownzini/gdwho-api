package com.gdwho.api.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "user_guess")
public class GuessDBEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String input;

    @JsonBackReference("user-guess")
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private UserDBEntity user;

    public void setUser(UserDBEntity user) {
        this.user = user;
    }

    public GuessDBEntity() {
    }

    public GuessDBEntity(Long id, String input) {
        this.input = input;
    }
    
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Long getId() {
        return id;
    }

    public UserDBEntity getUser() {
        return user;
    }

}
