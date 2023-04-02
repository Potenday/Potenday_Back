package com.example.protenday.dto.response;

import com.example.protenday.dto.Plant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlantResponse {
    private Long id;
    private Long imageId;
    private String name;
    private String attribute;
    private String description;

    public static PlantResponse fromPlant(Plant plant) {
        return PlantResponse.builder()
                .id(plant.getId())
                .imageId(plant.getImageId())
                .attribute(plant.getAttribute())
                .description(plant.getDescription())
                .build();
    }
}
