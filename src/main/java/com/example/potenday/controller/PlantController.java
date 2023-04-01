package com.example.potenday.controller;

import com.example.potenday.dto.request.PlantRequest;
import com.example.potenday.service.PlantEntityService;
import com.example.protenday.dto.Plant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trees")
public class PlantController {

    private PlantEntityService plantEntityService;

    // TODO: Response 통일
    @PostMapping
    public void registerPlant(@RequestBody PlantRequest request) {
        Plant plant = plantEntityService.registerPlant(request);
    }

    @GetMapping
    public void requestPlants() {
        List<Plant> plants = plantEntityService.requestPlants();
    }

    @GetMapping("/{id}")
    public void requestPlant(@PathVariable Long id) {
        Plant plant = plantEntityService.requestPlant(id);
    }

    @PutMapping("/{id}")
    public void requestModifyPlant(@PathVariable Long id, @RequestBody PlantRequest request) {
        Plant plant = plantEntityService.requestModifyPlant(id, request);
    }

    @DeleteMapping("/{id}")
    public void requestRemovePlant(@PathVariable Long id) {
        plantEntityService.requestRemovePlant(id);
    }
}
