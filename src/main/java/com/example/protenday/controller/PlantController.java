package com.example.protenday.controller;

import com.example.protenday.dto.request.PlantRequest;
import com.example.protenday.dto.response.Response;
import com.example.protenday.service.PlantEntityService;
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

    @GetMapping("/test")
    public Response<Void> test() {
        return Response.success();
    }

    @PostMapping
    public Response<Void> registerPlant(@RequestBody PlantRequest request) {
        Plant plant = plantEntityService.registerPlant(request);
        return Response.success();
    }

    /** 전체 허용 */
    @GetMapping
    public Response<Void> requestPlants() {
        List<Plant> plants = plantEntityService.requestPlants();
        return Response.success();
    }

    @GetMapping("/{id}")
    public Response<Void> requestPlant(@PathVariable Long id) {
        Plant plant = plantEntityService.requestPlant(id);
        return Response.success();
    }

    @PutMapping("/{id}")
    public Response<Void> requestModifyPlant(@PathVariable Long id, @RequestBody PlantRequest request) {
        Plant plant = plantEntityService.requestModifyPlant(id, request);
        return Response.success();
    }

    @DeleteMapping("/{id}")
    public Response<Void> requestRemovePlant(@PathVariable Long id) {
        plantEntityService.requestRemovePlant(id);
        return Response.success();
    }
}
