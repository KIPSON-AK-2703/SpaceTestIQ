package com.spacetestiq.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TelemetryRequest {

    @NotBlank(message = "System name is required")
    private String systemName;

    @NotNull(message = "Timestamp is required")
    private LocalDateTime timestamp;

    @NotNull(message = "Temperature is required")
    private Double temperature;

    @NotNull(message = "Pressure is required")
    private Double pressure;

    @NotNull(message = "Voltage is required")
    private Double voltage;

    @NotNull(message = "Current is required")
    private Double current;

    @NotNull(message = "Vibration is required")
    @Min(value = 0, message = "Vibration cannot be negative")
    private Double vibration;

    @NotNull(message = "Battery is required")
    @Min(value = 0, message = "Battery cannot be below 0%")
    @Max(value = 100, message = "Battery cannot exceed 100%")
    private Double battery;

    public TelemetryRequest() {
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    public Double getVibration() {
        return vibration;
    }

    public void setVibration(Double vibration) {
        this.vibration = vibration;
    }

    public Double getBattery() {
        return battery;
    }

    public void setBattery(Double battery) {
        this.battery = battery;
    }
}
