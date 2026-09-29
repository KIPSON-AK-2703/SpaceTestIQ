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

    public AnomalyResult detectTemperature(
            TelemetryData telemetryData,
            String profileName) {

        // 1. Find the test profile in MySQL
        TestProfile profile =
                testProfileRepository
                        .findByProfileName(profileName)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Test profile not found: "
                                                + profileName
                                )
                        );

        // 2. Read temperature limits from MySQL
        ThresholdRule temperatureRule =
                new ThresholdRule(
                        "Temperature",
                        profile.getTemperatureMinimum(),
                        profile.getTemperatureMaximum()
                );

        // 3. Run anomaly detection
        AnomalyResult result =
                thresholdDetector.detect(
                        telemetryData.getSystemName(),
                        "Temperature",
                        telemetryData.getTemperature(),
                        temperatureRule
                );

        // 4. Convert result to database entity
        AnomalyRecord record =
                new AnomalyRecord();

        record.setSystemName(
                result.getSystemName()
        );

        record.setParameterName(
                result.getParameterName()
        );

        record.setMeasuredValue(
                result.getMeasuredValue()
        );

        record.setDetectionMethod(
                result.getDetectionMethod()
        );

        record.setAnomalyDetected(
                result.isAnomalyDetected()
        );

        record.setSeverity(
                result.getSeverity()
        );

        record.setMessage(
                result.getMessage()
        );

        record.setDetectedAt(
                result.getDetectedAt()
        );

        // 5. Save result to MySQL
        anomalyRepository.save(record);

        // 6. Return result
        return result;
    }
}