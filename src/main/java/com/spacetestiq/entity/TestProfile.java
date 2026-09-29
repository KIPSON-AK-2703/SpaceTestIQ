package com.spacetestiq.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "test_profiles")
public class TestProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String profileName;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private double temperatureMinimum;

    @Column(nullable = false)
    private double temperatureMaximum;

    public TestProfile() {
    }

    public TestProfile(
            String profileName,
            String description,
            double temperatureMinimum,
            double temperatureMaximum) {

        this.profileName = profileName;
        this.description = description;
        this.temperatureMinimum = temperatureMinimum;
        this.temperatureMaximum = temperatureMaximum;
    }

    public Long getId() {
        return id;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemperatureMinimum() {
        return temperatureMinimum;
    }

    public void setTemperatureMinimum(double temperatureMinimum) {
        this.temperatureMinimum = temperatureMinimum;
    }

    public double getTemperatureMaximum() {
        return temperatureMaximum;
    }

    public void setTemperatureMaximum(double temperatureMaximum) {
        this.temperatureMaximum = temperatureMaximum;
    }
}