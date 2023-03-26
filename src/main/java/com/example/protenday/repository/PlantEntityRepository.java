package com.example.protenday.repository;

import com.example.protenday.domain.PlantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantEntityRepository extends JpaRepository<PlantEntity, Long> {
}
