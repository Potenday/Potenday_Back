package com.example.protenday.controller;

import com.example.protenday.dto.response.PlantResponse;
import com.example.protenday.dto.request.PlantRequest;
import com.example.protenday.dto.response.Response;
import com.example.protenday.service.PlantEntityService;
import com.example.protenday.dto.Plant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trees")
public class PlantController {

    private final PlantEntityService plantEntityService;

    @PostMapping
    public Response<Void> registerPlant(@RequestBody PlantRequest request) {
        Plant plant = plantEntityService.registerPlant(request);
        return Response.success();
    }

    /** 전체 허용 */
    @GetMapping
    public Response<List<PlantResponse>> requestPlants() {
        List<Plant> plants = plantEntityService.requestPlants();

        return Response.success(plants.stream().map(PlantResponse::fromPlant).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public Response<PlantResponse> requestPlant(@PathVariable Long id) {
        Plant plant = plantEntityService.requestPlant(id);
        return Response.success(PlantResponse.fromPlant(plant));
    }

    @PutMapping("/{id}")
    public Response<PlantResponse> requestModifyPlant(@PathVariable Long id, @RequestBody PlantRequest request) {
        Plant plant = plantEntityService.requestModifyPlant(id, request);
        return Response.success(PlantResponse.fromPlant(plant));
    }

    @DeleteMapping("/{id}")
    public Response<Void> requestRemovePlant(@PathVariable Long id) {
        plantEntityService.requestRemovePlant(id);
        return Response.success();
    }
}
