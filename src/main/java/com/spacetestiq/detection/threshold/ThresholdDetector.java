package com.spacetestiq.detection.threshold;

import com.spacetestiq.detection.model.AnomalyResult;

import java.time.LocalDateTime;

public class ThresholdDetector {

    public AnomalyResult detect(
            String systemName,
            String parameterName,
            double measuredValue,
            ThresholdRule rule) {

        AnomalyResult result = new AnomalyResult();

        result.setSystemName(systemName);
        result.setParameterName(parameterName);
        result.setMeasuredValue(measuredValue);
        result.setDetectionMethod("THRESHOLD");
        result.setDetectedAt(LocalDateTime.now());

        boolean belowMinimum =
                measuredValue < rule.getMinimumValue();

        boolean aboveMaximum =
                measuredValue > rule.getMaximumValue();

        if (belowMinimum || aboveMaximum) {

            result.setAnomalyDetected(true);
            result.setSeverity(determineSeverity(
                    measuredValue,
                    rule
            ));

            result.setMessage(
                    buildMessage(
                            parameterName,
                            measuredValue,
                            rule
                    )
            );

        } else {

            result.setAnomalyDetected(false);
            result.setSeverity("NORMAL");

            result.setMessage(
                    parameterName + " is within the configured threshold."
            );
        }

        return result;
    }

    private String determineSeverity(
            double measuredValue,
            ThresholdRule rule) {

        double minimum = rule.getMinimumValue();
        double maximum = rule.getMaximumValue();

        double range = maximum - minimum;

        if (range <= 0) {
            return "UNKNOWN";
        }

        double distance;

        if (measuredValue < minimum) {
            distance = minimum - measuredValue;
        } else {
            distance = measuredValue - maximum;
        }

        double percentageBeyondLimit =
                (distance / range) * 100.0;

        if (percentageBeyondLimit >= 50) {
            return "CRITICAL";
        }

        if (percentageBeyondLimit >= 20) {
            return "HIGH";
        }

        return "MEDIUM";
    }

    private String buildMessage(
            String parameterName,
            double measuredValue,
            ThresholdRule rule) {

        if (measuredValue < rule.getMinimumValue()) {

            return parameterName
                    + " is below the configured minimum. "
                    + "Measured: "
                    + measuredValue
                    + ", Minimum: "
                    + rule.getMinimumValue();
        }

        return parameterName
                + " is above the configured maximum. "
                + "Measured: "
                + measuredValue
                + ", Maximum: "
                + rule.getMaximumValue();
    }
}