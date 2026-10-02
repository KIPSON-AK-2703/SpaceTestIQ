package com.spacetestiq.detection.service;

import com.spacetestiq.detection.model.AnomalyResult;
import com.spacetestiq.detection.threshold.ThresholdDetector;
import com.spacetestiq.detection.threshold.ThresholdRule;
import com.spacetestiq.entity.AnomalyRecord;
import com.spacetestiq.entity.TelemetryData;
import com.spacetestiq.entity.TestProfile;
import com.spacetestiq.repository.AnomalyRepository;
import com.spacetestiq.repository.TestProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class AnomalyDetectionService {

    private final ThresholdDetector thresholdDetector;
    private final AnomalyRepository anomalyRepository;
    private final TestProfileRepository testProfileRepository;

    public AnomalyDetectionService(
            AnomalyRepository anomalyRepository,
            TestProfileRepository testProfileRepository) {

        this.thresholdDetector = new ThresholdDetector();
        this.anomalyRepository = anomalyRepository;
        this.testProfileRepository = testProfileRepository;
    }

    // ---------------------------------------------------------
    // TEMPERATURE DETECTION
    // ---------------------------------------------------------

    public AnomalyResult detectTemperature(
            TelemetryData telemetryData,
            String profileName) {

        TestProfile profile = getProfile(profileName);

        ThresholdRule temperatureRule =
                new ThresholdRule(
                        "Temperature",
                        profile.getTemperatureMinimum(),
                        profile.getTemperatureMaximum()
                );

        AnomalyResult result =
                thresholdDetector.detect(
                        telemetryData.getSystemName(),
                        "Temperature",
                        telemetryData.getTemperature(),
                        temperatureRule
                );

        saveAnomaly(result);

        return result;
    }

    // ---------------------------------------------------------
    // PRESSURE DETECTION
    // ---------------------------------------------------------

    public AnomalyResult detectPressure(
            TelemetryData telemetryData,
            String profileName) {

        TestProfile profile = getProfile(profileName);

        ThresholdRule pressureRule =
                new ThresholdRule(
                        "Pressure",
                        profile.getPressureMinimum(),
                        profile.getPressureMaximum()
                );

        AnomalyResult result =
                thresholdDetector.detect(
                        telemetryData.getSystemName(),
                        "Pressure",
                        telemetryData.getPressure(),
                        pressureRule
                );

        saveAnomaly(result);

        return result;
    }

    // ---------------------------------------------------------
    // VOLTAGE DETECTION
    // ---------------------------------------------------------

    public AnomalyResult detectVoltage(
            TelemetryData telemetryData,
            String profileName) {

        TestProfile profile = getProfile(profileName);

        ThresholdRule voltageRule =
                new ThresholdRule(
                        "Voltage",
                        profile.getVoltageMinimum(),
                        profile.getVoltageMaximum()
                );

        AnomalyResult result =
                thresholdDetector.detect(
                        telemetryData.getSystemName(),
                        "Voltage",
                        telemetryData.getVoltage(),
                        voltageRule
                );

        saveAnomaly(result);

        return result;
    }

    // ---------------------------------------------------------
    // GET TEST PROFILE
    // ---------------------------------------------------------

    private TestProfile getProfile(String profileName) {

        return testProfileRepository
                .findByProfileName(profileName)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Test profile not found: "
                                        + profileName
                        )
                );
    }

    // ---------------------------------------------------------
    // SAVE ANOMALY RESULT
    // ---------------------------------------------------------

    private void saveAnomaly(AnomalyResult result) {

        AnomalyRecord record = new AnomalyRecord();

        record.setSystemName(result.getSystemName());
        record.setParameterName(result.getParameterName());
        record.setMeasuredValue(result.getMeasuredValue());
        record.setDetectionMethod(result.getDetectionMethod());
        record.setAnomalyDetected(result.isAnomalyDetected());
        record.setSeverity(result.getSeverity());
        record.setMessage(result.getMessage());
        record.setDetectedAt(result.getDetectedAt());

        anomalyRepository.save(record);
    }
}