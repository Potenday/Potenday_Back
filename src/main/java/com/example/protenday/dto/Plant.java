package com.example.protenday.dto;

import com.example.protenday.domain.PlantEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plant {
    private Long id;
    private String name;
    private String attribute;
    private String description;
    private LocalDateTime registeredAt;
    private String registeredBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    public static Plant fromEntity(PlantEntity entity) {
        return Plant.builder()
                .id(entity.getId())
                .name(entity.getName())
                .attribute(entity.getAttribute())
                .description(entity.getDescription())
                .registeredAt(entity.registeredAt)
                .registeredBy(entity.registeredBy)
                .modifiedAt(entity.modifiedAt)
                .modifiedBy(entity.modifiedBy)
                .build();
    }
}
