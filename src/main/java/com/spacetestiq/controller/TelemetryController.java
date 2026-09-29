package com.spacetestiq.controller;

import com.spacetestiq.dto.TelemetryRequest;
import com.spacetestiq.entity.TelemetryData;
import com.spacetestiq.service.TelemetryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/telemetry")
public class TelemetryController {

    private final TelemetryService telemetryService;

    public TelemetryController(TelemetryService telemetryService) {
        this.telemetryService = telemetryService;
    }

    // Save new telemetry data
    @PostMapping
    public ResponseEntity<TelemetryData> saveTelemetry(
            @Valid @RequestBody TelemetryRequest request) {

        TelemetryData telemetryData = new TelemetryData();

        telemetryData.setSystemName(request.getSystemName());
        telemetryData.setTimestamp(request.getTimestamp());
        telemetryData.setTemperature(request.getTemperature());
        telemetryData.setPressure(request.getPressure());
        telemetryData.setVoltage(request.getVoltage());
        telemetryData.setCurrent(request.getCurrent());
        telemetryData.setVibration(request.getVibration());
        telemetryData.setBattery(request.getBattery());

        TelemetryData savedData =
                telemetryService.saveTelemetry(telemetryData);

        return ResponseEntity.ok(savedData);
    }

    // Get all telemetry data
    @GetMapping
    public ResponseEntity<List<TelemetryData>> getAllTelemetry() {

        return ResponseEntity.ok(
                telemetryService.getAllTelemetry()
        );
    }

    // Get telemetry by ID
    @GetMapping("/{id}")
    public ResponseEntity<TelemetryData> getTelemetryById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                telemetryService.getTelemetryById(id)
        );
    }

    // Delete telemetry
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelemetry(
            @PathVariable Long id) {

        telemetryService.deleteTelemetry(id);

        return ResponseEntity.noContent().build();
    }
}
