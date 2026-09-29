package com.spacetestiq.service;

import com.spacetestiq.entity.TelemetryData;
import com.spacetestiq.repository.TelemetryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelemetryService {

    private final TelemetryRepository telemetryRepository;

    public TelemetryService(TelemetryRepository telemetryRepository) {
        this.telemetryRepository = telemetryRepository;
    }

    public TelemetryData saveTelemetry(TelemetryData telemetryData) {
        return telemetryRepository.save(telemetryData);
    }

    public List<TelemetryData> getAllTelemetry() {
        return telemetryRepository.findAll();
    }

    public TelemetryData getTelemetryById(Long id) {
        return telemetryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Telemetry data not found with ID: " + id));
    }

    public void deleteTelemetry(Long id) {
        telemetryRepository.deleteById(id);
    }
}
