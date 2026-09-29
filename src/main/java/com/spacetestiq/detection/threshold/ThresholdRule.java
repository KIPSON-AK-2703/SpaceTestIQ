package com.spacetestiq.detection.threshold;

public class ThresholdRule {

    private String parameterName;

    private double minimumValue;

    private double maximumValue;

    public ThresholdRule() {
    }

    public ThresholdRule(
            String parameterName,
            double minimumValue,
            double maximumValue) {

        this.parameterName = parameterName;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public double getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(double minimumValue) {
        this.minimumValue = minimumValue;
    }

    public double getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(double maximumValue) {
        this.maximumValue = maximumValue;
    }
}