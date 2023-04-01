package com.example.potenday.repository;

import com.example.potenday.domain.PlantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantEntityRepository extends JpaRepository<PlantEntity, Long> {
}
