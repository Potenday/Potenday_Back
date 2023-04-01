package com.example.protenday.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlantRequest {

    private String name;
    private String attribute;
    private String description;
    private Long imageId;
}
