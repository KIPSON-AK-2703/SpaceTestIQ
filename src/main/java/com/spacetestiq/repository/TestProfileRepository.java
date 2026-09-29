package com.spacetestiq.repository;

import com.spacetestiq.entity.TestProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestProfileRepository
        extends JpaRepository<TestProfile, Long> {

    Optional<TestProfile> findByProfileName(String profileName);
}