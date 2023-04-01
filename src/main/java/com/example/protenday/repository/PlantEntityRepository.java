package com.example.protenday.repository;

import com.example.protenday.domain.PlantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlantEntityRepository extends JpaRepository<PlantEntity, Long> {

    Optional<PlantEntity> findByName(String name);
}
