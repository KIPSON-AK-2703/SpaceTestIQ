package com.spacetestiq.repository;

import com.spacetestiq.entity.AnomalyRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnomalyRepository
        extends JpaRepository<AnomalyRecord, Long> {

    List<AnomalyRecord> findBySystemName(String systemName);

    List<AnomalyRecord> findByAnomalyDetectedTrue();

    List<AnomalyRecord> findBySeverity(String severity);
}
