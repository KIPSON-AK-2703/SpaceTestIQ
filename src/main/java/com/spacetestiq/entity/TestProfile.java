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

    // Temperature limits
    @Column(nullable = false)
    private double temperatureMinimum;

    @Column(nullable = false)
    private double temperatureMaximum;

    // Pressure limits
    @Column(nullable = false)
    private double pressureMinimum;

    @Column(nullable = false)
    private double pressureMaximum;

    // Voltage limits
    @Column(nullable = false)
    private double voltageMinimum;

    @Column(nullable = false)
    private double voltageMaximum;

    // Default constructor required by JPA
    public TestProfile() {
    }

    // Full constructor
    public TestProfile(
            String profileName,
            String description,
            double temperatureMinimum,
            double temperatureMaximum,
            double pressureMinimum,
            double pressureMaximum,
            double voltageMinimum,
            double voltageMaximum) {

        this.profileName = profileName;
        this.description = description;

        this.temperatureMinimum = temperatureMinimum;
        this.temperatureMaximum = temperatureMaximum;

        this.pressureMinimum = pressureMinimum;
        this.pressureMaximum = pressureMaximum;

        this.voltageMinimum = voltageMinimum;
        this.voltageMaximum = voltageMaximum;
    }

    // ID
    public Long getId() {
        return id;
    }

    // Profile name
    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    // Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Temperature minimum
    public double getTemperatureMinimum() {
        return temperatureMinimum;
    }

    public void setTemperatureMinimum(double temperatureMinimum) {
        this.temperatureMinimum = temperatureMinimum;
    }

    // Temperature maximum
    public double getTemperatureMaximum() {
        return temperatureMaximum;
    }

    public void setTemperatureMaximum(double temperatureMaximum) {
        this.temperatureMaximum = temperatureMaximum;
    }

    // Pressure minimum
    public double getPressureMinimum() {
        return pressureMinimum;
    }

    public void setPressureMinimum(double pressureMinimum) {
        this.pressureMinimum = pressureMinimum;
    }

    // Pressure maximum
    public double getPressureMaximum() {
        return pressureMaximum;
    }

    public void setPressureMaximum(double pressureMaximum) {
        this.pressureMaximum = pressureMaximum;
    }

    // Voltage minimum
    public double getVoltageMinimum() {
        return voltageMinimum;
    }

    public void setVoltageMinimum(double voltageMinimum) {
        this.voltageMinimum = voltageMinimum;
    }

    // Voltage maximum
    public double getVoltageMaximum() {
        return voltageMaximum;
    }

    public void setVoltageMaximum(double voltageMaximum) {
        this.voltageMaximum = voltageMaximum;
    }
}