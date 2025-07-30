package com.gdwho.api.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "users_entries")
public class EntriesDBEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String input;

    @Column(nullable = false, length = 100)
    private String output;

    @Column(nullable = false)
    private double label;

    @JsonBackReference("user-entries")
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private UserDBEntity user;

    public EntriesDBEntity() {
    }

    public EntriesDBEntity(String input, String output, double label) {
        this.input = input;
        this.output = output;
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public double getLabel() {
        return label;
    }

    public void setLabel(double label) {
        this.label = label;
    }

    public void setUser(UserDBEntity user) {
        this.user = user;
    }

    public UserDBEntity getUser() {
        return user;
    }

}
