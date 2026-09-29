package com.spacetestiq.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "anomaly_records")
public class AnomalyRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String systemName;

    @Column(nullable = false)
    private String parameterName;

    @Column(nullable = false)
    private double measuredValue;

    @Column(nullable = false)
    private String detectionMethod;

    @Column(nullable = false)
    private boolean anomalyDetected;

    @Column(nullable = false)
    private String severity;

    @Column(length = 1000)
    private String message;

    @Column(nullable = false)
    private LocalDateTime detectedAt;

    public AnomalyRecord() {
    }

    public Long getId() {
        return id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public double getMeasuredValue() {
        return measuredValue;
    }

    public void setMeasuredValue(double measuredValue) {
        this.measuredValue = measuredValue;
    }

    public String getDetectionMethod() {
        return detectionMethod;
    }

    public void setDetectionMethod(String detectionMethod) {
        this.detectionMethod = detectionMethod;
    }

    public boolean isAnomalyDetected() {
        return anomalyDetected;
    }

    public void setAnomalyDetected(boolean anomalyDetected) {
        this.anomalyDetected = anomalyDetected;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDetectedAt() {
        return detectedAt;
    }

    public void setDetectedAt(LocalDateTime detectedAt) {
        this.detectedAt = detectedAt;
    }
}
