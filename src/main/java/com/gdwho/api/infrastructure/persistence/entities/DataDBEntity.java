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
    private String input;

    @Column(nullable = false, length = 100)
    private String output;

    @Column(nullable = false)
    private double label;

    @JsonBackReference("user-data")
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private UserDBEntity user;

    public DataDBEntity() {
    }

    public DataDBEntity(String input) {
        this.input = input;
    }

    public Long getId() {
        return id;
    }

    public void setUser(UserDBEntity user) {
        this.user = user;
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

    public UserDBEntity getUser() {
        return user;
    }

}
