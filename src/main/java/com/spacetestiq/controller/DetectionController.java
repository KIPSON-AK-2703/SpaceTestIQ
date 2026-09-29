package com.spacetestiq.controller;

import com.spacetestiq.detection.model.AnomalyResult;
import com.spacetestiq.detection.service.AnomalyDetectionService;
import com.spacetestiq.entity.TelemetryData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/detection")
public class DetectionController {

    private final AnomalyDetectionService anomalyDetectionService;

    public DetectionController(
            AnomalyDetectionService anomalyDetectionService) {

        this.anomalyDetectionService =
                anomalyDetectionService;
    }

    @PostMapping("/temperature")
    public ResponseEntity<AnomalyResult> detectTemperature(

            @RequestParam(
                    defaultValue = "DEMO_PROPULSION_PROFILE"
            )
            String profileName,

            @RequestBody TelemetryData telemetryData) {

        AnomalyResult result =
                anomalyDetectionService.detectTemperature(
                        telemetryData,
                        profileName
                );

        return ResponseEntity.ok(result);
    }
}