package com.example.protenday.repository;

import com.example.protenday.domain.ForestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForestEntityRepository extends JpaRepository<ForestEntity, Long> {

    ForestEntity findByUserEntity_Id(Long id);
}
