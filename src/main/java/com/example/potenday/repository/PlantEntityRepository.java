package com.example.potenday.repository;

import com.example.potenday.domain.PlantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlantEntityRepository extends JpaRepository<PlantEntity, Long> {

    Optional<PlantEntity> findByName(String name);
}
