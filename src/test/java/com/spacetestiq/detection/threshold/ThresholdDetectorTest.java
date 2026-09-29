package com.spacetestiq.detection.threshold;

import com.spacetestiq.detection.model.AnomalyResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThresholdDetectorTest {

    @Test
    void normalTemperatureShouldNotBeAnomaly() {

        ThresholdRule rule =
                new ThresholdRule("Temperature", 10.0, 60.0);

        ThresholdDetector detector =
                new ThresholdDetector();

        AnomalyResult result =
                detector.detect(
                        "PROPULSION_TEST_01",
                        "Temperature",
                        45.0,
                        rule
                );

        assertFalse(result.isAnomalyDetected());
        assertEquals("NORMAL", result.getSeverity());
    }

    @Test
    void highTemperatureShouldBeAnomaly() {

        ThresholdRule rule =
                new ThresholdRule("Temperature", 10.0, 60.0);

        ThresholdDetector detector =
                new ThresholdDetector();

        AnomalyResult result =
                detector.detect(
                        "PROPULSION_TEST_01",
                        "Temperature",
                        65.0,
                        rule
                );

        assertTrue(result.isAnomalyDetected());
        assertNotEquals("NORMAL", result.getSeverity());
    }

    @Test
    void lowTemperatureShouldBeAnomaly() {

        ThresholdRule rule =
                new ThresholdRule("Temperature", 10.0, 60.0);

        ThresholdDetector detector =
                new ThresholdDetector();

        AnomalyResult result =
                detector.detect(
                        "PROPULSION_TEST_01",
                        "Temperature",
                        5.0,
                        rule
                );

        assertTrue(result.isAnomalyDetected());
        assertNotEquals("NORMAL", result.getSeverity());
    }
}
