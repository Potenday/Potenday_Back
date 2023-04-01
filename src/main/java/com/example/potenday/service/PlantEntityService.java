package com.example.potenday.service;

import com.example.potenday.domain.PlantEntity;
import com.example.potenday.dto.request.PlantRequest;
import com.example.potenday.repository.PlantEntityRepository;
import com.example.protenday.dto.Plant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PlantEntityService {

    private final PlantEntityRepository plantEntityRepository;

    /**
     * 식물 생성
     * @param request
     * @return
     */
    public Plant registerPlant(PlantRequest request) {
        Optional<PlantEntity> plantEntity = plantEntityRepository.findByName(request.getName());

        if(!Objects.isNull(plantEntity)) {
            // 이미 존재하다는 에러 출력
            return null;
        }

        PlantEntity savedPlant = plantEntityRepository.save(PlantEntity.builder()
                .name(request.getName())
                .attribute(request.getAttribute())
                .description(request.getDescription())
                .build());

        return Plant.fromEntity(savedPlant);
    }

    /**
     * 식물 리스트 조회
     * @return
     */
    public List<Plant> requestPlants() {
        // 캐시를 통해 바로 전달하는 것이 좋을 것으로 판단됨.
        return plantEntityRepository.findAll().stream()
                .map(Plant::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * 식물 조회
     * @param id
     * @return
     */
    public Plant requestPlant(Long id) {
        // TODO 없을 경우에 대한 처리
        PlantEntity plantEntity = getPlantEntityOrException(id);

        return Plant.fromEntity(plantEntity);
    }

    /**
     * 식물 수정
     * @param id
     * @param request
     * @return
     */
    @Transactional
    public Plant requestModifyPlant(Long id, PlantRequest request) {
        PlantEntity plantEntity = getPlantEntityOrException(id);

        plantEntity.updatePlant(request);

        return Plant.fromEntity(plantEntity);
    }

    private PlantEntity getPlantEntityOrException(Long id) {
        return plantEntityRepository.findById(id).orElseThrow();
    }

    public void requestRemovePlant(Long id) {
        plantEntityRepository.deleteById(id);
    }
}
